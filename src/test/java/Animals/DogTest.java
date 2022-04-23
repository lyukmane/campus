package Animals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DogTest {


    @Test
    public void checkSound() {
    //GIVEN
    Dog barsik = new Dog("Rex", 12, "Pet");
    String expectedSound = "gav-gav!!!";
    //WHEN
    String actualSound = barsik.makeSound();
    //THEN
    Assertions.assertEquals(expectedSound, actualSound);
    }
    @Test
    public void checkEat(){
        //GIVEN
        Dog barsik = new Dog("Chuck", 5, "Pet");
        String expectedEat = "dog eats";
        //WHEN
        String actualEat = barsik.eat();
        //THEN
        Assertions.assertEquals(expectedEat, actualEat);

    }

    @Test
    public void checkDogName() {
        //GIVEN
        Dog barsik = new Dog("Bobi", 6, "pet");
        String expectedDogName = "Bobi";
        //WHEN
        String actualDogName = barsik.getName();
        //THEN
        Assertions.assertEquals(expectedDogName, actualDogName);
    }

    @Test
    public void checkDogAge() {
        //GIVEN
        Dog barsik = new Dog("Bobi", 6, "pet");
        int expectedAge = 6;
        //WHEN
        int actualAge = barsik.getAge();
        //THEN
        Assertions.assertEquals(expectedAge,actualAge);
    }


}