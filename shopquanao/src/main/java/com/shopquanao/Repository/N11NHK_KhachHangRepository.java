package com.shopquanao.Repository;

import com.shopquanao.Entity.N11NHK_KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface N11NHK_KhachHangRepository extends JpaRepository<N11NHK_KhachHang, Integer> {
    // Các hàm cơ bản đã được JpaRepository cung cấp sẵn
}