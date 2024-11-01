package com.github.dougmab.yabbl.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class UserRegisterDTO {
    @Length(min = 3, max = 20)
    private String nickname;
    @Length(min = 3, max = 20)
    private String handle;
    @Email
    private String email;
    private String password;
}
