package com.example.userdata;

import com.example.userdata.data.UserData;
import com.example.userdata.dto.UserDataResponseDTO;
import com.example.userdata.repository.UserDataRepository;
import com.example.userdata.service.UserDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserDataServiceTest {
    @InjectMocks
    @Spy
    UserDataService userDataService;

    @Mock
    UserDataRepository repository;

    @Test
    public void saveUserDataTestSuccess1(){
        List<UserData> userDataList=new ArrayList<>();
        UserData userData1=new UserData(1,"A",123);
        UserData userData2=new UserData(2,"A",123);
        userDataList.add(userData1);
        userDataList.add(userData2);
        userDataService.saveUserData(userDataList);
        verify(userDataService,times(1)).saveUserData(userDataList);

    }
    @Test
    public void saveUserDataTestSuccess2(){
        List<UserData> userDataList=new ArrayList<>();
        userDataService.saveUserData(userDataList);
        verify(userDataService,times(1)).saveUserData(userDataList);

    }
    @Test(expected = Exception.class)
    public void saveUserDataTestFailure(){
        List<UserData> userDataList=new ArrayList<>();
        Mockito.doThrow(Exception.class).when(repository).save(new UserData());
        userDataService.saveUserData(userDataList);
    }
    @Test
    public void fetchUserDataSuccess1(){
        UserDataResponseDTO userDataResponseDTO=userDataService.fetchUserData(10,200);
        assertTrue(userDataResponseDTO instanceof UserDataResponseDTO);
    }
    @Test
    public void fetchUserDataSuccess2(){
        when(repository.findAll()).thenReturn(Collections.emptyList());
        UserDataResponseDTO userDataResponseDTO=userDataService.fetchUserData(Mockito.anyInt(),Mockito.anyInt());
        assertTrue(userDataResponseDTO instanceof UserDataResponseDTO);
    }
    @Test(expected = Exception.class)
    public void fetchUserDataFailure(){
        Mockito.doThrow(Exception.class).when(repository).findAll();
       userDataService.fetchUserData(Mockito.anyInt(),Mockito.anyInt());
    }
}
