// murach/data/UserIO.java - lưu/đọc rất đơn giản từ file text
package van.minh;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class UserIO {
  public static void add(User u, String path) throws IOException {
    try (PrintWriter out = new PrintWriter(new FileWriter(path, true))) {
      out.println(u.getEmail() + "|" + u.getFirstName() + "|" + u.getLastName());
    }
  }
  public static User getUser(String email, String path) {
    File f = new File(path);
    if (!f.exists()) return null;
    try (BufferedReader br = new BufferedReader(new FileReader(f))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] p = line.split("\\|");
        if (p.length >= 3 && p[0].equalsIgnoreCase(email)) {
          User u = new User();
          u.setEmail(p[0]); u.setFirstName(p[1]); u.setLastName(p[2]);
          return u;
        }
      }
    } catch(Exception ignore){}
    return null;
  }
}
