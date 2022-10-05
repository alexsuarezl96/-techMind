package com.techmind.project_enterprise.service.Impl;

import com.techmind.project_enterprise.model.User;
import com.techmind.project_enterprise.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class UserService {
    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    public User createUser(User newUser) {

        return this.repository.save(newUser);
    }
public User findUserByEmail(String email){
       return this.repository.findByEmail(email);
}
    public User getOrCreateUser(Map<String, Object> userData) {
        String email = (String) userData.get("email");
        User user = findUserByEmail(email);
        if (user == null) {
            String name = (String) userData.get("nickname");
            String image = (String) userData.get("picture");
            String authid = (String) userData.get("sub");

            User newUser = new User(email = email, image = image, authid = authid);
            return createUser(newUser);
        }

        return user;
    }
}
