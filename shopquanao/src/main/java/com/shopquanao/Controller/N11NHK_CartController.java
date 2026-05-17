package com.shopquanao.Controller;

import com.shopquanao.Dto.CartItemDTO;
import com.shopquanao.Entity.N11NHK_ChiTietDonHang;
import com.shopquanao.Entity.N11NHK_DonHang;
import com.shopquanao.Entity.N11NHK_NguoiDung;
import com.shopquanao.Entity.N11NHK_SanPham;
import com.shopquanao.Repository.N11NHK_DonHangRepository;
import com.shopquanao.Repository.N11NHK_NguoiDungRepository;
import com.shopquanao.Service.N11NHK_CartService;
import com.shopquanao.Service.N11NHK_SanPhamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class N11NHK_CartController {

    private final N11NHK_CartService cartService;
    private final N11NHK_SanPhamService sanPhamService;

    // THÊM 2 REPOSITORY NÀY ĐỂ TƯƠNG TÁC VỚI DATABASE
    private final N11NHK_DonHangRepository donHangRepository;
    private final N11NHK_NguoiDungRepository nguoiDungRepository;

    @GetMapping("/cart")
    public String showCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("totalPrice", cartService.getTotalPrice());
        return "cart";
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam("productId") Integer productId,
                            @RequestParam("quantity") int quantity) {
        N11NHK_SanPham sp = sanPhamService.getSanPhamById(productId);
        if (sp != null) {
            cartService.addToCart(sp, quantity);
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable("id") Integer productId) {
        cartService.removeFromCart(productId);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String showCheckoutForm(Model model) {
        if (cartService.getCartItems().isEmpty()) {
            return "redirect:/cart";
        }
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("totalPrice", cartService.getTotalPrice());
        model.addAttribute("donHang", new N11NHK_DonHang());
        return "checkout";
    }

    // ==============================================================
    // HÀM MỚI: HỨNG DỮ LIỆU TỪ FORM VÀ LƯU VÀO DATABASE
    // ==============================================================
    @PostMapping("/checkout/process")
    public String processCheckout(@ModelAttribute("donHang") N11NHK_DonHang donHang, Principal principal) {
        // 1. Kiểm tra xem khách đã đăng nhập chưa (Spring Security cung cấp đối tượng Principal)
        if (principal == null) {
            return "redirect:/login"; // Chưa đăng nhập thì bắt quay ra đăng nhập
        }

        // Lấy thông tin tài khoản từ Database dựa trên tên đăng nhập
        String username = principal.getName();
        N11NHK_NguoiDung user = nguoiDungRepository.findByUsername(username).orElse(null);

        // 2. Gắn thêm các thông tin ngầm định cho Đơn Hàng (Ngày mua, Tổng tiền, Trạng thái...)
        donHang.setUser(user);
        donHang.setNgayDatHang(LocalDateTime.now());
        donHang.setTongTien(cartService.getTotalPrice());
        donHang.setTrangThai("Chờ xác nhận");

        // 3. Trích xuất từng món đồ trong Giỏ hàng chuyển thành Chi Tiết Đơn Hàng
        List<N11NHK_ChiTietDonHang> danhSachChiTiet = new ArrayList<>();
        for (CartItemDTO item : cartService.getCartItems()) {
            N11NHK_ChiTietDonHang chiTiet = new N11NHK_ChiTietDonHang();

            chiTiet.setDonHang(donHang); // Rất quan trọng: Báo cho CSDL biết chi tiết này thuộc về hóa đơn nào
            chiTiet.setSanPham(item.getSanPham());
            chiTiet.setSoLuong(item.getQuantity());

            // .toString() rồi ép qua Double để đảm bảo không bị lỗi bất kể kiểu dữ liệu giá của bạn là Integer, Double hay BigDecimal
            chiTiet.setGiaLucMua(Double.valueOf(item.getSanPham().getPrice().toString()));

            danhSachChiTiet.add(chiTiet);
        }

        // Gắn danh sách chi tiết vào hóa đơn chính
        donHang.setChiTietDonHangs(danhSachChiTiet);

        // 4. LƯU VÀO DATABASE: Nhờ có cấu hình CascadeType.ALL, lệnh save này sẽ tự động lưu luôn cả các dòng chi tiết!
        donHangRepository.save(donHang);

        // 5. Xóa sạch giỏ hàng (vì đã mua xong rồi)
        cartService.clearCart();

        // 6. Chuyển hướng sang trang báo thành công
        return "redirect:/checkout/success";
    }

    // Trang báo đặt hàng thành công
    @GetMapping("/checkout/success")
    public String checkoutSuccess() {
        return "checkout-success";
    }
}