package com.shopquanao.Repository;

import com.shopquanao.Entity.N11NHK_SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface N11NHK_SanPhamRepository extends JpaRepository<N11NHK_SanPham, Integer> {
    List<N11NHK_SanPham> findByNameContainingIgnoreCase(String keyword);
    List<N11NHK_SanPham> findByDanhMucId(Integer danhMucId);
    List<N11NHK_SanPham> findBySupplierId(Integer supplierId);

}