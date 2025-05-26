package com.example.passfashion.controller;

import com.example.passfashion.dto.Request.AddressUpdateRequest;
import com.example.passfashion.dto.Request.UserUpdateRequest;
import com.example.passfashion.dto.Response.AddressResponse;
import com.example.passfashion.dto.Response.UserResponse;
import com.example.passfashion.model.Address;
import com.example.passfashion.model.User;
import com.example.passfashion.repository.AddressRepository;
import com.example.passfashion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with id: " + id);
        }
        User user = userOptional.get();
        return new UserResponse(user.getName(), user.getEmail(), user.getBirthday(),user.getPhone(),user.getImage().getUrl());
    }

    @PutMapping("/{id}")
    public boolean updateUserById(@PathVariable long id, @RequestBody UserUpdateRequest request) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with id: " + id);
        }
        User user = userOptional.get();
        user.setName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setBirthday(request.getBirthday());
        user.setPhone(request.getPhone());

        // Save và return true nếu không lỗi
        userRepository.save(user);
        return true;
    }

    @GetMapping("/address-list/{userId}")
    public  List<AddressResponse> getUserAddress(@PathVariable long userId) {
        List<Address> list = addressRepository.getAddressesByUserId(userId);
        List<AddressResponse> response = new ArrayList<>();
        for (Address address : list) {
            response.add(new AddressResponse(address.getId(),address.getProvince(),address.getDistrict(), address.getWard(),address.getDetail(),address.getPhone()));
        }
        return response;
    }

    @PutMapping("/address-update")
    public boolean updateUserAddress(@RequestBody AddressUpdateRequest request) {
        Optional<Address> addressOptional = addressRepository.findById(request.getId());
        if (addressOptional.isEmpty()) {
            throw new RuntimeException("Address not found with id: " + request.getId());
        }
        Address address = addressOptional.get();
        address.setProvince(request.getProvince());
        address.setDistrict(request.getDistrict());
        address.setWard(request.getWard());
        address.setDetail(request.getDetail());
        address.setPhone(request.getPhone());

        // Save và return true nếu không lỗi
        addressRepository.save(address);
        return true;
    }
}
