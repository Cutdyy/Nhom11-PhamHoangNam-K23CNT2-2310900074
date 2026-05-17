package com.shopquanao.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "N11NHK_donhang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class N11NHK_DonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "N11NHK_id")
    private Integer id;

    // Liên kết với người dùng đã đặt hàng
    @ManyToOne
    @JoinColumn(name = "N11NHK_user_id", referencedColumnName = "N11NHK_id")
    private N11NHK_NguoiDung user;

    @Column(name = "N11NHK_ngay_dat_hang")
    private LocalDateTime ngayDatHang;

    @Column(name = "N11NHK_tong_tien")
    private Double tongTien; // Hoặc kiểu dữ liệu giá tiền mà bạn đang dùng

    // Trạng thái: Chờ xác nhận, Đang giao, Hoàn thành, Đã hủy
    @Column(name = "N11NHK_trang_thai", length = 50)
    private String trangThai;

    @Column(name = "N11NHK_dia_chi_giao", length = 255)
    private String diaChiGiao;

    @Column(name = "N11NHK_so_dien_thoai", length = 20)
    private String soDienThoai;

    // Mối quan hệ 1-N với bảng Chi tiết đơn hàng
    @OneToMany(mappedBy = "donHang", cascade = CascadeType.ALL)
    private List<N11NHK_ChiTietDonHang> chiTietDonHangs;
}