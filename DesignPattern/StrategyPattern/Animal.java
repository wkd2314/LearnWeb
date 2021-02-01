package StrategyPattern;

public class Animal {
    private String name;
    private int weight;
    private String sound;

    public Flys flyingType;

    public String tryToFly() {
        return flyingType.fly();
    }

    public void setFlyingAbility(Flys newFlyType) {
        flyingType = newFlyType;
    }

    public void setName(String newName) {
        name = newName;
    }

    public String getName() {
        return name;
    }

    public void setWeight(int newWeight) {
        if (newWeight > 0) {
            weight = newWeight;
        } else {
            System.out.println("Weight < 0");
        }
    }

    public double getWeight() {
        return weight;
    }

    public void setSound(String newSound) {
        sound = newSound;
    }

    public String getSound() {
        return sound;
    }

}
