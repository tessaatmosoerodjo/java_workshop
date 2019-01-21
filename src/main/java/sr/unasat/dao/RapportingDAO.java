package sr.unasat.dao;

import sr.unasat.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
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


    //Jaaroverzicht met goed/afgekeurd. Meest/minst goed en af
    public List<Application> selectJaaroverzicht() {
        entityManager.getTransaction().begin();
        String jpql = "select a from Application a where a.date between '2019-01-01' and '2019-12-31' ";
        TypedQuery<Application> query = entityManager.createQuery(jpql, Application.class);
        List<Application> applicationList = query.getResultList();
        entityManager.getTransaction().commit();
        return applicationList;
    }


    //afgekeurde per school
    public List<Application> selectDeclinedBySchool() {
        entityManager.getTransaction().begin();
        String jpql = "select a.application, a.education, a.student, a.status, a.tender, a.note from Application a group by a.education_id, a.status ";
        List<Object[]> results = entityManager.createQuery(jpql, Object[].class).getResultList();
        //query.setParameter("gender_id", );
        entityManager.getTransaction().commit();


        List<Application> applicationList = new ArrayList<>();

        for (Object[] row : results) {
            Application container = new Application();
            container.setApplication_id((int) row[0]);
            container.setEducation((Education) row[1]);
            container.setStudent((Student) row[2]);
            container.setStatus((Status) row[3]);
            container.setTender((Tender) row[4]);
            container.setNote((String) row[5]);

            applicationList.add(container);
        }

        return applicationList;
    }


}
