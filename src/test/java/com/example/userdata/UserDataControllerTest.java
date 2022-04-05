package com.example.userdata;

import com.example.userdata.controller.UserDataController;
import com.example.userdata.data.UserData;
import com.example.userdata.service.IUserDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UserDataControllerTest {
    @InjectMocks
    UserDataController userDataController;

    @Mock
    IUserDataService userDataService;

    private MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userDataController).build();
    }

    @Test
    public void saveDataSuccess() throws Exception {
        List<UserData> userDataList=new ArrayList<>();
        UserData userData1=new UserData(1,"A",12);
        UserData userData2=new UserData(2,"B",2);
        userDataList.add(userData1);
        userDataList.add(userData2);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .content(new ObjectMapper().writeValueAsString(userDataList))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andDo(print())
                        .andExpect(status().isOk());
    }
    @Test
    public void getUserDataSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/userdata")
                        .param("minValue", String.valueOf(100))
                        .param("maxValue", String.valueOf(4000))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andDo(print())
                        .andExpect(status().isOk());
    }

    @Test(expected = Exception.class)
    public void getUserDataFailure() throws Exception {
        Mockito.doThrow(Exception.class).when(userDataService).fetchUserData(10,10);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/userdata")
                        .param("minValue", String.valueOf(10))
                        .param("maxValue", String.valueOf(10))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isInternalServerError());
    }
    @Test(expected = Exception.class)
    public void saveDataFailure() throws Exception {
        List<UserData> userDataList=new ArrayList<>();
        UserData userData1=new UserData(1,"A",12);
        UserData userData2=new UserData(2,"B",2);
        userDataList.add(userData1);
        userDataList.add(userData2);
        Mockito.doThrow(Exception.class).when(userDataService).saveUserData(userDataList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                        .content(new ObjectMapper().writeValueAsString(userDataList))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isInternalServerError());
    }
}