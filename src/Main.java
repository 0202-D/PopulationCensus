import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Dm.Petrov
 * DATE: 02.07.2022
 */
public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        Long minor = persons.stream().filter(person -> person.getAge() < 18).count();
        System.out.println(minor);
        List<String> conscripts = persons.stream().filter(el -> el.getSex() == Sex.MAN)
                .filter(el -> el.getAge() > 17 && el.getAge() < 27).map(Person::getFamily).collect(Collectors.toList());
        System.out.println(conscripts);
        List<Person> ableBodiedPeopleWithHigherEducation = persons.stream().filter(el -> el.getEducation() == Education.HIGHER)
                .filter(el -> {
                    if (el.getSex() == Sex.WOMAN) {
                        return el.getAge() > 17 && el.getAge() < 60;
                    } else {
                        return el.getAge() > 17 && el.getAge() < 65;
                    }
                })
                .sorted(Comparator.comparing(Person::getFamily)).collect(Collectors.toList());


    }
}
