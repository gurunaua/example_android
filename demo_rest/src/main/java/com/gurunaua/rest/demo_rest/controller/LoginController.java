package com.gurunaua.rest.demo_rest.controller;


import com.gurunaua.rest.demo_rest.model.LoginResponse;
import com.gurunaua.rest.demo_rest.model.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class LoginController {

    @RequestMapping("/login")
    public @ResponseBody LoginResponse doLogin(@RequestBody User user) {
        LoginResponse response = new LoginResponse();
        try {

            if (user==null || user.getUsername()==null || user.getUsername().isEmpty() || user.getPassword()==null || user.getPassword().isEmpty()){
                response.setStatus(LoginResponse.status_failed);
                return response;
            }

            response.setUsername(user.getUsername());

            if(user.getUsername().equals("admin") && user.getPassword().equals("123456")){
                response.setStatus(LoginResponse.status_success);
            }
            else
                response.setStatus(LoginResponse.status_wrong_username_or_pass);
        }catch (Exception e){
            response.setStatus(LoginResponse.status_error);
            e.printStackTrace();
        }
        return response;
    }



}
