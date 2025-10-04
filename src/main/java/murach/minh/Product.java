package murach.download.bai9_1;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {
    private String code;
    private String description;
    private List<String> songs;

    public Product() {}

    public Product(String code, String description, List<String> songs) {
        this.code = code;
        this.description = description;
        this.songs = songs;
    }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<String> getSongs() { return songs; }
    public void setSongs(List<String> songs) { this.songs = songs; }
}
