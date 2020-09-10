package com.thoughtworks.capacity.gtb.mvc.domain;

import com.thoughtworks.capacity.gtb.mvc.validation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private int id;

    @NotBlank(message = "用户名不能为空", groups = {UserNameNotBlankCheck.class})
    @Pattern(regexp = "^[a-zA-Z0-9_]{3,10}$",
            message = "用户名不合法",
            groups = {UserNamePatternCheck.class})
    private String username;

    @NotBlank(message = "密码不能为空", groups = {PassWordNotBlankCheck.class})
    @Size(min = 5, max = 12, message = "密码不合法", groups = {PassWordSizeCheck.class})
    private String password;

    @Email(message = "邮箱地址不合法", groups = {EmailCheck.class})
    private String email;
}
