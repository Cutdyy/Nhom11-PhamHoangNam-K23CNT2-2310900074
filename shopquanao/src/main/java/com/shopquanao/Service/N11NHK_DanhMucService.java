package com.shopquanao.Service;

import com.shopquanao.Entity.N11NHK_DanhMuc;

import java.util.List;

public interface N11NHK_DanhMucService {
    List<N11NHK_DanhMuc> getAllDanhMuc();

    N11NHK_DanhMuc getDanhMucById(Integer id);

    N11NHK_DanhMuc saveDanhMuc(N11NHK_DanhMuc danhMuc);

    void deleteDanhMuc(Integer id);

    List<N11NHK_DanhMuc> searchDanhMucByName(String name);
}
