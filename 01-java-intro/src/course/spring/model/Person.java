package course.spring.model;

import java.time.LocalDate;
import java.util.Objects;

public class Person implements Identifiable<Long> {
    private static Long nextId = 0L;
    public static Long getNextId() {
        return ++nextId;
    }

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth = LocalDate.now();

    public Person() {
        id = getNextId();
    }

    public Person(String firstName, String lastName) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(String firstName, String lastName, LocalDate dateOfBirth) {
        this(firstName, lastName);
        this.dateOfBirth = dateOfBirth;
    }

    public Person(Long id, String firstName, String lastName, LocalDate dateOfBirth) {
        this(firstName, lastName, dateOfBirth);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", dateOfBirth=").append(dateOfBirth);
        sb.append('}');
        return sb.toString();
    }
}
