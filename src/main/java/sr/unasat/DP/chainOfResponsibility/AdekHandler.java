package sr.unasat.DP.chainOfResponsibility;

import sr.unasat.dao.EducationDAO;
import sr.unasat.entities.Application;
import sr.unasat.entities.Education;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AdekHandler extends Handler {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCE");
    EntityManager entityManager = emf.createEntityManager();

    @Override
    protected int getOption() {
        return 1;
    }

    @Override
    protected Application getMethod(Application application) {
        EducationDAO educationDAO = new EducationDAO(entityManager);
        Education education = educationDAO.selectEducationByEducationName("ADEK");
        application.setEducation(education);
        return application;
    }

  /*  @Override
    public Education handleRequest(Request request) {
        if (request.getValue().equals("1")) {           //if request is eligible handle it

            EducationDAO educationDAO = new EducationDAO(entityManager);
            Education education = educationDAO.selectEducationByEducationName("ADEK");
            return education;
        }
        return null;

    }*/
}
