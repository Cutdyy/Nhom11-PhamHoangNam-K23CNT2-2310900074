package com.shopquanao.Impl;

import com.shopquanao.Entity.N11NHK_NguoiDung;
import com.shopquanao.Repository.N11NHK_NguoiDungRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class N11NHK_UserDetailsServiceImpl implements UserDetailsService {

    private final N11NHK_NguoiDungRepository nguoiDungRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Tìm user trong Database của chúng ta
        N11NHK_NguoiDung user = nguoiDungRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy tài khoản: " + username));

        // Chuyển đổi thành UserDetails của Spring Security
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                // Ép kiểu role (admin, staff, customer) sang chuẩn phân quyền
                .roles(user.getRole().name().toUpperCase())
                .build();
    }
}