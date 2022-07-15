package com.restaurant.course.controller;

import com.restaurant.course.dto.ResponseAddress;
import com.restaurant.course.dto.ResponseCustomer;
import com.restaurant.course.dto.SaveAddress;
import com.restaurant.course.dto.SaveCustomer;
import com.restaurant.course.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
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
        return customerService.getCustomerByEmail(email);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<ResponseCustomer> getAllCustomer(){
        return customerService.getAllCustomers();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseCustomer saveCustomer(@RequestBody SaveCustomer saveCustomer){
        return customerService.saveCustomer(saveCustomer);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseCustomer updateCustomer(@PathVariable Integer id, @RequestBody SaveCustomer customer){
        return customerService.updateCustomer(id, customer);
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
        customerService.deleteCustomerByEmail(email);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/add-address/email/{email}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseAddress addNewAddress(@PathVariable String email, @RequestBody SaveAddress saveAddress){
        return customerService.addNewAddress(email, saveAddress);
    }
}
