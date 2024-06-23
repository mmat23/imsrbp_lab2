package com.imsrbp.ignored;

import java.util.Date;

import javax.persistence.*;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "profiles")
public class Profile extends BaseEntity<Long> {
    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    private String patronymic = "";

    @NotNull
    private Date birthdate;

    @NotEmpty
    private String phone;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}

