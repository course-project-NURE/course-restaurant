package com.restaurant.course.controller;

import com.restaurant.course.dto.ResponseStaff;
import com.restaurant.course.dto.SaveStaff;
import com.restaurant.course.entity.en.Role;
import com.restaurant.course.exception.EmailValidationException;
import com.restaurant.course.service.StaffService;
import com.restaurant.course.util.EmailValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/staff")
public class StaffController {

    private final StaffService staffService;


    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseStaff getStaffById(@PathVariable Integer id){
        return staffService.getStaffById(id);
    }

    @GetMapping("/email/{email}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseStaff getStaffByEmail(@PathVariable String email){
        if(EmailValidator.validate(email)){
            return staffService.getStaffByEmail(email);
        }
        else{
            throw EmailValidationException.invalidEmail(email);
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<ResponseStaff> getAllStaff(){
        return staffService.getAllStaff();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseStaff saveStaff(@RequestBody SaveStaff staff){
        if(EmailValidator.validate(staff.getEmail())){
            return staffService.saveStaff(staff);        }
        else{
            throw EmailValidationException.invalidEmail(staff.getEmail());
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseStaff updateStaff(@PathVariable Integer id, @RequestBody SaveStaff staff){
        if(EmailValidator.validate(staff.getEmail())){
            return staffService.updateStaff(id, staff);       }
        else{
            throw EmailValidationException.invalidEmail(staff.getEmail());
        }
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteStaffById(@PathVariable Integer id){
        staffService.deleteStaffById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteStaffByEmail(@PathVariable String email){
        if(EmailValidator.validate(email)){
            staffService.deleteStaffByEmail(email);
            return ResponseEntity.noContent().build();       }
        else{
            throw EmailValidationException.invalidEmail(email);
        }
    }

    @GetMapping("/role/{role}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ResponseStaff> getStaffByRole(@PathVariable String role){
        return staffService.getStaffByRole(Role.valueOf(role));
    }


}
