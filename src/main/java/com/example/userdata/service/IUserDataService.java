package com.example.userdata.service;

import com.example.userdata.data.UserData;
import com.example.userdata.dto.UserDataResponseDTO;

import java.util.List;

public interface IUserDataService {
    void saveUserData(List<UserData> userDataList);
    UserDataResponseDTO fetchUserData(int minValue, int maxValue);
}
