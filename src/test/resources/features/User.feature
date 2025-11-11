@UserFeature
Feature: User Feature

  @Positive @FindUserByValidUsername
  Scenario Outline:[P] Get user by username with valid value
    Given [pet-store] prepare user data with parameter '<value>'
    When  [pet-store] send request to find user by username
    Then  [pet-store] the response status code should be 200
    And   [pet-store] the response body should contain the username '<value>'
    # From API ->   The name that needs to be fetched. Use user1 for testing.
    Examples:
      | value |
      | user1 |

  @Positive @LoginAndLogout
  Scenario:[P] Log in and Log out from the system
    Given [pet-store] prepare user data with username '1234' and password 'testing'
    When  [pet-store] send request to login
    Then  [pet-store] the response status code should be 200
    And   [pet-store] send request to logout
    Then  [pet-store] the response status code should be 200

  @Negative @FindUserByInvalidUsername
  Scenario Outline:[N] Get user by username with invalid data username '<value>'
    Given [pet-store] prepare user data with parameter '<value>'
    When  [pet-store] send request to find user by username
    Then  [pet-store] the response status code should be <statusCode>
    Examples:
      | value    | statusCode |
      | user1245 | 404        |
      |          | 405        |