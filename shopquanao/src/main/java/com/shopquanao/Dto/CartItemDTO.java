package com.shopquanao.Dto;

import com.shopquanao.Entity.N11NHK_SanPham;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    private N11NHK_SanPham sanPham; // Lưu thông tin gốc của sản phẩm
    private int quantity;           // Số lượng khách đặt mua

    // Hàm tiện ích tự động tính thành tiền (Giá x Số lượng)
    public double getSubTotal() {
        return sanPham.getPrice().multiply(java.math.BigDecimal.valueOf(quantity)).doubleValue();
    }
}