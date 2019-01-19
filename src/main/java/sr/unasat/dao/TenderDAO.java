package sr.unasat.dao;

import sr.unasat.entities.Status;
import sr.unasat.entities.Tender;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class TenderDAO {


    private EntityManager entityManager;

    public TenderDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    public void createTender(Tender tender ){
        entityManager.getTransaction().begin();
        entityManager.persist(tender);
        entityManager.getTransaction().commit();


    }

    public Tender selectTenderBy(int tender_id) {
        entityManager.getTransaction().begin();
        String jpql = "select t from Tender t where t.tender_id = :tender_id";
        TypedQuery<Tender> query = entityManager.createQuery(jpql, Tender.class);
        query.setParameter("tender_id", tender_id);
        Tender tender = query.getSingleResult();
        entityManager.getTransaction().commit();
        return tender;
    }
    public List<Tender> selectAllTender(){
        entityManager.getTransaction().begin();
        //hier begint transactie
        String jpql = "select t from Tender t";
        TypedQuery<Tender> query = entityManager.createQuery(jpql, Tender.class);
        List<Tender> tenderList = query.getResultList();

        entityManager.getTransaction().commit();
        //entityManager.close();
        return tenderList;
    }

    public void updateTender(Tender tender){
        entityManager.getTransaction().begin();
        entityManager.merge(tender);
        entityManager.getTransaction().commit();

    }

    public void deleteTender(Tender tender){
       if (tender.getApplication() == null){
           entityManager.getTransaction().begin();
           entityManager.remove(tender);
           entityManager.getTransaction().commit();
       }
    }




}
