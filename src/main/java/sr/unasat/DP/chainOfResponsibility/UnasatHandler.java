package sr.unasat.DP.chainOfResponsibility;

import sr.unasat.dao.EducationDAO;
import sr.unasat.entities.Application;
import sr.unasat.entities.Education;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UnasatHandler extends Handler {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCE");
    EntityManager entityManager = emf.createEntityManager();

    @Override
    protected int getOption() {
        return 2;
    }

    @Override
    protected Application getMethod(Application application) {
        EducationDAO educationDAO = new EducationDAO(entityManager);
        Education education = educationDAO.selectEducationByEducationName("UNASAT");
        application.setEducation(education);
        return application;
    }
}


