package com.shopquanao.Service;

import com.shopquanao.Entity.N11NHK_SanPham;

import java.util.List;

public interface N11NHK_SanPhamService {
    List<N11NHK_SanPham> getAllSanPham();

    N11NHK_SanPham getSanPhamById(Integer id);

    N11NHK_SanPham saveSanPham(N11NHK_SanPham sanPham);

    void deleteSanPham(Integer id);

    List<N11NHK_SanPham> searchSanPhamByName(String keyword);

    List<N11NHK_SanPham> getSanPhamByDanhMuc(Integer danhMucId);

}
