package java_8.objectDefinitions;

import java.util.Comparator;

public class LastNameComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return o1.getLastName().compareTo(o2.getLastName());
    }
}
