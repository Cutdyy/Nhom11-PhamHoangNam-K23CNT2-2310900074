package com.shopquanao.Dto;

import lombok.Data;

@Data
public class N11NHK_RegisterDTO {
    private String username;
    private String password;
    private String confirmPassword; // Để kiểm tra nhập lại mật khẩu
    private String fullName;
    private String email;
    private String phone;
    private String address;
}
