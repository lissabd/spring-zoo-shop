package org.example.zooshop.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    @NonNull
    private String name;

    @NonNull
    private String password;

    @NonNull
    private String email;


    @NonNull
    private String role;

}
