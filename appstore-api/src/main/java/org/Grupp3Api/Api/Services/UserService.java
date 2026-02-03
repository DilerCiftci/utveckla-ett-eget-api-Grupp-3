package org.Grupp3Api.Api.Services;

import java.util.List;
import java.util.UUID;

import org.Grupp3Api.Api.Entity.User;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;


@Transactional(Transactional.TxType.SUPPORTS)
@ApplicationScoped
public class UserService {
    
    @Inject
    EntityManager em;

    UUID uuid;

    @Transactional(Transactional.TxType.REQUIRED)
    public User registerUser(User user){
        user.setApiKey(UUID.randomUUID());
        em.persist(user);
        return user;
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public User getUserByUsername(String username){

        return em.find(User.class, username);
    }

        @Transactional(Transactional.TxType.REQUIRED)
    public User getUserById(Long id){

        return em.find(User.class, id);
    }

        @Transactional(Transactional.TxType.REQUIRED)
    public User generateNewApiKey(Long id){

        User user = em.find(User.class, id);
        user.setApiKey(UUID.randomUUID());
        return user;
    }

    public List<User> findAll(){

        List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();

        return users;
    }

}
