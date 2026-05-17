package com.shopquanao.Controller;

import com.shopquanao.Dto.N11NHK_RegisterDTO;
import com.shopquanao.Entity.N11NHK_SanPham;
import com.shopquanao.Service.N11NHK_DanhMucService;
import com.shopquanao.Service.N11NHK_NguoiDungService;
import com.shopquanao.Service.N11NHK_SanPhamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class N11NHK_HomeController {

    private final N11NHK_SanPhamService sanPhamService;
    private final N11NHK_DanhMucService danhMucService;
    private final N11NHK_NguoiDungService nguoiDungService;

    // ==========================================
    // CÁC TRANG CHUNG (KHÁCH VÀNG LAI)
    // ==========================================

    @GetMapping("/")
    public String trangChu(Model model) {
        model.addAttribute("danhSachDanhMuc", danhMucService.getAllDanhMuc());
        model.addAttribute("danhSachSanPham", sanPhamService.getAllSanPham());
        return "index";
    }

    @GetMapping("/login")
    public String trangDangNhap() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerDTO", new N11NHK_RegisterDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerProcess(@ModelAttribute("registerDTO") N11NHK_RegisterDTO dto, Model model) {
        try {
            nguoiDungService.registerNewCustomer(dto);
            return "redirect:/login?registerSuccess";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    // ==========================================
    // TÌM KIẾM, LỌC VÀ XEM CHI TIẾT SẢN PHẨM
    // ==========================================

    // Lọc sản phẩm theo danh mục (Bấm trên menu thả xuống)
    @GetMapping("/danhmuc/{id}")
    public String getProductsByCategory(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("danhSachSanPham", sanPhamService.getSanPhamByDanhMuc(id));
        // Vẫn phải lấy danh sách danh mục để vẽ thanh Navbar
        model.addAttribute("danhSachDanhMuc", danhMucService.getAllDanhMuc());
        return "index";
    }

    // Tìm kiếm bằng chữ
    @GetMapping("/search")
    public String searchProduct(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("danhSachSanPham", sanPhamService.searchSanPhamByName(keyword));
        model.addAttribute("danhSachDanhMuc", danhMucService.getAllDanhMuc());
        model.addAttribute("keyword", keyword); // Giữ lại từ khóa trên ô tìm kiếm
        return "index";
    }

    // Xem chi tiết một sản phẩm
    @GetMapping("/sanpham/{id}")
    public String xemChiTietSanPham(@PathVariable("id") Integer id, Model model) {
        N11NHK_SanPham sanPham = sanPhamService.getSanPhamById(id);
        if (sanPham == null) {
            return "redirect:/"; // Sản phẩm không tồn tại thì đá về trang chủ
        }

        model.addAttribute("sanPham", sanPham);
        model.addAttribute("danhSachDanhMuc", danhMucService.getAllDanhMuc());

        // Gợi ý các sản phẩm cùng loại
        if (sanPham.getDanhMuc() != null) {
            model.addAttribute("sanPhamLienQuan", sanPhamService.getSanPhamByDanhMuc(sanPham.getDanhMuc().getId()));
        }

        return "sanpham-detail";
    }
    // Hiển thị trang riêng cho tất cả sản phẩm
    @GetMapping("/products")
    public String trangTatCaSanPham(Model model) {
        model.addAttribute("danhSachDanhMuc", danhMucService.getAllDanhMuc());
        model.addAttribute("danhSachSanPham", sanPhamService.getAllSanPham());
        return "products"; // Trả về file products.html
    }
    // ==========================================
    // TRANG TIN TỨC / BLOG
    // ==========================================
    @GetMapping("/tintuc")
    public String trangTinTuc(Model model) {
        // Vẫn phải lấy danh sách danh mục để vẽ thanh Navbar thả xuống
        model.addAttribute("danhSachDanhMuc", danhMucService.getAllDanhMuc());
        return "tintuc"; // Trả về file tintuc.html
    }
}