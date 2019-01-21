package sr.unasat.dao;

import sr.unasat.entities.Type;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class TypeDAO {


    private EntityManager entityManager;

    public TypeDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    public void createType(Type type){
        entityManager.getTransaction().begin();
        entityManager.persist(type);
        entityManager.getTransaction().commit();

    }

    public Type selectTypeByTypeEducation(String type_education) {
        entityManager.getTransaction().begin();
        String jpql = "select t from Type t where t.type_education = :type_education";
        TypedQuery<Type> query = entityManager.createQuery(jpql, Type.class);
        query.setParameter("type_education", type_education);
        Type type = query.getSingleResult();
        entityManager.getTransaction().commit();
        return type;
    }

    public List<Type> selectAllType(){
        entityManager.getTransaction().begin();
        //hier begint transactie
        String jpql = "select t from Type t";
        TypedQuery<Type> query = entityManager.createQuery(jpql, Type.class);
        List<Type> typeList = query.getResultList();
        entityManager.getTransaction().commit();
        return typeList;
    }

    public void updateType(Type type){
        entityManager.getTransaction().begin();
        entityManager.merge(type);
        entityManager.getTransaction().commit();
    }

    public void deleteTypeByTypeEducation(Type type){
        entityManager.getTransaction().begin();
        entityManager.remove(type);
        entityManager.getTransaction().commit();
    }
}
