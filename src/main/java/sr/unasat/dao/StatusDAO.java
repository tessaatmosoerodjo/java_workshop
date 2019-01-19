package sr.unasat.dao;

import sr.unasat.entities.Status;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class StatusDAO {

    private EntityManager entityManager;

    public StatusDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void createStatus(Status status ){
        entityManager.getTransaction().begin();
        entityManager.persist(status);
        entityManager.getTransaction().commit();
    }


    public Status selectStatusById(int status_id) {
        entityManager.getTransaction().begin();
        String jpql = "select s from Status s where s.status_id = :status_id";
        TypedQuery<Status> query = entityManager.createQuery(jpql, Status.class);
        query.setParameter("status_id", status_id);
        Status status = query.getSingleResult();
        entityManager.getTransaction().commit();
        return status;
    }

    public Status selectAddressByStatus(String status) {
        entityManager.getTransaction().begin();
        String jpql = "select s from Status s where s.status = :status";
        TypedQuery<Status> query = entityManager.createQuery(jpql, Status.class);
        query.setParameter("status", status);
        Status selectStatus = query.getSingleResult();
        entityManager.getTransaction().commit();
        return selectStatus;
    }

    public List<Status> selectAllStatus(){
        entityManager.getTransaction().begin();
        //hier begint transactie
        String jpql = "select s from Status s";
        TypedQuery<Status> query = entityManager.createQuery(jpql, Status.class);
        List<Status> statusList = query.getResultList();
        entityManager.getTransaction().commit();
        //entityManager.close();
        return statusList;
    }

    public void updateStatus(Status status){
        entityManager.getTransaction().begin();
        entityManager.merge(status);
        entityManager.getTransaction().commit();
    }

    public void deleteAddressByStatus(Status status){
            entityManager.getTransaction().begin();
            entityManager.remove(status);
            entityManager.getTransaction().commit();

    }
}
