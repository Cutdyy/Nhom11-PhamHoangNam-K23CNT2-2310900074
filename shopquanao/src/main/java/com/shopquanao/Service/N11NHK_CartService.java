package com.shopquanao.Service;

import com.shopquanao.Dto.CartItemDTO;
import com.shopquanao.Entity.N11NHK_SanPham;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope // ĐIỂM CỐT LÕI: Mỗi khách hàng (mỗi trình duyệt) sẽ có một "giỏ" riêng biệt
public class N11NHK_CartService {

    // Danh sách các món hàng đang có trong giỏ
    private List<CartItemDTO> cartItems = new ArrayList<>();

    // 1. Lấy toàn bộ hàng trong giỏ
    public List<CartItemDTO> getCartItems() {
        return cartItems;
    }

    // 2. Thêm một sản phẩm vào giỏ
    public void addToCart(N11NHK_SanPham sanPham, int quantity) {
        // Kiểm tra xem sản phẩm này đã có trong giỏ chưa
        for (CartItemDTO item : cartItems) {
            if (item.getSanPham().getId().equals(sanPham.getId())) {
                // Nếu có rồi thì chỉ tăng số lượng
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        // Nếu chưa có thì thêm mới vào danh sách
        cartItems.add(new CartItemDTO(sanPham, quantity));
    }

    // 3. Xóa một món khỏi giỏ
    public void removeFromCart(Integer sanPhamId) {
        cartItems.removeIf(item -> item.getSanPham().getId().equals(sanPhamId));
    }

    // 4. Xóa sạch giỏ hàng (Dùng sau khi thanh toán xong)
    public void clearCart() {
        cartItems.clear();
    }

    // 5. Tính tổng tiền cả giỏ
    public double getTotalPrice() {
        double total = 0;
        for (CartItemDTO item : cartItems) {
            total += item.getSubTotal();
        }
        return total;
    }
}
