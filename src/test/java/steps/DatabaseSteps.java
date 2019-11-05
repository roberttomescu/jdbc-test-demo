package steps;

import database.dao.TestBean;
import database.model.Customer;
import database.service.ShopService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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

        //theCustomer.setId(2);
        theCustomer.setFirstName("Anita");
        theCustomer.setLastName("Random");
        theCustomer.setEmail("random@email.com");

        shopService.addCustomer(theCustomer);
    }

    @Then("I test that the customer is in the database")
    public void iTestThatTheCustomerIsInTheDatabase() {
    }
}
