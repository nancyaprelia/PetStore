package controller;

import config.ApiConfig;
import data.PetData;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.stereotype.Component;

@Component
public class PetController {
    public Response findsPetsByStatus(String status) {
        Response response = RestAssured
                .given()
                .baseUri(ApiConfig.BASE_URL)
                .queryParam("status", status)
                .get("/pet/findByStatus");
        response.getBody().prettyPrint();
        return response;
    }

    public Response findPetById(String petId) {
        Response response = RestAssured
                .given()
                .baseUri(ApiConfig.BASE_URL)
                .queryParam("petId", petId)
                .get("/pet/"+petId);
        response.getBody().prettyPrint();
        return response;
    }

    public Response createNewPet(PetData petData) {
        Response response = RestAssured
                .given()
                .baseUri(ApiConfig.BASE_URL)
                .basePath("/pet")
                .contentType("application/json")
                .accept("application/json")
                .body(petData)
                .log().body()
                .post();
        response.getBody().prettyPrint();
        return response;
    }
}
