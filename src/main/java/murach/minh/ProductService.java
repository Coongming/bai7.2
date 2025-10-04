package murach.download.bai9_1;

import java.util.*;

public class ProductService {
    private final Map<String, Product> products = new HashMap<>();

    public ProductService() {
        products.put("8601", new Product("8601", "86 (the band) - True Life Songs and Pictures",
                Arrays.asList("You Are a Star", "Don't Make No Difference")));
        products.put("pf01", new Product("pf01", "Paddlefoot - The First CD",
                Arrays.asList("Wait", "I Won't Let You Down")));
        products.put("pf02", new Product("pf02", "Paddlefoot - The Second CD",
                Arrays.asList("Neon Dreams", "Lost in the City")));
        products.put("jr01", new Product("jr01", "Joe Rut - Genuine Wood Grained Finish",
                Arrays.asList("Wood Grain", "Rustic Finish")));
    }

    public Product getProduct(String code) {
        return products.get(code);
    }

    public Map<String, Product> getAllProducts() {
        return Collections.unmodifiableMap(products);
    }
}
