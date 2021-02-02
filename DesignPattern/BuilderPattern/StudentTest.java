package BuilderPattern;

public class StudentTest {
    public static void main(String[] args) {
        /*
         * Student s1 = new Student(); s1.setFirstName("Berry");
         * s1.setLastName("Allen"); s1.setGender('m'); System.out.println(s1);
         * 
         * Student s2 = new Student("Iris", "West", 0, 'f'); System.out.println(s2);
         */
        Student s1 = new Student.Builder().setFirstName("star").setAge(213).setLastName("tes").build();

    }
}
