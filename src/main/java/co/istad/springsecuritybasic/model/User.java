package co.istad.springsecuritybasic.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 100, nullable = false,unique = true)
    private String email;
    @Column(length = 100, nullable = false)
    private String password;
    private boolean isDisabled;
    private boolean isAccountLocked;
    private boolean isAccountExpired;
    private boolean isCredentialsExpired;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<Role> roles;

}
