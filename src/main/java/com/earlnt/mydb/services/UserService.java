package com.earlnt.mydb.services;

import com.earlnt.mydb.entities.User;
import com.earlnt.mydb.repositories.ProfilesRepository;
import com.earlnt.mydb.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final ProfilesRepository profilesRepository;

    @Transactional
    public void showEntityState() {
        var user = User.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .password("password")
                .build();
        if(entityManager.contains(user))
            System.out.println("Persistent");
        else
            System.out.println("Transient/Detached");

        userRepository.save(user);

        if(entityManager.contains(user))
            System.out.println("Persistent");
        else
            System.out.println("Transient/Detached");
    }

    @Transactional
    public void showRelatedEntities(){
        //var user = userRepository.findById(2L).orElseThrow();
        //System.out.println(user.getEmail());

        var profile = profilesRepository.findById(2L).orElseThrow();
        System.out.println(profile.getUser().getEmail());
    }
}
