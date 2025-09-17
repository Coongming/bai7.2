package murach.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UncheckedIOException;

import murach.business.User;

public class UserIO {

    // Ghi thêm (append) user vào file dạng email|first|last
    public static synchronized void add(User user, String path) {
        try (PrintWriter out = new PrintWriter(new FileWriter(path, true))) {
            out.println(user.getEmail() + "|" + user.getFirstName() + "|" + user.getLastName());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    // Tìm user theo email trong file
    public static User getUser(String email, String path) {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = in.readLine()) != null) {
                String[] p = line.split("\\|");
                if (p.length >= 3 && p[0].equalsIgnoreCase(email)) {
                    User u = new User();
                    u.setEmail(p[0]);
                    u.setFirstName(p[1]);
                    u.setLastName(p[2]);
                    return u;
                }
            }
        } catch (IOException ignore) {}
        return null;
    }
}
