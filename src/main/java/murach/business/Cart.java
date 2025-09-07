package murach.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    private final List<LineItem> items = new ArrayList<>();

    public List<LineItem> getItems() { return items; }
    public int getCount() { return items.size(); }

    public void addItem(LineItem item) {
        String code = item.getProduct().getCode();
        int quantity = item.getQuantity();
        for (LineItem li : items) {
            if (li.getProduct().getCode().equals(code)) {
                li.setQuantity(quantity);
                return;
            }
        }
        items.add(item);
    }

    public void removeItem(LineItem item) {
        String code = item.getProduct().getCode();
        items.removeIf(li -> li.getProduct().getCode().equals(code));
    }
}
