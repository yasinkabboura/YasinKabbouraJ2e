package com.example.ma_jpa.service;

import com.example.ma_jpa.entities.Role;
import com.example.ma_jpa.entities.User;

public interface UserService {
    User addNewUser(User user);
    Role addNewRole(Role role);
    User findUserByUserName(String userName);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String userName,String roleName);
    User authenticate (String userName,String password);
}
