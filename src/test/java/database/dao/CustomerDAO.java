package database.dao;

import database.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDAO {

    List<Customer> getCustomers();

    int saveCustomer(Customer theCustomer);

    int updateCustomer(Customer theCustomer);

    List<Customer> getCustomerWithEmail(String email);

    int removeCustomerWithEmail(String email);

    List<Customer> getCustomerWithId(int id);

    int removeCustomerWithId(int id);

}
