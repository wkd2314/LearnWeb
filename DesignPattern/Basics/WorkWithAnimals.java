package Basics;

public class WorkWithAnimals {

    public static void main(String[] args) {
        Dog fido = new Dog();

        fido.setName("Fido");
        System.out.println(fido.getName());

        fido.digHole();
        fido.setWeight(-1);

        int randNum = 10;
        fido.changeVar(randNum);

        System.out.println("randNum after method: " + randNum);

        Animal doggy = new Dog();
        Animal kitty = new Cat();

        System.out.println("Doggy says: " + doggy.getSound());
        System.out.println("Kitty says: " + kitty.getSound());

        Animal[] animals = new Animal[4];
        animals[0] = doggy;
        animals[1] = kitty;

        speakAnimal(doggy);
        // doggy.digHole(); // undifined It is not Dog obj but Animal
        // sayHello(); // cant make a static reference to non-static method
    }

    public static void speakAnimal(Animal randAnimal) {
        System.out.println("Animal says: " + randAnimal.getSound());
    }

    public void sayHello() {
    }
}
