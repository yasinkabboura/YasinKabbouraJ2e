package com.example.ma_jpa.service;

import com.example.ma_jpa.entities.Role;
import com.example.ma_jpa.entities.User;
import com.example.ma_jpa.repositories.RoleRepository;
import com.example.ma_jpa.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByroleName(roleName);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        User user = findUserByUserName(userName);
        Role role = findRoleByRoleName(roleName);
        if(user.getRoles()!=null){
            user.getRoles().add(role);
            role.getUsers().add(user);
        }
    }

    @Override
    public User authenticate(String userName, String password) {
        User user = findUserByUserName(userName);
        if (user==null)  throw new RuntimeException("Bad info");
        if(user.getPassword().equals(password)){
            return user;
        }
        throw new RuntimeException("Bad info");
    }
}
