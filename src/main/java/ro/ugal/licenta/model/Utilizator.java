package ro.ugal.licenta.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Data
@Entity
public class Utilizator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUtilizator;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Te rugam sa introduci un email valid!")
    @NotEmpty(message = "Te rugam sa introduci un email valid!")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @OneToOne(mappedBy = "utilizator")
    private Client client;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id_utilizator"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;
}
