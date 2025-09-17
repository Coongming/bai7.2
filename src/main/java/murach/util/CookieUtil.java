package murach.util;

import jakarta.servlet.http.Cookie;

public class CookieUtil {
    public static String getCookieValue(
        Cookie[] cookies, String cookieName) {
        String cookieValue = ""; // Khởi tạo giá trị mặc định

        // Kiểm tra xem mảng cookies có tồn tại không
        if (cookies != null) {
            // Duyệt qua từng cookie trong mảng
            for (Cookie cookie : cookies) {
                // So sánh tên của cookie với tên cần tìm
                if (cookieName.equals(cookie.getName())) {
                    cookieValue = cookie.getValue();
                    break; // Thoát vòng lặp khi đã tìm thấy
                }
            }
        }
        return cookieValue;
    }
}