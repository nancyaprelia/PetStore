package steps;

import controller.PetController;
import data.PetData;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLOutput;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class PetSteps {
    PetData petData = new PetData();
    PetController petController = new PetController();
    Response actualData;

    @Given("[pet-store] prepare pet status data with parameter {string}")
    public void preparePetStatusDataWithParameter(String status) {
        petData.setStatus(status);
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
                .isEmpty();
    }

    @Given("[pet-store] prepare data to get pet by {string}")
    public void petStorePrepareDataToGetPetById(String petId) {
        petData.setId(petId);
    }

    @When("[pet-store] send request to find pets by id")
    public void petStoreSendRequestToFinePetsByStatus() {
        actualData = petController.findPetById(petData.getId());
    }


    @And("[pet-store] validate the status should be {string}")
    public void petStoreValidateTheStatusShouldBeStatus(String status) {
        String findPetStatus = actualData.getBody().jsonPath().getString("status");
        assertThat(findPetStatus.equals(status));
    }

    @Given("[pet-store] prepare add data pet to the pet store")
    public void petStorePrepareAddDataPetToThePetStore() {
        System.out.println("starting creating pets");
    }

    @Then("[pet-store] set data {string} with value {string}")
    public void petStoreSetDataIdWithValueId(String field, String value) {
        PetData.Category category = petData.getCategory();
        PetData.Tag tag = petData.getTag();
        switch (field) {
            case "id":
                petData.setId(value);
                break;
            case "categoryId":
                category.setId(value);
                break;
            case "categoryName":
                category.setName(value);
                break;
            case "name":
                petData.setName(value);
                break;
            case "status":
                petData.setStatus(value);
                break;
            case "tagId":
                tag.setId(value);
                break;
            case "tagName":
                tag.setName(value);
                break;
            default:
                break;
        }
    }


    @When("[pet-store] send request to create new pets")
    public void petStoreSendRequestToCreateNewPets() {
        actualData = petController.createNewPet(petData);
    }

    @And("[pet-store] validate pets created")
    public void petStoreValidatePetsCreated() {
        String responseId = actualData.jsonPath().getString("id");
        assertThat(responseId).isEqualTo(petData.getId());
    }
}
