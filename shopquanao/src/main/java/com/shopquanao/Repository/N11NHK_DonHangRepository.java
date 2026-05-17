package com.shopquanao.Repository;

import com.shopquanao.Entity.N11NHK_DonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface N11NHK_DonHangRepository extends JpaRepository<N11NHK_DonHang, Integer> {

    // ĐÃ SỬA CHUẨN: Tên hàm là findByUserId
    List<N11NHK_DonHang> findByUserId(Integer userId);

    List<N11NHK_DonHang> findByTrangThai(String trangThai);
}