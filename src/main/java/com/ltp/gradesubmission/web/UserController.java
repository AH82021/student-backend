package com.ltp.gradesubmission.web;

import com.ltp.gradesubmission.entity.User;
import com.ltp.gradesubmission.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<String> findById(@PathVariable Long id){

        return new ResponseEntity<>(userService.getUser(id).getUsername(),HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
