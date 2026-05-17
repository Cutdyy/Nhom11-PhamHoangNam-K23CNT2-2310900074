package com.shopquanao.Impl;

import com.shopquanao.Entity.N11NHK_SanPham;
import com.shopquanao.Repository.N11NHK_SanPhamRepository;
import com.shopquanao.Service.N11NHK_SanPhamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class N11NHK_SanPhamServiceImpl implements N11NHK_SanPhamService {

    private final N11NHK_SanPhamRepository sanPhamRepository;

    @Override
    public List<N11NHK_SanPham> getAllSanPham() {
        return sanPhamRepository.findAll();
    }

    @Override
    public N11NHK_SanPham getSanPhamById(Integer id) {
        return sanPhamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với ID: " + id));
    }

    @Override
    public N11NHK_SanPham saveSanPham(N11NHK_SanPham sanPham) {
        // Bạn có thể thêm các logic kiểm tra (Validation) ở đây trước khi lưu
        // Ví dụ: Kiểm tra giá tiền có âm không?
        if (sanPham.getPrice().signum() < 0) {
            throw new IllegalArgumentException("Giá sản phẩm không được nhỏ hơn 0");
        }
        return sanPhamRepository.save(sanPham);
    }

    @Override
    public void deleteSanPham(Integer id) {
        // Kiểm tra xem sản phẩm có tồn tại trước khi xóa không
        N11NHK_SanPham existingSP = getSanPhamById(id);
        sanPhamRepository.delete(existingSP);
    }

    @Override
    public List<N11NHK_SanPham> searchSanPhamByName(String keyword) {
        return sanPhamRepository.findByNameContainingIgnoreCase(keyword);
    }

    @Override
    public List<N11NHK_SanPham> getSanPhamByDanhMuc(Integer danhMucId) {
        return sanPhamRepository.findByDanhMucId(danhMucId);
    }

}