package controller;

import config.ApiConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.stereotype.Component;

@Component
public class PetController {
    public Response findsPetsByStatus(String[] status) {
        Response response = RestAssured
                .given()
                .baseUri(ApiConfig.BASE_URL)
                .queryParam("status", (Object[]) status)
                .get("/pet/findByStatus");
        response.getBody().prettyPrint();
        return response;
    }

}
