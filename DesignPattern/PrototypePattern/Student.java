package PrototypePattern;

public class Student implements Cloneable {
    private String name;
    private String gender;
    private String school;

    public Student clone() {
        Student s = null;
        try {
            s = (Student) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public String toString() {
        return "{" + " name='" + getName() + "'" + ", gender='" + getGender() + "'" + ", school='" + getSchool() + "'"
                + ", ref='" + hashCode() + "'" + "}";
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSchool() {
        return this.school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Student() {
    }

    public Student(String name, String gender, String school) {
        this.name = name;
        this.gender = gender;
        this.school = school;
    }
}
