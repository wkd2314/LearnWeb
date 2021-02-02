# Design Pattern

My log of learning Design Patterns.

- WHY Learn Design Pattern?    
  I Started learning React and found the useState method    
  Actually is from State method. thought i had to learn Design patterns first.

  Above all word DesignPattern sounds nice.

- [Where I Got Helped](https://www.youtube.com/watch?v=vNHpsC5ng_E&list=PLF206E906175C7E0)
- [Where I Got Helped 2](https://www.youtube.com/watch?v=0GTe8e7DYHk&feature=youtu.be)

- [The Well-Known Book for DesignPattern](http://www.uml.org.cn/c++/pdf/designpatterns.pdf)
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
  used in game such as Barrack in Starcraft

<hr/>

## Abstract Factory Pattern

Just like factory but everything encapsulated  
Compared to the UFO Factory Pattern in last example,  
this pattern also Define generic ordering form And then  
Connect the ordering form to Specific Factory.  
So as a result there will be MANY factories connected to ONE form

- Good point and Bad point
  - Can Create families of related objects w/o specifying a concrete class  
    Just like factory pattern, Every ships take adventage of polymorphism
  - It can get complicated(very)

![Abstract-Factory-Small](/AbstractFactoryPattern/Abstract-Factory-Small.png)

- Each Abstract Class(Ship,Factory,Building) is seperated to Concrete Childs
  Giving`Flexability` to each level of creating enemy ship.
- Process
  1. Create Base entities and Builer
  2. Send entity to Builder with type information
  3. Builder Sends to different Factories based on Type
  4. Inside Each Factory add Different features to entity
  5. Base Entities now turned in to various different subclasses

  * for example, in this enemyship there can be one with only missle,
    there can also be one with fast engine only all belonging to same super.

- This Pattern is combination of Strategy, Polymorphism, Factory.

<hr/>

## Singleton Pattern

To prevent instantiating more than one object in class

- Basic Implementation
  ```Java
  public class Singleton {
      private static Singleton firstInstance = null;
      private Singleton() { }
      public static Singleton getInstance() {
          if(firstInstance == null) { // lazy instantiation
          firstInstance = new Singleton();
          }
          return firstInstance;
      }
  } 
  ```
  its called lazy instantiation, if NOT needed never create one

- Issue with Singleton
  MultiThread environment has potential to create more than one obj
  * A Thread is light-weight small process(task).
  So MultiThread is Like a Carrier with Multi Interceptors!
  ```Java
  // Inside getInstance method //
  if (firstThread) { 
      firstThread = false;
      Thread.currentThread();
      Thread.sleep(1000);
  }
  //////////////////////////////
  public static void main(String[] args) {
      Runnable getData = new GetData();
      Runnable getDataAgain = new GetData();

      new Thread(getData).start();
      new Thread(getDataAgain).start();
  }
  public class GetData implements Runnable {
      public void run() {
          Singleton newInstance = Singleton.getInstance();
          System.out.println(System.identityHashCode(newInstance));
      }
  }
  ```
  Two Threads will have different identityHashCode! (two instance made)

- Solutions for multi thread
  - use synchronized option in _Constructor_
    This option prevents second thread to come into Constructor
    Before first thread goes out. 
    So Second thread will wait while First Thread sleeps(1000);
    - This will make process `VERY SLOW`
  ```Java
  public class Singleton {
      private static Singleton firstInstance = null;
      private Singleton() { }
      public static synchronized Singleton getInstance() {
          if(firstInstance == null) {
              if (firstThread) { 
                  firstThread = false;
                  Thread.currentThread();
                  Thread.sleep(1000);
              }
          firstInstance = new Singleton();
          }
          return firstInstance;
      }
  }
  ``` 
  - Instead use synchronized for only _FIRST THREAD_
  ```Java
  public static Singleton getInstance() {
      if (firstInstance == null) {
          synchronized (Singleton.class) {
              if (firstInstance == null) {
                  firstInstance = new Singleton();
              }
          }
      }
      return firstInstance;
  }
  ```
  This way is Thread-Safe. and synchronized will only work
  with First Thread so it will not get slow

- More Ways to Handle Thread Issues 

  excluded synchornize way explained above

  1. make instance in class initialization
    Spring uses this way   
    Can waste memory because it will create unused instance too.
  ```Java
  class Singleton{
    private static Singleton instance = new Singleton();

    public static Singleton getInstance() {
      return instance;
    }
    private Singleton() {}
  }
  ```
  2. LazyHolder
    inner static class is initialized when getInstance() method
    refercences LazyHolder.instance for the first time.

    so. simply using another getter(constructor) with static    
    inside getter will solve the issue

    This also saves memory. never used instance will not be created
  ```Java
  class Singleton {
    public static Singleton getInstance() {
        if (firstThread) {  // for test
            firstThread = false;
            Thread.currentThread();
            Thread.sleep(1000);
        }
        return LazyHolder.instance; //second thread will create instance
    }
    private static class LazyHolder {
        private static final Singleton instance = new Singleton();
    }
    private Singleton() {}
  }
  ```

<hr/>

## Builder Pattern

When creating obj there is mainly two ways.
```Java
// First way
Student s1 = new Student();
s1.setFirstName("Berry");
s1.setLastName("Allen");
s1.setGender('m');
System.out.println(s1);
// Second way
Student s2 = new Student("Iris", "West", 0, 'f');
System.out.println(s2);
```

What if there are tons of attributes(parameters) to create ONE OBJ?
- Issues
  - Setter :  Multiple Statements for one Obj (statement = parameter num)
  - Constructor : Order of Parameters has to be Preserved

- Solution
  - Create inner static class Builder then setter in it
  ```Java
  public static class Builder {
      private String firstName;
      private String lastName;
      public Builder setFirstName(String firstName) {
          this.firstName = firstName;
          return this;
      }
      public Builder setLastName(String lastName) { ... }
      public Student build() {
          return new Student(this.firstName, this.lastName);
      }
  }
  ////
  Student s1 = new Student.Builder().setLastName("T").setFirstName("P").build();
  Student s2 = new Student.Builder().setLastName("E").build();
  ```
  This way order is not preserved, can even skip   
  some parameters, creating object with one statement.

  Also think of using encapsulation making it cleaner!
  ```Java
  public interface RobotBuilder {...}
  public class  OldRobotBuilder implements RobotBuilder {...}
  public class RobotEngineer {
    private RobotBuilder robotBuilder;
    public RobotEngineer(RobotBuilder robotBuilder) {
        this.robotBuilder = robotBuilder;
    }
    public Robot getRobot() {...}
    public Robot makeRobot() {...}
  }
  // in Main.java //
  RobotBuilder oldStyleRobot = new OldRobotBuilder();
  // Blueprint of old styled robot!
  RobotEngineer robotEngineer = new RobotEngineer(oldStyleRobot);
  robotEngineer.makeRobot();
  Robot firstRobot = robotEngineer.getRobot();
  ```

<hr/>

## Prototype Pattern

```Java
public static void main(String[] args) {
    Student s1 = new Student("Barry", "male", "St. Josephs");
    Student s2 = new Student("Harry", "male", "St. Josephs");
    Student s3 = new Student("Wally", "male", "St. Josephs");
}
```
Creating new object with new keyword is Unefficient.
So prototype pattern is for reducing use of new keyword.

- Implementation
  1. implement Cloneable
  2. Create clone method inside with super.clone()
    There is no parent of Student class so super is Object class
  3. surround with try-catch statement
    There is possibility it can create Error so it doesnt run w/o error handling
  ```Java
  public class Student implements Cloneable {
    public Student clone() {
        Student s = null;
        try {
            s = (Student) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return s;
    }
  }
  // Main
  public static void main(String[] args) {
    Student prototype = new Student();
    prototype.setGender("male");
    prototype.setSchool("St. Josephs");

    Student s1 = prototype.clone();
    s1.setName("Barry");
    Student s2 = prototype.clone();
    s2.setName("Harry");
  }
  ```

<hr/>

