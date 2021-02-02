package BuilderPattern;

public class Robot implements RobotPlan {

    private String robotHead;
    private String robotTorso;
    private String robotArms;
    private String robotLegs;

    public String getRobotHead() {
        return this.robotHead;
    }

    public void setRobotHead(String robotHead) {
        this.robotHead = robotHead;
    }

    public String getRobotTorso() {
        return this.robotTorso;
    }

    public void setRobotTorso(String robotTorso) {
        this.robotTorso = robotTorso;
    }

    public String getRobotArms() {
        return this.robotArms;
    }

    public void setRobotArms(String robotArms) {
        this.robotArms = robotArms;
    }

    public String getRobotLegs() {
        return this.robotLegs;
    }

    public void setRobotLegs(String robotLegs) {
        this.robotLegs = robotLegs;
    }

}
