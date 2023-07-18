package com.niit.bej.proxy;

import com.niit.bej.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="userLogin",url="localhost:8081")
public interface UserProxy {
    @PostMapping("/userAuth/registerUser")
     ResponseEntity<?> registerUser(@RequestBody User user);
}
