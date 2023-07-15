package com.example.user.controller;

import com.example.user.entity.Users;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class userController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/usersall")
    public ResponseEntity<List<Users>> getAllUsers(){
        List<Users> userlist = new ArrayList<>();
        try {
            userRepository.findAll().forEach(userlist::add);
            return new ResponseEntity<>(userlist, HttpStatus.OK);
        }
       catch (Exception e){
           System.out.println(e);
           return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }

    }

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getSingleUser(@RequestParam(name = "user") String user){
        List<Users> userlist = new ArrayList<>();
        try {
           userlist.add(userRepository.findByUsername(user));
            return new ResponseEntity<>(userlist, HttpStatus.OK);
        }
        catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/users")
    @ResponseBody
    public ResponseEntity<Users> createUser(@RequestBody Users users) {
        try {
            Users _user = userRepository
                    .save(new Users(users.getEmail(), users.getUsername(),users.getPassword() , false));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<Users>(users, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users")
    @ResponseBody
    public ResponseEntity<Users> updateUserDetails(@RequestBody  Users user) {
        Optional<Users> userPresent = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));
            if(userPresent.isPresent()){
                Users _user = userPresent.get();
                _user.setEmail(user.getEmail());
                _user.setUsername(user.getUsername());
                _user.setPassword(user.getPassword());
                return new ResponseEntity<>(userRepository.save(_user),HttpStatus.OK);
            }
         else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user")
    public ResponseEntity<Users> deleteUser(@RequestParam(name = "email") String email) {
        try {
            userRepository.deleteByEmail(email);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<String> login(@RequestBody Users loginRequest) {
        // Retrieve the user from the database based on the provided email address
        Users user = userRepository.findByEmail(loginRequest.getEmail());

        // Check if the user exists and if the password matches
        if (user == null || !user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        // User authentication successful
        return ResponseEntity.ok("Login successful");
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hi";
    }




}
