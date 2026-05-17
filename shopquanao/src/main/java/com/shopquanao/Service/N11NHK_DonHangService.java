package com.shopquanao.Service;

import com.shopquanao.Entity.N11NHK_DonHang;
import com.shopquanao.Repository.N11NHK_DonHangRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class N11NHK_DonHangService {

    private final N11NHK_DonHangRepository donHangRepository;

    // Lấy tất cả đơn hàng (có thể sắp xếp mới nhất lên đầu nếu muốn)
    public List<N11NHK_DonHang> getAllDonHang() {
        return donHangRepository.findAll();
    }

    // Cập nhật trạng thái đơn hàng
    public void updateTrangThai(Integer id, String trangThaiMoi) {
        N11NHK_DonHang donHang = donHangRepository.findById(id).orElse(null);
        if (donHang != null) {
            donHang.setTrangThai(trangThaiMoi);
            donHangRepository.save(donHang);
        }
    }
}