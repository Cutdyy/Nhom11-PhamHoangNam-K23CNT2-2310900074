package com.shopquanao.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "N11NHK_sanpham")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class N11NHK_SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "N11NHK_id")
    private Integer id;

    @Column(name = "N11NHK_name", length = 150)
    private String name;

    @Column(name = "N11NHK_price")
    private BigDecimal price; // Sử dụng BigDecimal cho tiền tệ (DECIMAL)

    @Column(name = "N11NHK_description", columnDefinition = "TEXT")
    private String description;

    // Quan hệ Nhiều-1 với Danh Mục
    @ManyToOne
    @JoinColumn(name = "N11NHK_category_id")
    private N11NHK_DanhMuc danhMuc;

    // Quan hệ Nhiều-1 với Nhà Cung Cấp
    // (Giả sử bạn sẽ tạo entity NhaCungCap sau, tạm thời ta để khóa ngoại kiểu Integer hoặc Mapping trực tiếp)
    @Column(name = "N11NHK_supplier_id")
    private Integer supplierId;
}