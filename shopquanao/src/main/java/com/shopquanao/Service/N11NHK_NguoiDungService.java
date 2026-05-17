package com.shopquanao.Service;

import com.shopquanao.Dto.N11NHK_RegisterDTO;
import com.shopquanao.Entity.N11NHK_NguoiDung;

import java.util.Optional;

public interface N11NHK_NguoiDungService {
    // Hàm tìm kiếm người dùng theo username (phục vụ đăng nhập)
    Optional<N11NHK_NguoiDung> findByUsername(String username);

    // Hàm xử lý logic đăng ký tài khoản mới cho khách hàng
    void registerNewCustomer(N11NHK_RegisterDTO registerDTO);
}