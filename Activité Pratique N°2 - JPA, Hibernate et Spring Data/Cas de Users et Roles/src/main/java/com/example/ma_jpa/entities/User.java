package com.example.ma_jpa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

    @Id
    private String userId;
    @Column(name = "USER_NAME",unique = true,length = 20)
    private String username;
    private String password;
    @ManyToMany(mappedBy = "users",fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();
}
