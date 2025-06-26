package course.spring.model;

import jakarta.persistence.Entity;

import java.time.LocalDate;

import static course.spring.model.Role.READER;

@Entity
public class User extends Person {
    private String username;
    private String password;
    private Role role = READER;
    private String email;
    private boolean active = true;

    public User() {
    }

    public User(String firstName, String lastName, LocalDate dateOfBirth, String username, String password, String email) {
        super(firstName, lastName, dateOfBirth);
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String firstName, String lastName, String username, String password) {
        super(firstName, lastName);
        this.username = username;
        this.password = password;
    }

    public User(String firstName, String lastName, LocalDate dateOfBirth, String username, String password, Role role, String email) {
        super(firstName, lastName, dateOfBirth);
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public User(String firstName, String lastName, LocalDate dateOfBirth, String username, String password, Role role, String email, boolean active) {
        super(firstName, lastName, dateOfBirth);
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.active = active;
    }

    public User(Long id, String firstName, String lastName, LocalDate dateOfBirth, String username, String password,
                Role role, String email, boolean active) {
        super(id, firstName, lastName, dateOfBirth);
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", firstName='"+ getFirstName() +'\''+
                ", lastName='"+ getLastName() +'\''+
                ", dateOfBirth=" + getDateOfBirth() +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", active=" + active +
                "} ";
    }
}
