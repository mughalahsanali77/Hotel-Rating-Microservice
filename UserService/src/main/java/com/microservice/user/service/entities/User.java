package com.microservice.user.service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "micro_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "ID")
    private String userId;
    private String name;
    private String email;
    private String about;
    @Transient
    private List<Rating> ratings= new ArrayList<>();
}
