package com.example.userdata.repository;

import com.example.userdata.data.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserData,Long> {
}
