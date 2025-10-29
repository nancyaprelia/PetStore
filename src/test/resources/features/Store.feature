@StoreFeature
Feature: Store Feature

  @Positive @FindByOrderIdWithValidId
  Scenario Outline:[P] Find purchase order by Id <orderId> for case valid id
    Given [pet-store] prepare order data with parameter <orderId>
    Then  [pet-store] send request to find order by order id
    And   [pet-store] the response body should contain the id <orderId>
    # From API -> For valid response try integer IDs with value >= 1 and <= 10. Other values will generated exceptions
    Examples:
      | orderId |
      | 1       |
      | 2       |
      | 3       |
      | 4       |
      | 5       |
      | 6       |
      | 7       |
      | 8       |
      | 9       |
      | 10      |

  @Negative @FindByOrderIdWithInvalidId
  Scenario Outline:[N] Find purchase order by Id <orderId> for case invalid id
    Given [pet-store] prepare order data with parameter <orderId>
    Then  [pet-store] send request to find order by order id
    And   [pet-store] the response body should contain the 'type' 'ERROR'
    And   [pet-store] the response body should contain the 'message' 'ORDER NOT FOUND'
    Examples:
      | orderId  |
      | 13544444 |