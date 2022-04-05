package com.example.userdata.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDataResponseDTO {
    List<String> userList;
    Long totalCharacters;
}
