package Product;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainTest {
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
    List<Product> ListProducts = Arrays.asList(ArrayProducts);

    @Test
    public void sameVegetables() {
        //GIVEN
        Product [] ArrayVegetables = {carrot, pea, tomato};
        List<Product> ExpectedListVegetables = Arrays.asList(ArrayVegetables);

        //WHEN
        List<Product> ActualproductStream = ListProducts.stream().
                filter(product -> product.category.equals(ProductCategories.vegetables)).
                collect(Collectors.toList());
        //THEN
        Assertions.assertEquals(ExpectedListVegetables, ActualproductStream);
    }

    @Test
    public void sameMeat() {
        //GIVEN
        Product [] ArrayMeat = {salmon, sheep, pig};
        List<Product> ExpectedListMeat = Arrays.asList(ArrayMeat);

        //WHEN
        List<Product> ActualproductStream = ListProducts.stream().
                filter(product -> product.category.equals(ProductCategories.meat)).
                collect(Collectors.toList());
        //THEN
        Assertions.assertEquals(ExpectedListMeat, ActualproductStream);
    }

    @Test
    public void sameDairy() {
        //GIVEN
        Product [] ArrayDairy = {brie, yogurt, milk};
        List<Product> ExpectedListDairy = Arrays.asList(ArrayDairy);

        //WHEN
        List<Product> ActualproductStream = ListProducts.stream().
                filter(product -> product.category.equals(ProductCategories.dairy_products)).
                collect(Collectors.toList());
        //THEN
        Assertions.assertEquals(ExpectedListDairy, ActualproductStream);
    }

    @Test
    public void morePrice100() {
        //GIVEN
        Product [] ArrayMorePrice100 = {salmon, sheep, pig};
        List<Product> ExpectedListMorePrice100 = Arrays.asList(ArrayMorePrice100);

        //WHEN
        List<Product> ActualproductStream = ListProducts.stream().
                filter(product -> product.price > 100).collect(Collectors.toList());
        //THEN
        Assertions.assertEquals(ExpectedListMorePrice100, ActualproductStream);
    }
}
