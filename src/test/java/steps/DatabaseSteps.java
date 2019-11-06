package steps;

import database.dao.TestBean;
import database.model.Customer;
import database.service.ShopService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;


public class DatabaseSteps {

    @Autowired
    private TestBean testBean;

    @Autowired
    private ShopService shopService;

    @When("I check test bean")
    public void iCheckTestBean() {
        System.out.println("I check the test bean...");
        System.out.println(testBean.getMessage());
    }
    @Given("I insert a customer into the database")
    public void iInsertACustomerIntoTheDatabase() {
        Customer theCustomer = new Customer();

        theCustomer.setFirstName("Anita");
        theCustomer.setLastName("Random");
        theCustomer.setEmail("random2@email.com");

        int response = shopService.addCustomer(theCustomer);

        Assert.assertEquals("Customer insertion error",1, response);
    }

    @Then("I test that the customer is in the database")
    public void iTestThatTheCustomerIsInTheDatabase() {
        System.out.println(shopService.getAllCustomers());
    }

    @When("I insert the same customer in the database")
    public void iInsertTheSameCustomerInTheDatabase() {
    }

    @Then("I get an error")
    public void iGetAnError() {
    }

    @When("I insert a different customer")
    public void iInsertADifferentCustomer() {
    }

    @And("I change the email")
    public void iChangeTheEmail() {
    }

    @Then("I check to see if the email was changed")
    public void iCheckToSeeIfTheEmailWasChanged() {
    }

    @Then("I delete the first user using the id")
    public void iDeleteTheFirstUserUsingTheId() {
    }

    @And("I delete the second user using the email")
    public void iDeleteTheSecondUserUsingTheEmail() {
    }
}
