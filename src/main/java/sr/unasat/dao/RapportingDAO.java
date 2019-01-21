package sr.unasat.dao;

import sr.unasat.entities.Application;
import sr.unasat.entities.Education;
import sr.unasat.entities.Status;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class RapportingDAO {

    private EntityManager entityManager;

    public RapportingDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    //alle leningen met goedgekeurd
    public List<Application> selectAllApplicationByApproved(Status status) {
        entityManager.getTransaction().begin();
        String jpql = "select a from Application a where a.status = :status";
        TypedQuery<Application> query = entityManager.createQuery(jpql, Application.class);
        query.setParameter("status", status);
        List<Application> applicationList = query.getResultList();
        entityManager.getTransaction().commit();
        return applicationList;
    }

    //Jaaroverzicht met goed/afgekeurd.
    public List<Application> selectJaaroverzicht() {
        entityManager.getTransaction().begin();
        String jpql = "select a from Application a where a.date between '2019-01-01' and '2019-12-31' ";
        TypedQuery<Application> query = entityManager.createQuery(jpql, Application.class);
        List<Application> applicationList = query.getResultList();
        entityManager.getTransaction().commit();
        return applicationList;
    }

    // Meest declined
    public List<Object[]> findCountDeclined(Status status) {
        entityManager.getTransaction().begin();
        String jpql = "select count(a.status) from Application a where a.status = :status";
        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        query.setParameter("status", status);
        List<Object[]> applicationList = query.getResultList();
        entityManager.getTransaction().commit();
        return applicationList;
    }

    //meest approved
    public List<Object[]> findCountApproved(Status status) {
        entityManager.getTransaction().begin();
        String jpql = "select count(a.status) from Application a where a.status = :status";
        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        query.setParameter("status", status);
        List<Object[]> applicationList = query.getResultList();
        entityManager.getTransaction().commit();
        return applicationList;
    }

    //afgekeurde per school
    public List<Application> selectDeclinedBySchool(Education education, Status status) {
        entityManager.getTransaction().begin();
        String jpql = "select a from Application a where a.education= :education and a.status= :status ";
        TypedQuery<Application> query = entityManager.createQuery(jpql, Application.class);
        query.setParameter("education", education);
        query.setParameter("status", status);
        List<Application> results = query.getResultList();
        entityManager.getTransaction().commit();
        return results;
    }
}
