package com.shopquanao.Repository;

import com.shopquanao.Entity.N11NHK_NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface N11NHK_NguoiDungRepository extends JpaRepository<N11NHK_NguoiDung, Integer> {
    Optional<N11NHK_NguoiDung> findByUsername(String username);
    boolean existsByUsername(String username);
}