package database.service;

import database.dao.CustomerDAO;
import database.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ShopServiceImpl implements ShopService{

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public int addCustomer(Customer theCustomer) {
        return customerDAO.saveCustomer(theCustomer);
    }

    @Override
    public int updateCustomer(Customer theCustomer) {
        return customerDAO.updateCustomer(theCustomer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDAO.getCustomers();
    }

    @Override
    public List<Customer> getCustomerWithEmail(String email) {
        return customerDAO.getCustomerWithEmail(email);
    }

    @Override
    public List<Customer> getCustomerWithId(int id) {
        return customerDAO.getCustomerWithId(id);
    }

    @Override
    public int deleteCustomerWithEmail(String email) {
        return customerDAO.removeCustomerWithEmail(email);
    }

    @Override
    public int deleteCustomerWithId(int id) {
        return customerDAO.removeCustomerWithId(id);
    }
}
