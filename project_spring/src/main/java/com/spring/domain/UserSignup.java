package com.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignup {
    private String id;
    private String pwd;
    private String pwdChk;
    private String name;
    private String email;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotEmpty
    private Date birth;
    private String sns;
    private Date regDate;
}
