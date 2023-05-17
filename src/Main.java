import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static final long START_NUM_PERSONS = 100_000;
    private static final long DELTA_NUM_PERSONS = 500_000;

    public static void main(String[] args) {
        long np = START_NUM_PERSONS;

        for (int i = 1; i <= 50; i++) {
            run(np);

            np += DELTA_NUM_PERSONS;
        }
    }

    private static void run(long numPersons) {
        List<Person> persons = new ArrayList<>();
        Random r = new Random();

        System.out.println("Run with " + (double) numPersons / 1_000_000 + " million persons:");

        // create random person list
        for (int i = 1; i <= numPersons; i++) {
            Person p = new Person("AAA", r.nextInt(100));
            persons.add(p);
        }

        // classic approach
        long start1 = System.currentTimeMillis();
        double sum = 0;
        for (Person pers : persons) {
            sum += pers.getAge();
        }
        double avg1 = sum / persons.size();
        long finish1 = System.currentTimeMillis();
        long timeElapsed1 = finish1 - start1;
        System.out.println("Classic avg: " + avg1 + " time elapsed: " + timeElapsed1);

        // map-reduce
        long start2 = System.currentTimeMillis();
        double avg2 = persons.parallelStream().mapToInt(Person::getAge).average().getAsDouble();
        long finish2 = System.currentTimeMillis();
        long timeElapsed2 = finish2 - start2;
        System.out.println("Map-reduce avg: " + avg2 + " time elapsed: " + timeElapsed2);

        System.out.println();
    };
}
