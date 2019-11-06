package database.dao;

import database.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDAO {

    final String INSERT_QUERY = "insert into customer (first_name, last_name, email) values (?, ?, ?)";
    final String UPDATE_QUERY = "update customer set first_name = ?, last_name = ?, email = ? where id = ?";
    final String DELETE_QUERY_ID = "delete from customer where id = ?";
    final String DELETE_QUERY_EMAIL = "delete from customer where email = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Customer> getCustomers() {
        return jdbcTemplate.query("select * from customer", BeanPropertyRowMapper.newInstance(Customer.class));
    }

    @Override
    public List<Customer> getCustomerWithEmail(String email) {
        return jdbcTemplate.query("select * from customer where email = " + email, BeanPropertyRowMapper.newInstance(Customer.class));
    }

    @Override
    public int removeCustomerWithEmail(String email) {
        return jdbcTemplate.update(DELETE_QUERY_EMAIL, email);
    }

    @Override
    public List<Customer> getCustomerWithId(int id) {
        return jdbcTemplate.query("select * from customer where id = " + id, BeanPropertyRowMapper.newInstance(Customer.class));
    }

    @Override
    public int removeCustomerWithId(int id) {
        return jdbcTemplate.update(DELETE_QUERY_ID, id);
    }

    @Override
    public int saveCustomer(Customer theCustomer) {

        try {
            return jdbcTemplate.update(INSERT_QUERY, theCustomer.getFirstName(),
                                                    theCustomer.getLastName(),
                                                    theCustomer.getEmail());
        }
        catch (Exception exc) {
            System.out.println("Error inserting: " + exc);
            return 0;
        }
    }

    @Override
    public int updateCustomer(Customer theCustomer) {
        try {
            return jdbcTemplate.update(UPDATE_QUERY, theCustomer.getFirstName(),
                    theCustomer.getLastName(),
                    theCustomer.getEmail(),
                    theCustomer.getId());
        } catch(Exception exc) {
            System.out.println("Error updating: " + exc);
            return 0;
        }
    }
}
