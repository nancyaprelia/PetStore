package steps;

import controller.OrderController;
import data.OrderData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@Slf4j
public class OrderSteps {
    OrderController orderController = new OrderController();
    OrderData orderData = new OrderData();
    Response actualData;

    @Given("[pet-store] prepare order data with parameter {int}")
    public void petStorePrepareOrderDataWithParameterOrderId(int value) {
        orderData.setOrderId(value);
    }

    @Then("[pet-store] send request to find order by order id")
    public void petStoreSendRequestToFindOrderByOrderId() {
        actualData = orderController.findPurchaseOrderById(orderData.getOrderId());
    }

    @And("[pet-store] the response body should contain the id {int}")
    public void petStoreTheResponseBodyShouldContainTheIdValue(int expectedOrderId) {
        await()
                .atMost(10, TimeUnit.SECONDS)
                .pollInterval(500, TimeUnit.MILLISECONDS)
                .untilAsserted(() -> {
                    String actualOrderId = actualData.getBody().jsonPath().getString("id");
                    assertThat(actualOrderId)
                            .as("Order Id incorrect")
                            .isEqualTo(String.valueOf(expectedOrderId));
                });

        log.info("Expected order id: {}, Actual order id: {}", expectedOrderId,
                actualData.getBody().jsonPath().getString("id"));
    }

    @And("[pet-store] the response body should contain the {string} {string}")
    public void petStoreTheResponseBodyShouldContainTheFielsAndValue(String field, String value) {
        switch (field) {
            case "message":
                assertThat(actualData.getBody().jsonPath().getString(field).toUpperCase())
                        .as("Message mismatch")
                        .isEqualTo(value);
                break;
            case "type":
                assertThat(actualData.getBody().jsonPath().getString(field).toUpperCase())
                        .as("Type mismatch")
                        .isEqualTo(value);
                break;
            default:
                throw new AssertionError("Field '" + field + "' not defined yet in assertion!");
        }
    }
}
