Feature: UserEntity class behavior

  Scenario: Create a new UserEntity
    Given a user ID
    And a user name
    When a UserEntity is created
    Then the UserEntity should have the given ID and name

