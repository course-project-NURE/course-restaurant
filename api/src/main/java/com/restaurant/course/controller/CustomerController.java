package com.restaurant.course.controller;

import com.restaurant.course.dto.ResponseAddress;
import com.restaurant.course.dto.ResponseCustomer;
import com.restaurant.course.dto.SaveAddress;
import com.restaurant.course.dto.SaveCustomer;
import com.restaurant.course.exception.EmailValidationException;
import com.restaurant.course.service.CustomerService;
import com.restaurant.course.util.EmailValidatorUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseCustomer getCustomerById(@PathVariable Integer id){
        return customerService.getCustomerById(id);
    }

    @GetMapping("/email/{email}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseCustomer getCustomerByEmail(@PathVariable String email){
        if(EmailValidatorUtil.validate(email)){
            return customerService.getCustomerByEmail(email);
        }
        else{
            throw EmailValidationException.invalidEmail(email);
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<ResponseCustomer> getAllCustomer(){
        return customerService.getAllCustomers();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseCustomer saveCustomer(@RequestBody SaveCustomer saveCustomer){
        if(EmailValidatorUtil.validate(saveCustomer.getEmail())){
            return customerService.saveCustomer(saveCustomer);
        }
        else{
            throw EmailValidationException.invalidEmail(saveCustomer.getEmail());
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseCustomer updateCustomer(@PathVariable Integer id, @RequestBody SaveCustomer customer){
        if(EmailValidatorUtil.validate(customer.getEmail())){
            return customerService.updateCustomer(id, customer);
        }
        else{
            throw EmailValidationException.invalidEmail(customer.getEmail());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Integer id){
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteCustomerByEmail(@PathVariable String email){
        if(EmailValidatorUtil.validate(email)){
            customerService.deleteCustomerByEmail(email);
            return ResponseEntity.noContent().build();
        }
        else{
            throw EmailValidationException.invalidEmail(email);
        }
    }

    @PostMapping("/add-address/email/{email}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseAddress addNewAddress(@PathVariable String email, @RequestBody SaveAddress saveAddress){
        return customerService.addNewAddress(email, saveAddress);

    }

    @PostMapping("/sendpromo/{email}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> sendPromo(@PathVariable String email){
        customerService.sendPromoToEmail(email);
        return ResponseEntity.noContent().build();
    }

}
