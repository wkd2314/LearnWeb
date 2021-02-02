package PrototypePattern;

public class StudentTest {
    public static void main(String[] args) {
        /*
         * Student s1 = new Student("Barry", "male", "St. Josephs"); Student s2 = new
         * Student("Harry", "male", "St. Josephs"); Student s3 = new Student("Wally",
         * "male", "St. Josephs");
         */
        Student prototype = new Student(); // 초기, 원형.
        prototype.setGender("male");
        prototype.setSchool("St. Josephs");

        // Student s1 = prototype; // reference. not the data copy
        Student s1 = prototype.clone();
        s1.setName("Barry");
        Student s2 = prototype.clone();
        s2.setName("Harry");

        System.out.println(s1);
        System.out.println(s2);
    }
}
