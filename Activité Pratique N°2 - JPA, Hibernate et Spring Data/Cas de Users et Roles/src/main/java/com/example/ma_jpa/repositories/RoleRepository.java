package com.example.ma_jpa.repositories;

import com.example.ma_jpa.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByroleName(String roleName);
}
