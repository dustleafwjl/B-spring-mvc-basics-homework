package com.thoughtworks.capacity.gtb.mvc.domain;

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
    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9_]{3,10}$",
            message = "用户名不合法")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 5, max = 12, message = "密码不合法")
    private String password;

    @Email(message = "邮箱地址不合法")
    private String email;
}
