package com.shopquanao.Impl;

import com.shopquanao.Dto.N11NHK_RegisterDTO;
import com.shopquanao.Entity.N11NHK_KhachHang;
import com.shopquanao.Entity.N11NHK_NguoiDung;
import com.shopquanao.Repository.N11NHK_KhachHangRepository;
import com.shopquanao.Repository.N11NHK_NguoiDungRepository;
import com.shopquanao.Service.N11NHK_NguoiDungService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class N11NHK_NguoiDungServiceImpl implements N11NHK_NguoiDungService {

    private final N11NHK_NguoiDungRepository nguoiDungRepository;
    private final N11NHK_KhachHangRepository khachHangRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<N11NHK_NguoiDung> findByUsername(String username) {
        return nguoiDungRepository.findByUsername(username);
    }

    @Override
    @Transactional // Rất quan trọng: Nếu lưu khách hàng lỗi thì sẽ hủy luôn việc tạo user để tránh rác DB
    public void registerNewCustomer(N11NHK_RegisterDTO dto) {
        // 1. Kiểm tra username đã tồn tại chưa
        if (nguoiDungRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Tên đăng nhập này đã tồn tại!");
        }

        // 2. Kiểm tra mật khẩu khớp nhau
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new RuntimeException("Mật khẩu xác nhận không khớp!");
        }

        // 3. Tạo và lưu Người dùng (N11NHK_nguoidung)
        N11NHK_NguoiDung user = new N11NHK_NguoiDung();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(N11NHK_NguoiDung.Role.customer);
        N11NHK_NguoiDung savedUser = nguoiDungRepository.save(user);

        // 2. Tạo và lưu Khách hàng
        N11NHK_KhachHang customer = new N11NHK_KhachHang();
        customer.setUser(savedUser); // Gán trực tiếp đối tượng User vừa lưu
        customer.setName(dto.getFullName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());

        khachHangRepository.save(customer);
    }
}