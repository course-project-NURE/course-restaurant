package com.restaurant.course.service;

import com.restaurant.course.dto.ResponseStaff;
import com.restaurant.course.dto.SaveStaff;
import com.restaurant.course.entity.LoginInfo;
import com.restaurant.course.entity.RoleEntity;
import com.restaurant.course.entity.Staff;
import com.restaurant.course.exception.RoleException;
import com.restaurant.course.exception.StaffException;
import com.restaurant.course.repository.LoginInfoRepository;
import com.restaurant.course.repository.RoleRepository;
import com.restaurant.course.repository.StaffRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
public class StaffService {
    private final StaffRepository staffRepository;
    private final RoleRepository roleRepository;
    private final LoginInfoRepository loginInfoRepository;

    public StaffService(
            StaffRepository staffRepository,
            RoleRepository roleRepository,
            LoginInfoRepository loginInfoRepository
    ) {
        this.staffRepository = staffRepository;
        this.roleRepository = roleRepository;

        this.loginInfoRepository = loginInfoRepository;
    }


    public ResponseStaff getStaffById(Integer id){
        Optional<Staff> notNull = staffRepository.findById(id);
        Staff staff = notNull.orElseThrow(() ->
                StaffException.staffNotFoundById(id)
        );

        return new ResponseStaff(staff);
    }

    public ResponseStaff getStaffByEmail(String email){
        Optional<Staff> notNull = staffRepository.findByEmail(email);
        Staff staff = notNull.orElseThrow(() ->
                StaffException.staffNotFoundByEmail(email)
        );

        return new ResponseStaff(staff);
    }

    public List<ResponseStaff> getAllStaff(){
        List<Staff> staff = staffRepository.findAll();
        if(staff.isEmpty()){
            throw StaffException.NoOneStaffInDb();
        }

        List<ResponseStaff> responseStaff = new ArrayList<>();
        for(Staff s: staff){
            responseStaff.add(new ResponseStaff(s));
        }
        return responseStaff;
    }


    public ResponseStaff saveStaff(SaveStaff saveStaff){
        Staff staff = saveStaff.toStaff();

        LoginInfo loginInfo = new LoginInfo(saveStaff.getEmail(),saveStaff.getPassword());

        RoleEntity role = roleRepository.findByTitle(saveStaff.getRole()).orElseThrow(
                () -> RoleException.roleNotFoundByTitle(saveStaff.getRole().name())
        );

        loginInfo.setRole(role);
        staff.setLoginInfo(loginInfo);

        loginInfoRepository.save(loginInfo);
        staffRepository.save(staff);

        return new ResponseStaff(staff);
    }

    public ResponseStaff updateStaff(Integer id, SaveStaff saveStaff){

        Staff staff = staffRepository.findById(id).orElseThrow(() ->
                StaffException.staffNotFoundById(id)
        );

        staff.setName(saveStaff.getName());
        staff.setSurname(saveStaff.getSurname());
        staff.setLastname(saveStaff.getLastname());
        staff.setPhone(saveStaff.getPhone());
        staff.setSalary(saveStaff.getSalary());

        LoginInfo loginInfo = staff.getLoginInfo();
        loginInfo.setEmail(saveStaff.getEmail());
        loginInfo.setPassword(saveStaff.getPassword());

        RoleEntity role = roleRepository.findByTitle(saveStaff.getRole()).orElseThrow(
                () -> RoleException.roleNotFoundByTitle(saveStaff.getRole().name())
        );

        loginInfo.setRole(role);
        loginInfoRepository.save(loginInfo);
        staffRepository.save(staff);

        return new ResponseStaff(staff);
    }

    @Transactional
    public void deleteStaffById(Integer id){
        Optional<Staff> staff = staffRepository.findById(id);
        if(staff.isPresent()){
            staffRepository.deleteById(id);
            loginInfoRepository.deleteById(staff.get().getLoginInfo().getId());
        }else{
            throw StaffException.staffNotFoundById(id);
        }
    }

    @Transactional
    public void deleteStaffByEmail(String email){
        if(staffRepository.findByEmail(email).isPresent()){
            staffRepository.deleteByEmail(email);
            loginInfoRepository.deleteByEmail(email);
        }else{
            throw StaffException.staffNotFoundByEmail(email);
        }
    }
}
