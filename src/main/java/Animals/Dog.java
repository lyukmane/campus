package Animals;

public class Dog extends Animal{
    private String name1;
    private Object animal;
    public Dog(String name, int age, String type) {
        super(name, age, type);
    }

    @Override
    public String makeSound() {
        return "gav-gav!!!";
    }

    @Override
    public String eat() {
        return "dog eats";
    }
}
