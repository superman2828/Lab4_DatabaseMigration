package com.earlnt.mydb;

import com.earlnt.mydb.entities.Addresses;
import com.earlnt.mydb.entities.User;
import com.earlnt.mydb.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MyDbApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(MyDbApplication.class, args);
        var repository = context.getBean(UserRepository.class);
        var user = User.builder()
                //.id(1L)
                .name("John")
                .email("john@example.com")
                .password("password")
                .build();

        /*var addresses = Addresses.builder()
                .id(1L)
                .street("123 Main St")
                .city("Anytown")
                .state("CA")
                .zipCode("12345")
                .build();
        user.addAddress(addresses);*/

        //repository.save(user);

        var user1 = repository.findById(2L).orElseThrow();
        System.out.println(user1.getEmail());

        var users = repository.findAll();
        users.forEach(u -> System.out.println(u.getEmail()));

        repository.deleteById(1L);
    }
}
