package com.shopquanao.Controller;

import com.shopquanao.Entity.N11NHK_DanhMuc;
import com.shopquanao.Entity.N11NHK_SanPham;
import com.shopquanao.Service.N11NHK_DanhMucService;
import com.shopquanao.Service.N11NHK_DonHangService;
import com.shopquanao.Service.N11NHK_SanPhamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin") // Tất cả URL trong đây đều bắt đầu bằng /admin
@RequiredArgsConstructor
public class N11NHK_AdminController {

    // Tiêm cả 3 Service vào đây
    private final N11NHK_SanPhamService sanPhamService;
    private final N11NHK_DanhMucService danhMucService;
    private final N11NHK_DonHangService donHangService;

    // ==========================================
    // 1. QUẢN LÝ SẢN PHẨM
    // ==========================================
    @GetMapping("/sanpham")
    public String listSanPham(Model model) {
        model.addAttribute("danhSachSP", sanPhamService.getAllSanPham());
        return "admin/sanpham-list";
    }

    @GetMapping("/sanpham/add")
    public String showAddForm(Model model) {
        model.addAttribute("sanPham", new N11NHK_SanPham());
        model.addAttribute("danhSachDM", danhMucService.getAllDanhMuc());
        return "admin/sanpham-form";
    }

    @PostMapping("/sanpham/save")
    public String saveSanPham(@ModelAttribute("sanPham") N11NHK_SanPham sanPham) {
        sanPhamService.saveSanPham(sanPham);
        return "redirect:/admin/sanpham?success";
    }

    @GetMapping("/sanpham/delete/{id}")
    public String deleteSanPham(@PathVariable("id") Integer id) {
        sanPhamService.deleteSanPham(id);
        return "redirect:/admin/sanpham?deleted";
    }

    // ==========================================
    // 2. QUẢN LÝ DANH MỤC (Đây là đoạn nãy bạn bị mất)
    // ==========================================
    @GetMapping("/danhmuc")
    public String listDanhMuc(Model model) {
        model.addAttribute("danhSachDM", danhMucService.getAllDanhMuc());
        return "admin/danhmuc-list";
    }

    @GetMapping("/danhmuc/add")
    public String showAddDanhMucForm(Model model) {
        model.addAttribute("danhMuc", new N11NHK_DanhMuc());
        return "admin/danhmuc-form";
    }

    @PostMapping("/danhmuc/save")
    public String saveDanhMuc(@ModelAttribute("danhMuc") N11NHK_DanhMuc danhMuc) {
        danhMucService.saveDanhMuc(danhMuc);
        return "redirect:/admin/danhmuc?success";
    }

    @GetMapping("/danhmuc/delete/{id}")
    public String deleteDanhMuc(@PathVariable("id") Integer id) {
        try {
            danhMucService.deleteDanhMuc(id);
            return "redirect:/admin/danhmuc?deleted";
        } catch (Exception e) {
            return "redirect:/admin/danhmuc?error";
        }
    }

    // ==========================================
    // 3. QUẢN LÝ ĐƠN HÀNG
    // ==========================================
    @GetMapping("/donhang")
    public String listDonHang(Model model) {
        model.addAttribute("danhSachDonHang", donHangService.getAllDonHang());
        return "admin/donhang-list";
    }

    @PostMapping("/donhang/update-status")
    public String updateOrderStatus(@RequestParam("id") Integer id,
                                    @RequestParam("trangThai") String trangThai) {
        donHangService.updateTrangThai(id, trangThai);
        return "redirect:/admin/donhang?success";
    }
}