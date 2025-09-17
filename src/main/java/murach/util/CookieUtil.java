package murach.util;

import jakarta.servlet.http.Cookie;

public class CookieUtil {
    public static String getCookieValue(Cookie[] cookies, String name) {
        if (cookies == null) return null;
        for (Cookie c : cookies) {
            if (name.equals(c.getName())) return c.getValue();
        }
        return null;
    }
}
