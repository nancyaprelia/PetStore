package controller;

import config.ApiConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OrderController {
    public Response findPurchaseOrderById(int orderId) {
        Response response = RestAssured
                .given()
                .baseUri(ApiConfig.BASE_URL)
                .queryParam("orderId", orderId)
                .get("/store/order/" + orderId);
        response.getBody().prettyPrint();
        return response;
    }
}
