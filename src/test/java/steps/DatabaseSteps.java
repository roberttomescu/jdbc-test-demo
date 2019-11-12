package steps;

import database.backpack.TestBackPack;
import database.dao.TestBean;
import database.model.Customer;
import database.service.ShopService;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class DatabaseSteps extends TestRunner{

    @Autowired
    private TestBean testBean;

    @Autowired
    private TestBackPack testBackPack;

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

        String firstName = "Anita";
        String lastName = "Random";
        String email = "random@email.com";

        theCustomer.setFirstName(firstName);
        theCustomer.setLastName(lastName);
        theCustomer.setEmail(email);

        testBackPack.setEmail(email);

        int response = shopService.addCustomer(theCustomer);

        Assert.assertEquals("Customer insertion error",1, response);
    }

    @Then("I test that the customer is in the database")
    public void iTestThatTheCustomerIsInTheDatabase() {
        List<Customer> Customers = shopService.getCustomerWithEmail(testBackPack.getEmail());
        Assert.assertEquals("No or multiple objects retrieved ", Customers.size(), 1);
        Customer theCustomer = Customers.get(0);
        Assert.assertEquals("Email is different", testBackPack.getEmail(), theCustomer.getEmail());
    }

    @When("I insert the same customer in the database and get an error")
    public void iInsertTheSameCustomerInTheDatabaseAndGetAnError() {
        Customer theCustomer = new Customer();

        String firstName = "Anita";
        String lastName = "Random";
        String email = "random@email.com";

        theCustomer.setFirstName(firstName);
        theCustomer.setLastName(lastName);
        theCustomer.setEmail(email);

        int response = shopService.addCustomer(theCustomer);

        Assert.assertEquals("Customer insertion was successful",0, response);
    }

    @When("I insert a different customer")
    public void iInsertADifferentCustomer() {

        Customer theCustomer = new Customer();

        String firstName = "Bob";
        String lastName = "Builder";
        String email = "builder@email.com";

        theCustomer.setFirstName(firstName);
        theCustomer.setLastName(lastName);
        theCustomer.setEmail(email);

        testBackPack.setEmail(email);

        int response = shopService.addCustomer(theCustomer);

        Assert.assertEquals("Customer insertion error",1, response);
    }

    @And("I change the email")
    public void iChangeTheEmail() {

        List<Customer> Customers = shopService.getCustomerWithEmail(testBackPack.getEmail());
        Assert.assertEquals("No or multiple objects retrieved ", Customers.size(), 1);
        Customer theCustomer = Customers.get(0);

        String email = "bob.builder@email.com";

        theCustomer.setEmail(email);

        testBackPack.setEmail(email);
        testBackPack.setId(theCustomer.getId());

        int response = shopService.updateCustomer(theCustomer);

        Assert.assertEquals("Customer update error",1, response);
    }

    @Then("I check to see if the email was changed")
    public void iCheckToSeeIfTheEmailWasChanged() {
        List<Customer> Customers = shopService.getCustomerWithId(testBackPack.getId());
        Assert.assertEquals("No or multiple objects retrieved ", Customers.size(), 1);
        Customer theCustomer = Customers.get(0);
        Assert.assertEquals("Email is different", testBackPack.getEmail(), theCustomer.getEmail());
    }

    @After
    public void iDeleteTheFirstUserUsingTheId() {
        try {
            Customer theCustomer = shopService.getCustomerWithEmail("random@email.com").get(0);
            int response = shopService.deleteCustomerWithId(theCustomer.getId());
            Assert.assertEquals("Error deleting with id", 1, response);
        } catch(Exception e) {
            System.out.println("Error deleting: " + e);
        }

    }

    @After
    public void iDeleteTheSecondUserUsingTheEmail() {
        try {
            Customer theCustomer = shopService.getCustomerWithEmail(testBackPack.getEmail()).get(0);
            int response = shopService.deleteCustomerWithEmail(theCustomer.getEmail());
            Assert.assertEquals("Error deleting with email", 1, response);
        }
        catch(Exception e) {
            System.out.println("Error deleting: " + e);
        }
    }

}
