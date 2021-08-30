package com.example.linkpreview.service;

import com.example.linkpreview.Entity.Userd;
import com.example.linkpreview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserdserviceImpl implements UserdService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(Userd userd) {
                userRepository.save(userd);
    }

    @Override
    public Userd getUserd(String userName) {
        return userRepository.findById(userName).get();
    }
}
