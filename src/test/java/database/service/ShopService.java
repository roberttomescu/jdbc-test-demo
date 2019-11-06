package database.service;

import database.model.Customer;

import java.util.List;

public interface ShopService {

    public int addCustomer(Customer theCustomer);

    public int updateCustomer(Customer theCustomer);

    public List<Customer> getAllCustomers();

    public List<Customer> getCustomerWithEmail(String email);

    public List<Customer> getCustomerWithId(int id);

    public int deleteCustomerWithEmail(String email);

    public int deleteCustomerWithId(int id);
}
