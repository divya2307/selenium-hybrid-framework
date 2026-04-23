package stepdefinitions;

import org.testng.Assert;

import browserFactory.BrowserFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class LoginSteps {

    LoginPage loginPage;

    @Given("user is on login page")
    public void user_is_on_login_page() {
        loginPage = new LoginPage(BrowserFactory.getDriverInstance());
    }

    @When("user logs in with username {string} and password {string}")
    public void user_logs_in_with_username_and_password(String username, String password) {
        loginPage.loginToApplication(username, password);
    }

    @Then("user should see the home page")
    public void user_should_see_the_home_page() {
        Assert.assertEquals(loginPage.textValidate(), "iNeuron Courses");
    }
}
