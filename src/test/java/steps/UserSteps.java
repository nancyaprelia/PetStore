package steps;

import controller.UserController;
import data.OrderData;
import data.UserData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class UserSteps {
    UserData userData = new UserData();
    OrderData orderData = new OrderData();
    UserController userController = new UserController();
    Response actualData;


    @Given("[pet-store] prepare user data with parameter {string}")
    public void petStorePrepareUserDataWithParameterUsernameValue(String value) {
        userData.setUsername(value);
    }

    @When("[pet-store] send request to find user by username")
    public void petStoreSendRequestToFindUserByUsername() {
        actualData = userController.getUserByUsername(userData.getUsername());
    }

    @Then("[pet-store] the response status code should be {int}")
    public void petStoreTheResponseStatusCodeShouldBe(int expectedStatusCode) {
        int actualStatusCode = actualData.getStatusCode();
        assertEquals(expectedStatusCode, actualStatusCode,
                "Expected status code " + expectedStatusCode + " but got " + actualStatusCode);
    }

    @And("[pet-store] the response body should contain the username {string}")
    public void petStoreTheResponseBodyShouldContainTheUsernameUsernameValue(String expectedUsername) {
        String actualUsername = actualData.getBody().jsonPath().getString("username");

        assertThat(actualUsername)
                .as("Username incorrect")
                .isEqualTo(expectedUsername);

        log.info("Expected username: {}, Actual username: {}", expectedUsername, actualUsername);
    }

    @Given("[pet-store] prepare user data with username {string} and password {string}")
    public void petStorePrepareUserDataWithUsernameAndPasswordTesting(String username, String password) {
        userData.setUsername(username);
        userData.setPassword(password);
    }

    @When("[pet-store] send request to login")
    public void petStoreSendRequestToLogin() {
        actualData = userController.loginUserIntoSystem(userData.getUsername(), userData.getPassword());
    }

    @And("[pet-store] send request to logout")
    public void petStoreSendRequestToLogout() {
        actualData = null;
        actualData = userController.logoutInUserSession();
    }
}
