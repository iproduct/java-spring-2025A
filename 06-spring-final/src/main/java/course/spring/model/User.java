package course.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static course.spring.model.Role.READER;

@Entity
//@Table(name="USERS", uniqueConstraints = {
//        @UniqueConstraint(name="UC_USERNAME", columnNames={"USERNAME"}),
//        @UniqueConstraint(name="UC_FIST_LAST_NAME", columnNames={"FIRST_NAME", "LAST_NAME"})
//})
@Table(name="USERS", indexes = {
        @Index(name = "UC_USERNAME", columnList = "USERNAME", unique = true),
        @Index(name = "UC_NAMES", columnList = "FIRST_NAME,LAST_NAME",  unique = true),
})
public class User extends Person {
    @Basic(optional = false)
    @Column(  nullable = false, updatable = false, length = 30)
    @Size(min = 2, max = 30)
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role = READER;
    @Basic(optional = false)
    @Column(  nullable = false, length = 60)
    @Email
    private String email;
    private boolean active = true;
    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private List<Article> articles= Collections.emptyList();

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

//    public User(String firstName, String lastName, LocalDate dateOfBirth, String username, String password, Role role, String email, boolean active) {
//        super(firstName, lastName, dateOfBirth);
//        this.username = username;
//        this.password = password;
//        this.role = role;
//        this.email = email;
//        this.active = active;
//    }


    public User(long id, String firstName, String lastName, LocalDate dateOfBirth, String username, String password, Role role, String email, boolean active) {
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

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
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
