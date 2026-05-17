package com.shopquanao.Impl;

import com.shopquanao.Entity.N11NHK_DanhMuc;
import com.shopquanao.Repository.N11NHK_DanhMucRepository;
import com.shopquanao.Service.N11NHK_DanhMucService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class N11NHK_DanhMucServiceImpl implements N11NHK_DanhMucService {

    // Spring sẽ tự động inject Repository vào đây nhờ @RequiredArgsConstructor
    private final N11NHK_DanhMucRepository danhMucRepository;

    @Override
    public List<N11NHK_DanhMuc> getAllDanhMuc() {
        return danhMucRepository.findAll();
    }

    @Override
    public N11NHK_DanhMuc getDanhMucById(Integer id) {
        // Trả về danh mục nếu tìm thấy, nếu không ném ra lỗi (hoặc trả về null tùy logic của bạn)
        return danhMucRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy danh mục với ID: " + id));
    }

    @Override
    public N11NHK_DanhMuc saveDanhMuc(N11NHK_DanhMuc danhMuc) {
        // Hàm save() của JPA dùng chung cho cả Thêm mới và Cập nhật
        // Nếu danhMuc có id (đã tồn tại), nó sẽ Update. Nếu id là null, nó sẽ Insert.
        return danhMucRepository.save(danhMuc);
    }

    @Override
    public void deleteDanhMuc(Integer id) {
        danhMucRepository.deleteById(id);
    }

    @Override
    public List<N11NHK_DanhMuc> searchDanhMucByName(String name) {
        return danhMucRepository.findByNameContainingIgnoreCase(name);
    }
}