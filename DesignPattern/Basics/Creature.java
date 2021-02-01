package Basics;

public abstract class Creature { // abstract class CANT BE OBJECT
    protected String name; // just like private
    protected int weight; // but can be inherited to child
    protected String sound; // otherwise abstract class would be useless

    public abstract void setName(String newName);

    public abstract String getName();

    public abstract void setWeight(double newWeight);

    public abstract double getWeight();
}
