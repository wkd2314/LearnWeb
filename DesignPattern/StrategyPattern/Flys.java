package StrategyPattern;

public interface Flys {

    String fly();

}

class ItFly implements Flys {

    public String fly() {
        return "Fly High!";
    }

}

class CantFly implements Flys {

    public String fly() {
        return "I can't fly..";
    }

}
