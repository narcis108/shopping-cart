package ro.ugal.licenta.model;

import ro.ugal.licenta.model.Utilizator;

import javax.persistence.*;
import javax.rmi.CORBA.Util;
import java.util.Collection;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role", unique = true)
    private String role;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "roles")
    private Collection<Utilizator> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Collection<Utilizator> getUsers() {
        return users;
    }

    public void setUsers(Collection<Utilizator> users) {
        this.users = users;
    }
}
