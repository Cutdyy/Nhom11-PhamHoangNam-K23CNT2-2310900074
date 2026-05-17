package com.shopquanao.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "N11NHK_khachhang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class N11NHK_KhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "N11NHK_id")
    private Integer id;

    // Thiết lập mối quan hệ 1-1 với bảng Người dùng
    @OneToOne
    @JoinColumn(name = "N11NHK_user_id", referencedColumnName = "N11NHK_id")
    private N11NHK_NguoiDung user;

    @Column(name = "N11NHK_name", length = 150)
    private String name;

    @Column(name = "N11NHK_email", length = 150)
    private String email;

    @Column(name = "N11NHK_phone", length = 20)
    private String phone;

    @Column(name = "N11NHK_address", length = 255)
    private String address;
}