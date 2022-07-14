package com.restaurant.course.service;

import com.restaurant.course.dto.ResponseAddress;
import com.restaurant.course.dto.ResponseCustomer;
import com.restaurant.course.dto.SaveAddress;
import com.restaurant.course.dto.SaveCustomer;
import com.restaurant.course.entity.*;
import com.restaurant.course.entity.en.Role;
import com.restaurant.course.exception.CustomerException;
import com.restaurant.course.exception.RoleException;
import com.restaurant.course.repository.*;
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

    private final AddressRepository addressRepository;
    private final CustomerHasAddressRepository customerHasAddressRepository;

    public CustomerService(
            CustomerRepository customerRepository,
            RoleRepository roleRepository,
            LoginInfoRepository loginInfoRepository,
            AddressRepository addressRepository,
            CustomerHasAddressRepository customerHasAddressRepository
    ) {
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
        this.loginInfoRepository = loginInfoRepository;
        this.addressRepository = addressRepository;
        this.customerHasAddressRepository = customerHasAddressRepository;
    }

    public ResponseCustomer getCustomerById(Integer id){
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
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

        return new ResponseCustomer(customerRepository.save(customer));
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

        return new ResponseCustomer(customerRepository.save(customer));
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
    @Transactional
    public ResponseAddress addNewAddress(String email, SaveAddress saveAddress){
        Customer customer = customerRepository.findByEmail(email).orElseThrow(() ->
                CustomerException.customerNotFoundByEmail(email)
        );
        Address address = addressRepository.findAddress(saveAddress.getStreet(), saveAddress.getHouse(), saveAddress.getFlat());
        if(address == null){
            Address newAddress = saveAddress.toAddress();
            CustomerHasAddress hasAddress = new CustomerHasAddress(customer, newAddress, saveAddress.getTitle());
            addressRepository.save(newAddress);
            return new ResponseAddress(customerHasAddressRepository.save(hasAddress));
        }
        else {
            CustomerHasAddress hasAddress = new CustomerHasAddress(customer, address, saveAddress.getTitle());
            return new ResponseAddress(customerHasAddressRepository.save(hasAddress));
        }
    }
}
