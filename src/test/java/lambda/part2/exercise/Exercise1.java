package lambda.part2.exercise;

import lambda.data.Person;
import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SuppressWarnings({"unused", "ConstantConditions"})
public class Exercise1 {

    @Test
    public void ageExtractorFromPersonUsingMethodReference() {
        Person person = new Person("Иван", "Мельников", 33);

        // TODO создать переменную ageExtractor: Person -> Integer, используя Function и ссылку на метод

        Function<Person, Integer> ageExtractor = Person::getAge;
        assertEquals(33, ageExtractor.apply(person).intValue());

        // FIXME удалить при реализации
//        throw new UnsupportedOperationException("Not implemented");
    }

    @Test
    public void sameAgesCheckerUsingBiPredicate() {
        Person person1 = new Person("Иван", "Мельников", 33);
        Person person2 = new Person("Дмитрий", "Гущин", 33);
        Person person3 = new Person("Илья", "Жирков", 22);

        // TODO создать переменную sameAgesChecker: (Person, Person) -> boolean, используя BiPredicate
        BiPredicate<Person, Person> sameAgesChecker = (p1, p2) -> p1.getAge() == p2.getAge();
         assertTrue(sameAgesChecker.test(person1, person2));
         assertFalse(sameAgesChecker.test(person1, person3));
         assertFalse(sameAgesChecker.test(person2, person3));

        // FIXME удалить при реализации
//        throw new UnsupportedOperationException("Not implemented");
    }

    // TODO метод getFullName: Person -> String, извлекающий из объекта Person строку в формате "имя фамилия".
     private static String getFullName(Person person) {
         return person.getFullName();
     }

    // TODO метод createExtractorAgeOfPersonWithTheLongestFullName: (Person -> String) -> ((Person, Person) -> int),
    // TODO - принимающий способ извлечения полного имени из объекта Person
    // TODO - возвращающий BiFunction, сравнивающий два объекта Person и возвращающий возраст того, чье полное имя длиннее.
     private static BiFunction<Person, Person, Integer> createExtractorAgeOfPersonWithTheLongestFullName(
             Function<Person, String> personStringFunction) {
         return (p1,p2) -> personStringFunction.apply(p1).length() >= personStringFunction.apply(p2).length()
                 ? p1.getAge()
                 : p2.getAge();
     }

    @Test
    public void getAgeOfPersonWithTheLongestFullName() {
        Person person1 = new Person("Иван", "Мельников", 33);
        Person person2 = new Person("Илья", "Жирков", 22);

        // TODO воспользоваться ссылкой на метод getFullName
        Function<Person, String> getFullName = Person::getFullName;

        // (Person, Person) -> Integer
        // TODO воспользоваться методом createExtractorAgeOfPersonWithTheLongestFullName
        BiFunction<Person, Person, Integer> extractorAgeOfPersonWithTheLongestFullName = createExtractorAgeOfPersonWithTheLongestFullName(getFullName);

        assertEquals(33, extractorAgeOfPersonWithTheLongestFullName.apply(person1, person2).intValue());
    }
}
