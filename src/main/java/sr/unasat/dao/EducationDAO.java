package sr.unasat.dao;

import sr.unasat.entities.Education;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EducationDAO {

    private EntityManager entityManager;

    public EducationDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    public void createEducation(Education education){
        entityManager.getTransaction().begin();
        entityManager.persist(education);
        entityManager.getTransaction().commit();
    }


    public Education selectEducationBy(int education_id) {
        entityManager.getTransaction().begin();
        String jpql = "select e from Education e where e.education_id = :education_id";
        TypedQuery<Education> query = entityManager.createQuery(jpql, Education.class);
        query.setParameter("education_id", education_id);
        Education education = query.getSingleResult();
        entityManager.getTransaction().commit();
        return education;
    }
    public Education selectEducationByEducationName(String education_name) {
        entityManager.getTransaction().begin();
        String jpql = "select e from Education e where e.education_name = :education_name";
        TypedQuery<Education> query = entityManager.createQuery(jpql, Education.class);
        query.setParameter("education_name", education_name);
        Education education = query.getSingleResult();
        entityManager.getTransaction().commit();
        return education;
    }


    public List<Education> selectAllEducation(){
        entityManager.getTransaction().begin();
        //hier begint transactie
        String jpql = "select t from Address t";
        TypedQuery<Education> query = entityManager.createQuery(jpql, Education.class);
        List<Education> educationList = query.getResultList();

        entityManager.getTransaction().commit();
       // entityManager.close();
        return educationList;
    }

    public void updateEducation(Education education){
        entityManager.getTransaction().begin();
        entityManager.merge(education);
        entityManager.getTransaction().commit();

    }

    public void deleteEducation(Education education){
        entityManager.getTransaction().begin();
        entityManager.remove(education);
        entityManager.getTransaction().commit();
    }
}


