package com.shopquanao.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "N11NHK_chitiet_donhang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class N11NHK_ChiTietDonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "N11NHK_id")
    private Integer id;

    // Thuộc về đơn hàng nào?
    @ManyToOne
    @JoinColumn(name = "N11NHK_donhang_id", referencedColumnName = "N11NHK_id")
    private N11NHK_DonHang donHang;

    // Đặt mua sản phẩm nào?
    @ManyToOne
    @JoinColumn(name = "N11NHK_sanpham_id", referencedColumnName = "N11NHK_id")
    private N11NHK_SanPham sanPham;

    @Column(name = "N11NHK_so_luong")
    private Integer soLuong;

    // Lưu lại giá tại thời điểm mua (vì sau này shop có thể đổi giá sản phẩm)
    @Column(name = "N11NHK_gia_luc_mua")
    private Double giaLucMua;
}