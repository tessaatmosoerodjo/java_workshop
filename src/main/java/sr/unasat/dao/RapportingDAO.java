package sr.unasat.dao;

import sr.unasat.entities.Address;
import sr.unasat.entities.Application;
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

    public List<Application> selectJaaroverzicht(int month, int year) {
        entityManager.getTransaction().begin();
        String jpql = "SELECT a FROM Application a " +
                "WHERE FUNCTION('MONTH', a.date) = :month " +
                "AND FUNCTION('YEAR', a.date) = :year ";
        TypedQuery<Application> query = entityManager.createQuery(jpql, Application.class);
        query.setParameter("month", month);
        query.setParameter("year", year);
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
    public List<Application> findAllSchoolApplications() {
        entityManager.getTransaction().begin();
        String jpql = "select a from Application a order by education desc, status";
        //order by school status
        TypedQuery<Application> query = entityManager.createQuery(jpql, Application.class);
        List<Application> results = query.getResultList();
        entityManager.getTransaction().commit();
        return results;
    }

    public void printMonthlyOverview(List<Application> applicationList1) {

        for (Application application : applicationList1) {
            System.out.println("");
            System.out.println("-------------BEGIN----------------");
            System.out.println("DATE OF APPLICATION: " + application.getDate());
            System.out.println("APPLICATION ID: " + application.getApplication_id());
            System.out.println("-----------------------------");
            System.out.println("STUDENT FULL NAME: " + application.getStudent().getFirstName() + " " + application.getStudent().getLastName());
            System.out.println("STUDENT DATE OF BIRTH: " + application.getStudent().getDate_of_birth());
            System.out.println("STUDENT TELEPHONE NUMBER: " + application.getStudent().getTelephone_number());
            System.out.println("STUDENT GENDER: " + application.getStudent().getGender());
            System.out.println("-----------------------------");
            for (Address address : application.getStudent().getAddress()) {
                System.out.println("STUDENT ADDRESS ");
                System.out.println("STUDENT DISTRICT: " + address.getDistrict());
                System.out.println("STUDENT STREETNAME: " + address.getStreetname());
                System.out.println("-----------------------------");
            }
            System.out.println("EDUCATION TITLE: " + application.getEducation().getTitle());
            System.out.println("EDUCATION NAME: " + application.getEducation().getEducation_name());
            System.out.println("EDUCATION AMOUNT: " + application.getEducation().getAmount());
            System.out.println("EDUCATION TYPE: " + application.getEducation().getType().getType_education());
            System.out.println("-----------------------------");
            System.out.println("TENDER DESCRIPTION: " + application.getTender().getTender_description());
            System.out.println("-----------------------------");
            System.out.println("STATUS: " + application.getStatus().getStatus());
            System.out.println("NOTE: " + application.getNote());
            System.out.println("-------------END----------------");
            System.out.println("");
        }
        StatusDAO statusDAO = new StatusDAO(entityManager);
        RapportingDAO rapportingDAO = new RapportingDAO(entityManager);

        System.out.println("Most Approved application");
        Status statusApproved1 = statusDAO.selectAddressByStatus("APPROVED");
        List<Object[]> application4 = rapportingDAO.findCountApproved(statusApproved1);
        System.out.println("Approved application" + application4);

        System.out.println("Most Declined application");
        Status statusDeclined1 = statusDAO.selectAddressByStatus("DECLINED");
        List<Object[]> application5 = rapportingDAO.findCountDeclined(statusDeclined1);
        System.out.println("Approved declined" + application5);


    }


}
