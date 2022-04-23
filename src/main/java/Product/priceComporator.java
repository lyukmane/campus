package Product;

import java.util.Comparator;

public class priceComporator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        Integer o1Price = o1.getPrice();
        Integer o2Price = o2.getPrice();
        return Integer.compare(o1Price, o2Price);
    }
}
