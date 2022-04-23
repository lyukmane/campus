package Product;

import java.util.Comparator;

public class NameComporator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        Integer o1Leght = o1.getName().length();
        Integer o2Leght = o2.getName().length();
        return Integer.compare(o1Leght, o2Leght);
    }
}
