package com.happyWatter.codivAlarm.service;


import com.happyWatter.codivAlarm.entity.User;
import com.happyWatter.codivAlarm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TokenService {

    @Autowired
    public UserRepository userRepository;

    public void createToken(String token){
        List<User> rst = userRepository.findByToken(token);
        if(rst.size()  < 1){
            User user = new User();
            user.setToken(token);
            user.setCreateDt(LocalDateTime.now());
            user.setUpdateDt(LocalDateTime.now());

            userRepository.save(user);
        }





    }

    public List<User> selectTokens(){

        return userRepository.findAll();
    }

}
