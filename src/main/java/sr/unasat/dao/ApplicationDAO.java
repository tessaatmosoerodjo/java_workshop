package sr.unasat.dao;

import sr.unasat.App.App;
import sr.unasat.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ApplicationDAO {

    private EntityManager entityManager;

    public ApplicationDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    public void createApplication(Application application){
        entityManager.getTransaction().begin();
        entityManager.persist(application);
        entityManager.getTransaction().commit();

    }

    public Application selectApplicationById(int application_id) {
        entityManager.getTransaction().begin();
        String jpql = "select a from Application a where a.application_id = :application_id";
        TypedQuery<Application> query = entityManager.createQuery(jpql, Application.class);
        query.setParameter("application_id", application_id);
        Application application = query.getSingleResult();
        entityManager.getTransaction().commit();
        return application;
    }


    public List<Application> selectAllApplicationByStatus(String status) {
        entityManager.getTransaction().begin();
        String jpql = "select s from Status s where s.status.status = :status";
        TypedQuery<Application> query = entityManager.createQuery(jpql, Application.class);
        query.setParameter("status", status);
        List<Application> applicationList = query.getResultList();
        entityManager.getTransaction().commit();
        return applicationList;
    }



    public List<Application> selectAllApplication(){
        entityManager.getTransaction().begin();
        //hier begint transactie
        String jpql = "select a from Application a";
        TypedQuery<Application> query = entityManager.createQuery(jpql, Application.class);
        List<Application> applicationList = query.getResultList();

        entityManager.getTransaction().commit();
        //entityManager.close();
        return applicationList;
    }

    public void updateEducation(Application application){
        entityManager.getTransaction().begin();
        entityManager.merge(application);
        entityManager.getTransaction().commit();

    }

    public void deleteApplicationById(int application_id){
        Application application = selectApplicationById(application_id);
        if (application != null){
            entityManager.remove(application);
        }
    }





}
