Scenario: Start a new round
   Given I am playing a game
   And the last round was won
   And the last round had "<previous length>" letters
   When I start a new round
   Then the word to guess has "<next length>" letters

    | previous length | next length |
    | 5               | 6           |
    | 6               | 7           |
    | 7               | 5           |

Scenario: player cannot start a new round when the player is still playing
    Given a round is active
    When the player is trying to start a new one
    Then the new game will not start

Scenario: player guesses a word
    Given I am playing a game
    And the word to guess is "<word to guess>"
    When I guess "<attempt>"
    Then I get feedback "<feedback>"

Scenario: the guess was correct
   Given the game has been started
   And I guessed the word
   Then the round has been ended
   And my score increases by 5*(5-number of "rounds")+5

Scenario: Using a Winning word from a earlier round
    Given a round has been won
    And a new one has started
    When I try to guess the word with the winning word from an earlier round
    Then I cannot submit the guess with that word

Scenario: the guess was incorrect
    Given the game has been started
    And I guessed the word
    When the "<attempt>" is incorrect
    Then I get feedback "<feedback>"

Scenario: Lost a game
    Given a round has started
    And I used 4 chances
    When I try to guess a word
    And the guess was wrong
    Then the round is over
    And the player lost a game

Scenario: player cannot guess a word when he lost
    Given the player has lost a game
    Then the player cannot guess a word

Scenario: player cannot guess a word when he lost
    Given the player has lost a game
    Then the player cannot guess a word

Scenario: player cannot guess a word when a new round has not been started
    Given no round is started
    When the player is trying to guess a word
    Then the word will not be guessed

