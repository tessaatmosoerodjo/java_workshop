package sr.unasat.dao;

import sr.unasat.entities.Gender;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class GenderDAO {


    private EntityManager entityManager;

    public GenderDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void createGender(Gender gender) {
        entityManager.getTransaction().begin();
        entityManager.persist(gender);
        entityManager.getTransaction().commit();
    }


    public Gender selectGenderById(int gender_id) {
        entityManager.getTransaction().begin();
        String jpql = "select g from Gender g where g.gender_id = :gender_id";
        TypedQuery<Gender> query = entityManager.createQuery(jpql, Gender.class);
        query.setParameter("gender_id", gender_id);
        Gender gender = query.getSingleResult();
        entityManager.getTransaction().commit();
        return gender;
    }

    public Gender selectGenderByName(String gender_name) {
        entityManager.getTransaction().begin();
        String jpql = "select g from Gender e where g.gender_name = :gender_name";
        TypedQuery<Gender> query = entityManager.createQuery(jpql, Gender.class);
        query.setParameter("gender_name", gender_name);
        Gender gender = query.getSingleResult();
        entityManager.getTransaction().commit();
        return gender;
    }


    public List<Gender> selectAllGender() {
        entityManager.getTransaction().begin();
        //hier begint transactie
        String jpql = "select g from Gender g";
        TypedQuery<Gender> query = entityManager.createQuery(jpql, Gender.class);
        List<Gender> genderList = query.getResultList();

        entityManager.getTransaction().commit();
        // entityManager.close();
        return genderList;
    }

    public void updateGender(Gender gender) {
        entityManager.getTransaction().begin();
        entityManager.merge(gender);
        entityManager.getTransaction().commit();

    }

    public void deleteGender(Gender gender) {
        entityManager.getTransaction().begin();
        entityManager.remove(gender);
        entityManager.getTransaction().commit();
    }
}
