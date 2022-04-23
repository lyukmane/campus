package Product;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Product carrot = new Product("Сarrot", 5, "orange", ProductCategories.vegetables);
        Product salmon = new Product("Salmon", 224, "red", ProductCategories.meat);
        Product brie = new Product("Brie", 50, "yellow", ProductCategories.dairy_products);
        Product sheep = new Product("Sheep", 1120, "white", ProductCategories.meat);
        Product pea = new Product("Pea", 70, "green", ProductCategories.vegetables);
        Product yogurt = new Product("Agusha", 3, "white", ProductCategories.dairy_products);
        Product tomato = new Product("Tomato", 14, "red", ProductCategories.vegetables);
        Product pig = new Product("Pig", 2230, "white", ProductCategories.meat);
        Product milk = new Product("Molokia", 25, "white", ProductCategories.dairy_products);

        Product [] ArrayProducts = {carrot, salmon, brie, sheep, pea, yogurt, tomato, pig, milk};
        List <Product> ListProducts = Arrays.asList(ArrayProducts);

        sameVegetables(ListProducts);
        sameMeat(ListProducts);
        sameDairy(ListProducts);
        morePrice100(ListProducts);
        sortedPrice(ListProducts);
        }
    private static void sameVegetables(List<Product> ListProduct){
        List<Product> productStream = ListProduct.stream().
                filter(product -> product.category.equals(ProductCategories.vegetables)).collect(Collectors.toList());
        System.out.println("Список овочів - " + productStream);
    }

    private static void sameMeat(List<Product> ListProduct){
        List<Product> productStream = ListProduct.stream().
                filter(product -> product.category.equals(ProductCategories.meat)).collect(Collectors.toList());
        System.out.println("Список м*яса - " + productStream);
    }
    private static void sameDairy(List<Product> ListProduct){
        List<Product> productStream = ListProduct.stream().
                filter(product -> product.category.equals(ProductCategories.dairy_products)).collect(Collectors.toList());
        System.out.println("Список молочки - " + productStream);
    }

    private static void morePrice100(List<Product> ListProduct){
        List<Product> productStream = ListProduct.stream().
                filter(product -> product.price > 100).collect(Collectors.toList());
        System.out.println("Список ціни менше 100 - " + productStream);
    }

    private static void sortedPrice(List<Product> ListProduct){
        Comparator <Product> comparator = new priceComporator().thenComparing(new NameComporator());
        List<Product> productStream = ListProduct.stream().sorted(comparator).collect(Collectors.toList());
        System.out.println("Списоки відсоpтовані по ціні - " + productStream);
    }
    /*
       Collection<Product> productsObj = new TreeSet<>(Arrays.asList(products));
        Collection <Product> veget_cat = new ArrayList<>();
        Collection <Product> meat_cat = new ArrayList<>();
        Collection <Product> dairy_cat = new ArrayList<>();
        Collection <Product> price_cat = new ArrayList<>();
        Collection <Product> name_cat = new ArrayList<>();
        Iterator productIterator = productsObj.iterator();
        Iterator productIterator2 = productsObj.iterator();
        Iterator productIterator3 = productsObj.iterator();
        while(productIterator.hasNext()) {
            Product nextProd = (Product) productIterator.next();
            if (nextProd.category.equals(ProductCategories.vegetables)){
                veget_cat.add(nextProd);
            }
            if (nextProd.category.equals(ProductCategories.meat)){
                meat_cat.add(nextProd);
            }
            if (nextProd.category.equals(ProductCategories.dairy_products)){
                dairy_cat.add(nextProd);
            }
        }

        while(productIterator2.hasNext()) {
            Product nextProd2 = (Product) productIterator2.next();
            if (nextProd2.description.matches("(.*)whi(.*)")){
                name_cat.add(nextProd2);}
        }
        while(productIterator3.hasNext()) {
            Product nextProd3 = (Product) productIterator3.next();
            if (nextProd3.price < 100){
                price_cat.add(nextProd3);}
        }
        System.out.println("Списоки відсотовані по ціні");
        System.out.println("Список овочів - " + veget_cat);
        System.out.println("Список м*яса - " + meat_cat);
        System.out.println("Список молочки - " + dairy_cat);
        System.out.println("Список однакових описів - " + name_cat);
        System.out.println("Список ціни менше 100 - " + price_cat);

        */

    }

