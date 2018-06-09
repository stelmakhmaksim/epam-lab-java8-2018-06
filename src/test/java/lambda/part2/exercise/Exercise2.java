package lambda.part2.exercise;

import lambda.data.Person;
import org.junit.Test;

import java.util.function.Predicate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SuppressWarnings({"ConstantConditions", "unused"})
public class Exercise2 {

    @Test
    public void personHasNotEmptyLastNameAndFirstName() {
        Predicate<Person> validate = person -> !person.getFirstName().isEmpty()
            && !person.getLastName().isEmpty();

        assertTrue(validate.test(new Person("Алексей", "Доренко", 40)));
        assertFalse(validate.test(new Person("Николай", "", 30)));
        assertFalse(validate.test(new Person("", "Мельников", 20)));
    }

    private Predicate<Person> negateUsingLogicalOperator(Predicate<Person> predicate) {
        return t -> !predicate.test(t);
    }

    private Predicate<Person> andUsingLogicalOperator(Predicate<Person> left,
                                                      Predicate<Person> right) {
        return t -> right.test(t) && left.test(t);
    }

    @Test
    public void personHasNotEmptyLastNameAndFirstNameUsingLogicalOperators() {
        Predicate<Person> personHasEmptyFirstName = person -> person.getFirstName().isEmpty();
        Predicate<Person> personHasEmptyLastName = person -> person.getLastName().isEmpty();

        Predicate<Person> personHasNotEmptyFirstName = negateUsingLogicalOperator(
            personHasEmptyFirstName);
        Predicate<Person> personHasNotEmptyLastName = negateUsingLogicalOperator(
            personHasEmptyLastName);

        Predicate<Person> personHasNotEmptyLastNameAndFirstName = andUsingLogicalOperator(
            personHasNotEmptyFirstName, personHasNotEmptyLastName);

        assertTrue(
            personHasNotEmptyLastNameAndFirstName.test(new Person("Алексей", "Доренко", 40)));
        assertFalse(personHasNotEmptyLastNameAndFirstName.test(new Person("Николай", "", 30)));
        assertFalse(personHasNotEmptyLastNameAndFirstName.test(new Person("", "Мельников", 20)));
    }

    // TODO метод (T -> boolean) -> (T -> boolean)
    // TODO - возвращает новый предикат, являющийся отрицанием исходного
    // TODO - при реализации использовать логический оператор !
    private <T> Predicate<T> negate(Predicate<T> predicate) {
        return t -> !predicate.test(t);
    }

    // TODO метод (T -> boolean, T -> boolean) -> (T -> boolean)
    // TODO - возвращает новый предикат, объединяющий исходные с помощью операции "AND"
    // TODO - при реализации использовать логический оператор &&
    private <T> Predicate<T> and(Predicate<T> left, Predicate<T> right) {
        return t -> left.test(t) && right.test(t);
    }

    @Test
    public void personHasNotEmptyLastNameAndFirstNameUsingGenericPredicates() {
        Predicate<Person> personHasEmptyFirstName = person -> person.getFirstName().isEmpty();
        Predicate<Person> personHasEmptyLastName = person -> person.getLastName().isEmpty();

        Predicate<Person> personHasNotEmptyFirstName = negate(personHasEmptyFirstName);
        Predicate<Person> personHasNotEmptyLastName = negate(personHasEmptyLastName);

        Predicate<Person> personHasNotEmptyLastNameAndFirstName = and(personHasNotEmptyFirstName,
            personHasNotEmptyLastName);

        assertTrue(
            personHasNotEmptyLastNameAndFirstName.test(new Person("Алексей", "Доренко", 40)));
        assertFalse(personHasNotEmptyLastNameAndFirstName.test(new Person("Николай", "", 30)));
        assertFalse(personHasNotEmptyLastNameAndFirstName.test(new Person("", "Мельников", 20)));
    }

    @Test
    public void personHasNotEmptyLastNameAndFirstNameUsingStandardMethods() {
        Predicate<Person> personHasEmptyFirstName = person -> person.getFirstName().isEmpty();
        Predicate<Person> personHasEmptyLastName = person -> person.getLastName().isEmpty();

        Predicate<Person> personHasNotEmptyFirstName = personHasEmptyFirstName.negate();
        Predicate<Person> personHasNotEmptyLastName = personHasEmptyLastName.negate();

        Predicate<Person> personHasNotEmptyLastNameAndFirstName = personHasNotEmptyFirstName
            .and(personHasNotEmptyLastName);

        assertTrue(
            personHasNotEmptyLastNameAndFirstName.test(new Person("Алексей", "Доренко", 40)));
        assertFalse(personHasNotEmptyLastNameAndFirstName.test(new Person("Николай", "", 30)));
        assertFalse(personHasNotEmptyLastNameAndFirstName.test(new Person("", "Мельников", 20)));
    }
}