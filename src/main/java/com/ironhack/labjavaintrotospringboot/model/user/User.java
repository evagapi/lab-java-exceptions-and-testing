package com.ironhack.labjavaintrotospringboot.model.user;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@MappedSuperclass
@Data
public class User {
    @NotEmpty(message = "You must provide a name")
    private String name;
}
