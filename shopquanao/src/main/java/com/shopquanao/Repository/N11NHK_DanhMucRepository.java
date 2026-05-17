package com.shopquanao.Repository;

import com.shopquanao.Entity.N11NHK_DanhMuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface N11NHK_DanhMucRepository extends JpaRepository<N11NHK_DanhMuc, Integer> {
    List<N11NHK_DanhMuc> findByNameContainingIgnoreCase(String name);
}