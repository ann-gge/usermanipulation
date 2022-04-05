package com.example.userdata.service;

import com.example.userdata.data.UserData;
import com.example.userdata.dto.UserDataResponseDTO;
import com.example.userdata.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class UserDataService implements IUserDataService{

    private static final Logger LOGGER= LogManager.getLogger(UserDataService.class);
    @Autowired
    UserDataRepository userDataRepository;

    @Override
    public void saveUserData(List<UserData> userDataList) {
        try{
            if(!userDataList.isEmpty()) {
               userDataList.forEach(u -> userDataRepository.save(u));
                LOGGER.info("Data saved successfully into the database");
            }
            else
                LOGGER.info("Empty list from the request");
        }
        catch (Exception e)
        {
        LOGGER.error("Exception occurred while saving the entity ",e);
        throw e;
        }
    }

    @Override
    public UserDataResponseDTO fetchUserData(int minValue, int maxValue) {
        LOGGER.info("In service method - fetchUserData");
        List<UserData> userDataList;
        List<String> usersInRange;
        Long characterCount= 0L;
        UserDataResponseDTO userDataResponseDTO=new UserDataResponseDTO();
        ValueRange range =ValueRange.of(minValue, maxValue);
        try{
            userDataList=userDataRepository.findAll();
            if(!userDataList.isEmpty()){
                usersInRange= userDataList.stream()
                        .filter(userData -> range.isValidIntValue(userData.getPostcode()))
                        .map(UserData::getName).sorted()
                        .collect(Collectors.toList());

                for(String name:usersInRange){
                    characterCount+=name.chars().count();
                }
                userDataResponseDTO.setUserList(usersInRange);
                userDataResponseDTO.setTotalCharacters(characterCount);
            }
            else{
                LOGGER.info("No users found in the DB");
            }
        }
        catch (Exception e)
        {
            LOGGER.error("Exception occurred while fetching the entity ",e);
            throw e;
        }
        LOGGER.info("After service method implementation");
        return userDataResponseDTO;
    }
}
