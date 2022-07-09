package com.restaurant.course.service;

import com.restaurant.course.dto.ResponseCustomer;
import com.restaurant.course.dto.SaveCustomer;
import com.restaurant.course.entity.Customer;
import com.restaurant.course.entity.LoginInfo;
import com.restaurant.course.entity.Role;
import com.restaurant.course.entity.RoleEntity;
import com.restaurant.course.exception.CustomerException;
import com.restaurant.course.exception.RoleException;
import com.restaurant.course.repository.CustomerRepository;
import com.restaurant.course.repository.LoginInfoRepository;
import com.restaurant.course.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;
    private final LoginInfoRepository loginInfoRepository;

    public CustomerService(
            CustomerRepository customerRepository,
            RoleRepository roleRepository,
            LoginInfoRepository loginInfoRepository
    ) {
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
        this.loginInfoRepository = loginInfoRepository;
    }

    public ResponseCustomer getCustomerById(Integer id){
        Optional<Customer> notNull = customerRepository.findById(id);
        Customer customer = notNull.orElseThrow(() ->
                CustomerException.customerNotFoundById(id)
        );

        return new ResponseCustomer(customer);
    }

    public ResponseCustomer getCustomerByEmail(String email){
        Optional<Customer> notNull = customerRepository.findByEmail(email);
        Customer customer = notNull.orElseThrow(() ->
                CustomerException.customerNotFoundByEmail(email)
        );

        return new ResponseCustomer(customer);
    }

    public List<ResponseCustomer> getAllCustomers() throws ResponseStatusException {
        List<Customer> customers = customerRepository.findAll();
        if(customers.isEmpty()){
            throw CustomerException.NoOneCustomerInDb();
        }

        List<ResponseCustomer> responseCustomers = new ArrayList<>();
        for(Customer c: customers){
            responseCustomers.add(new ResponseCustomer(c));
        }
        return responseCustomers;
    }

    public ResponseCustomer saveCustomer(SaveCustomer saveCustomer){
        Customer customer = saveCustomer.toCustomer();

        LoginInfo loginInfo = new LoginInfo(saveCustomer.getEmail(),saveCustomer.getPassword());

        RoleEntity role = roleRepository.findByTitle(Role.CUSTOMER).orElseThrow(
                () -> RoleException.roleNotFoundByTitle(Role.CUSTOMER.name())
        );

        loginInfo.setRole(role);
        customer.setLoginInfo(loginInfo);

        loginInfoRepository.save(loginInfo);
        customerRepository.save(customer);

        return new ResponseCustomer(customer);
    }

    public ResponseCustomer updateCustomer(Integer id, SaveCustomer saveCustomer){

        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                CustomerException.customerNotFoundById(id)
        );

        customer.setName(saveCustomer.getName());
        customer.setSurname(saveCustomer.getSurname());
        customer.setLastname(saveCustomer.getLastname());
        customer.setPhone(saveCustomer.getPhone());

        LoginInfo loginInfo = customer.getLoginInfo();
        loginInfo.setEmail(saveCustomer.getEmail());
        loginInfo.setPassword(saveCustomer.getPassword());


        loginInfoRepository.save(loginInfo);
        customerRepository.save(customer);

        return new ResponseCustomer(customer);
    }

    @Transactional
    public void deleteCustomerById(Integer id){
        Optional<Customer> staff = customerRepository.findById(id);
        if(staff.isPresent()){
            customerRepository.deleteById(id);
            loginInfoRepository.deleteById(staff.get().getLoginInfo().getId());
        }else{
            throw CustomerException.customerNotFoundById(id);
        }
    }

    @Transactional
    public void deleteCustomerByEmail(String email){
        if(customerRepository.findByEmail(email).isPresent()){
            customerRepository.deleteByEmail(email);
            loginInfoRepository.deleteByEmail(email);
        }else{
            throw CustomerException.customerNotFoundByEmail(email);
        }
    }
}
