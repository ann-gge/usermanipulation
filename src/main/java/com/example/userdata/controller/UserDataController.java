package com.example.userdata.controller;


import com.example.userdata.data.UserData;
import com.example.userdata.dto.UserDataResponseDTO;
import com.example.userdata.service.IUserDataService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class UserDataController {
    private static final Logger LOGGER= LogManager.getLogger(UserDataController.class);
    @Autowired
    IUserDataService userDataService;

    @PostMapping("users")
    public ResponseEntity<String> saveData(@RequestBody List<UserData> userDataList){
        LOGGER.info("In controller method for saving the entity");
        try{
        userDataService.saveUserData(userDataList);
        return new ResponseEntity<>("Data saved successfully into the database",HttpStatus.OK);
        }
        catch(Exception e){
        return new ResponseEntity<>("Exception while trying to save data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("userdata")
    public ResponseEntity<UserDataResponseDTO> getUserData(@RequestParam int minValue, @RequestParam int maxValue){
        LOGGER.info("In controller method for fetching users in the specified range");
        try{
        UserDataResponseDTO userDataList=userDataService.fetchUserData(minValue,maxValue);
        return new ResponseEntity<>(userDataList,new HttpHeaders(), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(null,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
