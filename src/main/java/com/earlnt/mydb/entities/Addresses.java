package com.earlnt.mydb.entities;
import jakarta.persistence.*;
import lombok.*;

@ToString
@Builder
@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Addresses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "zip", nullable = false)
    private String zipCode;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
