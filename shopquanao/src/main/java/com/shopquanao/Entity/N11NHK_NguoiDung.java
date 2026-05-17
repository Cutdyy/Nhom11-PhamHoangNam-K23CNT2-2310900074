package com.shopquanao.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "N11NHK_nguoidung")
@Data // Tự động tạo Getter, Setter, Constructor nhờ Lombok
public class N11NHK_NguoiDung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "N11NHK_id")
    private Integer id;

    @Column(name = "N11NHK_username", unique = true, length = 100)
    private String username;

    @Column(name = "N11NHK_password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "N11NHK_role", columnDefinition = "ENUM('admin','staff','customer') DEFAULT 'customer'")
    private Role role;

    @Column(name = "created_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public enum Role {
        admin, staff, customer
    }
}
