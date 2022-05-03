package com.example.dataprizma.loginmodel;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@Table(name = "user_accounts")
public class  User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Email
    @NotEmpty(message = "email is required")
    private String email;

    @NotNull
    @NotEmpty(message = "password is required")
    private String password;

    @NotNull
    @NotEmpty(message = "password is required")
    private String password1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;



    public User() {

    }
}

