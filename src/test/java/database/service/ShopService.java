package database.service;

import database.model.Customer;

import java.util.List;

public interface ShopService {

    public void addCustomer(Customer theCustomer);

    public List<Customer> getAllCustomers();
}
