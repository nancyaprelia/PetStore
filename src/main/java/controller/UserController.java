package controller;

import config.ApiConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserController {
    public Response getUserByUsername(String username) {
        Response response = RestAssured
                .given()
                .baseUri(ApiConfig.BASE_URL)
                .queryParam("username", username)
                .get("/user/" + username);

        response.getBody().prettyPrint();
        return response;
    }

    public Response loginUserIntoSystem(String username, String password) {
        Response response = RestAssured
                .given()
                .baseUri(ApiConfig.BASE_URL)
                .queryParam("username", username)
                .queryParam("password", password)
                .get("/user/login");
        response.getBody().prettyPrint();
        return response;
    }

    public Response logoutInUserSession() {
        Response response = RestAssured
                .given()
                .baseUri(ApiConfig.BASE_URL)
                .get("/user/logout");
        response.getBody().prettyPrint();
        return response;
    }

}
