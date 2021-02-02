# Design Pattern

My log of learning Design Patterns.

WHY Learn Design Pattern?
I Started learning React and found the useState method
Actually is from State method. thought i had to learn
Design patterns first.

- [Where I Got Helped (A LOT)](https://www.youtube.com/watch?v=vNHpsC5ng_E&list=PLF206E906175C7E0)
<hr/>

## Basics

### Encapsulation

- to protect data, never change value directly in main function
- main function just helps all the methods and data INTERACT

```Java
class Dog {
    private double weight; // save data by private
    public void setWeight(int newWeight) { // parameter newWeight
        weight = newWeight;
    }
}
public static void main(String[] args) { // static > called once
    Dog grover = new Dog();              // final > never change
    grover.setWeight(10); // argument 10
}
```

- Fields(Instance Variables) are declared in the class
- Local Variables are declared in a method

```Java
class Dog{
    private int weight; // Field

    public double getGrams() {
        double gramMult = 453.39; // Local Variable
        return gramMult * weight;
    }
}
```

### Polymorphism

- Different Subclasses from one parent
  Acts Differently using Common method.
  ```Java
  Animal doggy = new Dog();
  Animal kitty = new Cat(); // getSound method in Animal class
  kitty.getSound(); // Meow
  doggy.getSound(); // Bark
  ```
- Note that this animals CANT do their own functions

  ```Java
  Animal robotDog = new Dog();
  Dog realDog = new Dog(); // Dog extends Animal
  robotDog.digHole(); // undefined. digHole method only in Dog class
  realDog.digHole(); // works well
  ((Dog)robotDog).digHole(); // this works fine.
  ```

  - How to use Polymorphism >> Use `abstract` + Classes Created with abstract CANT be OBJECT

    ```Java
    abstract public class Creature{
    protected String name; // just like private but can be inherited
    public abstract void setName(String newName);
    }

    public class Giraffe extends Creature {
    @Override
    public void setName(String newName) { ... }
    }

    Creature creature = new Creature(); // error !!
    Giraffe giraffe = new Giraffe(); // use this way
    ```

    - Every class that extends Creature will have same method
      (setName) that can act differently.

  - Interfaces

  * A Class with only abstract methods.
  * Class can get(implement) many different Interfaces
    ```Java
    public interface Living {
        public void setName(String newName);
        //public abstract void setName(String newName);
        // Do not have to use this way because
        // interface is by Default, Abstract methods only
        public String getName();
        }
    public class Monkey implements Living {
        @Override public void setName(String newName) { ... }
    }
    ```

  <hr/>

## Strategy Pattern

Among childs of Super Class when we want to add
Special Common Method to Few of them

```Java
public class Animal {
    private String name;
    public void fly() { ... }
}
// OR
public class Bird extends Animal {
    public void fly() { ... }
}
```

- Issues
  1. this is not a good way. NOT all Animals can fly
  2. Inconvenient to add method to Every flying animals
     - Just implementing fly method would not help too.
  3. Make FlyAnimal class and let Bird and stuffs extend it
     - Pretty nice but not adjustable to All Case
       ( how about animal which can fly and swim? )

* Solution (Strategy Pattern)

  - Create Fly interface But Add BOOLEAN OPTION(CAN AND CANT)
  - those classes(ItFlys, CantFlys) are used as objects themselves in Each Animal.

  ```Java
  class ItFlys implements Flys {
      public String fly() {
          return "Fly High!";
      }
  }
  class CantFlys implements Flys {
      public String fly() {
          return "I can't fly..";
      }
  }
  public class Animal {
      public Flys flyingType;
      public String tryToFly(){
          return flyingType.fly();
      }
      public void setFlyingAbility(Flys newFlyType) {
          flyingType = newFlyType;
      }
  }
  public Dog() { // 생성자
      super();
      flyingType = new CantFly();
  }
  ```

  - Using this pattern I can Change its type Dynamically in _runtime_

  ```Java
  Animal sparky = new Dog();
  System.out.println("Dog: " + sparky.tryToFly()); //cant fly
  System.out.println("Bird: " + tweety.tryToFly()); //can fly

  sparky.setFlyingAbility(new ItFly());
  System.out.println("Dog: " + sparky.tryToFly()); //can fly
  ```

  - This also Encapsulates FlyingType so animal Dont know how it works.

* When to use.

  - one behavior that is similar to other behaviors in a list

    - Not Flying
    - Fly with Wings
    - Fly super fast

  - need to use one of several behaviors dynamically

    - dog cant fly
    - now can fly (may be by client)
    - now fly super fast

    <hr/>

## Observer Pattern

When there is a lot of Subscribers to receive an update
Depending on an object Change. For example,
when we log in to site. All the stuff needs to get updated

- GoodPoint and BadPoint

  - The subject(publisher) doesn't need to know any about subs
    every time data changes, subject just notifies observers
  - The subject may send updates that doesn't matter to some subs

- So what is Observer Pattern
  An Object called the subject, maintains a list of
  its dependents, called observers, and notifies them
  automatically of any state changes.
- Use Subject and Observer as INTERFACE
  - when creating StockObservers(생성자)
  * stockGrabber.register(this) adds it to stockGrabber List
  * it controls observers with Register,Unregister,Notify methods

```Java
public class StockGrabber implements Subject {
    @Override
    public void register(Observer newObserver) {
        observers.add(newObserver);
    }
    @Override
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update(data);
        }
    }
    public void setData(int newData) {
        this.Data = newData;
        notifyObserver();
    }
}
public class StockObserver implements Observer {
    private Subject stockGrabber;
    public StockObserver(Subject stockGrabber) {
        this.stockGrabber = stockGrabber;
        stockGrabber.register(this);
    }
    @Override
    public void update(int data){
        this.data = data;
        System.out.println(data);
    }
}
```

And Use it like this

```Java
StockGrabber stockGrabber = new StockGrabber();
StockObserver observer1 = new StockObserver(stockGrabber);
stockGrabber.setData(1923.00);
stockGrabber.setData(1243.00);
stockGrabber.setData(12423.00);
```

It will print out value of data everytime it changes
SetData >> notifyObserver >> update for each element in array

<hr/>

## Factory Pattern

When a method returns one of several possible classes
that shares common super class

- When I don't know what class object I need before runtime.
- And I just know it all belongs to the same super class
- To Hide the class from user (encapsulation)

```Java
public EnemyShip makeEnemyShip(String newShipType) {
        if (newShipType.equals("U")) {
            return new UFOEnemyShip();
        } else if (newShipType.equals("R")) {
            return new RocketEnemyShip();
        } else if (newShipType.equals("B")) {
            return new BigUFOEnemyShip();
        } else
            return null;
    }
}
```

- Process
  1. initialize ship
  2. What type of ship? >> make a ship
  3. goto the class where its name is the typeOfShip
  4. creates the ship based on the class data
  5. The ship has now turned in to rocketShip

* Encapsulates process of making different ships
  from basic ship.
* The Type can be chosen by client in runtime

- As named Factory pattern i feel like it is likely to be
  used in game such as Barrak in Starcraft

## Abstract Factory Pattern

Just like factory but everything encapsulated  
Compared to the UFO Factory Pattern in last example,  
this pattern also Define generic ordering form And then  
Connect the ordering form to Specific Factory.  
So as a result there will be MANY factories connected to ONE form

- Good point and Bad point
  - Can Create families of related objects w/o specifying a concrete class  
    Just like factory pattern, Every ships take adventage of polymorphism
  - It can get complicated
