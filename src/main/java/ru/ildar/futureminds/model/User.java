package ru.ildar.futureminds.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.ildar.futureminds.dto.Role;

import java.sql.Date;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 4, max = 32)
    private String email;
    @Size(min = 4, max = 32)
    private String password;
    @Column(name = "first_name")
    @Size(min = 2, max = 32)
    @NotNull
    private String firstName;
    @Column(name = "last_name")
    @Size(max = 32)
    private String lastName;
    @Column(name = "middle_name")
    @Size(max = 32)
    private String middleName;
    @Column(name =  "birthday")
    private Date birthday;
    @Column(name = "roles")
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<UserCourse> courses;

    public User(String email, String password, String firstName, String lastName,
                String middleName, Date birthday, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthday = birthday;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthday=" + birthday +
                ", roles=" + roles + '}';
    }
}
