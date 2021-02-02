package BuilderPattern;

public class Student {
    private String firstName;
    private String lastName;
    private int age;
    private char gender;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return this.gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Student() {
    }

    private Student(String firstName, String lastName, int age, char gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "{" + " firstName='" + getFirstName() + "'" + ", lastName='" + getLastName() + "'" + ", age='" + getAge()
                + "'" + ", gender='" + getGender() + "'" + "}";
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private int age;
        private char gender;

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setGender(char gender) {
            this.gender = gender;
            return this;
        }

        public Student build() {
            return new Student(this.firstName, this.lastName, this.age, this.gender);
        }
    }
}
