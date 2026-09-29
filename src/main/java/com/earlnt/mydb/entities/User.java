package com.earlnt.mydb.entities;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<Addresses> addresses = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private Profiles profiles;

    public void addAddress(Addresses address) {
        addresses.add(address);
        address.setUser(this);
    }

    public void removeAddress(Addresses address) {
        addresses.remove(address);
        address.setUser(null);
    }
}
