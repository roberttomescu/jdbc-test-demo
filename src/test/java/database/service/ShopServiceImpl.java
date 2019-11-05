package database.service;

import database.dao.CustomerDAO;
import database.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService{

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    @Transactional
    public void addCustomer(Customer theCustomer) {
        customerDAO.saveCustomer(theCustomer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return null;
    }
}
