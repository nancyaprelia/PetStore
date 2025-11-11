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

  @Positive @AddNewPet
  Scenario Outline: [P] Add new pet to pet store
    Given [pet-store] prepare add data pet to the pet store
    Then  [pet-store] set data 'id' with value '<id>'
    Then  [pet-store] set data 'categoryId' with value '<categoryId>'
    Then  [pet-store] set data 'categoryName' with value '<categoryName>'
    Then  [pet-store] set data 'name' with value '<name>'
    Then  [pet-store] set data 'photoUrls' with value '<photoUrls>'
    Then  [pet-store] set data 'tagId' with value '<tagId>'
    Then  [pet-store] set data 'tagName' with value '<tagName>'
    Then  [pet-store] set data 'status' with value '<status>'
    When  [pet-store] send request to create new pets
    And   [pet-store] validate pets created
    Examples:
      | id    | categoryId | categoryName | name      | photoUrls   | tagId      | tagName   | status    |
      | 22443 | 12345      | testing      | crocodile | www.123.com | chicken123 | chicken00 | available |

  @Positive @FindPetById
  Scenario Outline: [P] Get pet by id
    Given [pet-store] prepare data to get pet by '<id>'
    When  [pet-store] send request to find pets by id
    And   [pet-store] validate the status should be '<status>'
    Examples:
      | id | status    |
      | 2  | available |