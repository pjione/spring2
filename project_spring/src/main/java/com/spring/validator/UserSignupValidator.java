package com.spring.validator;

import com.spring.domain.UserSignup;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Date;


@Component
public class UserSignupValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserSignup.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        //if(!(o instanceof UserSignup)) return;
        UserSignup userSignup = (UserSignup) o; //supports를 통과했으므로 바로 캐스팅하면 된다.

        String id = userSignup.getId();
        String pwd = userSignup.getPwd();
        String pwdChk = userSignup.getPwdChk();
        String name = userSignup.getName();
        Date birth = userSignup.getBirth();

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"id","required");
        if(pwd.isBlank()){
            errors.rejectValue("pwd","required");
        }
        else if(!pwd.equals(pwdChk)){
            errors.rejectValue("pwdChk","required.user.pwd",null);
        }
        else if(name == null || "".equals(name.trim())){
            errors.rejectValue("name","required");
        }
        else if(birth==null){
            errors.rejectValue("birth","required");
        }
    }

}
