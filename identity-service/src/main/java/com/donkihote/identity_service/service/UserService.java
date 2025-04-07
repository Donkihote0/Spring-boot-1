package com.donkihote.identity_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donkihote.identity_service.controller.UserController;
import com.donkihote.identity_service.dto.request.UserCreationRequest;
import com.donkihote.identity_service.dto.request.UserUpdateRequest;
import com.donkihote.identity_service.entity.User;
import com.donkihote.identity_service.exception.AppException;
import com.donkihote.identity_service.exception.ErrorCode;
import com.donkihote.identity_service.repository.UserRepository;

@Service
public class UserService {

    
    @Autowired
    private UserRepository userRepository;

    

    public User createUser(UserCreationRequest request){
        User user = new User();

        if (userRepository.existsByUserName(request.getUserName())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }


        user.setUserName(request.getUserName());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());
        return userRepository.save(user);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUser(String id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    public User updateUser(String userId, UserUpdateRequest request){
        User user = getUser(userId);
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }
}
