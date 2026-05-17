package com.shopquanao.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Table(name = "N11NHK_danhmuc")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class N11NHK_DanhMuc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "N11NHK_id")
    private Integer id;

    @Column(name = "N11NHK_name", length = 100)
    private String name;

    @Column(name = "N11NHK_description", columnDefinition = "TEXT")
    private String description;

    // Một danh mục có nhiều sản phẩm
    @OneToMany(mappedBy = "danhMuc", cascade = CascadeType.ALL)
    private List<N11NHK_SanPham> sanPhams;
}
