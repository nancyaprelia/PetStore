@PetFeature
Feature: Find Pets by Status

  @Positive @FindPetsWithValidStatus
  Scenario Outline:[P] Get '<status>' pets by valid status
    Given [pet-store] prepare pet status data with parameter '<status>'
    When  [pet-store] send request to find pets by status
    And   [pet-store] the response should contain pets with status '<status>'
    Examples:
      | status    |
      | available |
      | pending   |
      | sold      |

  @Negative @FindPetsWithInvalidStatus
  Scenario Outline:[N] Get '<status>' pets by invalid status
    Given [pet-store] prepare pet status data with parameter '<status>'
    When  [pet-store] send request to find pets by status
    And   [pet-store] the response should be empty
    Examples:
      | status  |
      | testing |
      | 3555436 |