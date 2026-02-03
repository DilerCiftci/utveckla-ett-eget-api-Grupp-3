package org.Grupp3Api.Api.Services;

import java.util.List;
import org.Grupp3Api.Api.Entity.App;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Transactional(Transactional.TxType.SUPPORTS)
@ApplicationScoped
public class AppService {
    
    @Inject
    EntityManager em;

     public List<App> findAll() {
        List<App> app = em.createQuery("SELECT a FROM App a", App.class).getResultList();
        return app;
    }

    public App find(Long id) {
        return em.find(App.class, id);
    }

    public Long countAll() {
        return em.createQuery("SELECT COUNT(a) FROM App a", Long.class).getSingleResult();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public App create(App app) {
        em.persist(app);
        return app;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(Long id) {
        em.remove(em.getReference(App.class, id));
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public App update(App app) {
        return em.merge(app);
    }
}
