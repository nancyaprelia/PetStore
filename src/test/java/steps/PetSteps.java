package steps;

import controller.PetController;
import data.PetData;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class PetSteps {
    PetData petData = new PetData();
    PetController petController = new PetController();
    Response actualData;

    @Given("[pet-store] prepare pet status data with parameter {string}")
    public void preparePetStatusDataWithParameter(String status) {
        petData.setStatus(new String[]{status});
    }

    @When("[pet-store] send request to find pets by status")
    public void petStoreSendRequestToFindPetsByStatus() {
        actualData = petController.findsPetsByStatus(petData.getStatus());
    }

    @And("[pet-store] the response should contain pets with status {string}")
    public void petStoreTheResponseShouldContainPetsWithStatusStatus(String expectedStatus) {
        List<String> actualStatuses = actualData.getBody()
                .jsonPath()
                .getList("status", String.class);

        assertThat(actualStatuses)
                .as("Some pets do not have the expected status")
                .allMatch(status -> status.equals(expectedStatus));

        log.info("Expected status: {}", expectedStatus);
        log.info("Actual statuses: {}", actualStatuses);
    }

    @And("[pet-store] the response should be empty")
    public void petStoreTheResponseShouldBeEmpty() {
        List<?> list = actualData.getBody().jsonPath().getList("$");
        assertThat(list)
                .as("The response should be empty")
                .isEmpty();    }
}
