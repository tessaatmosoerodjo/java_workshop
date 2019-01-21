package sr.unasat.DP.chainOfResponsibility;

import sr.unasat.dao.EducationDAO;
import sr.unasat.entities.Education;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FhrHandler extends Handler {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCE");
    EntityManager entityManager = emf.createEntityManager();

    @Override
    public Education handleRequest(Request request) {
        //Integer.valueOf(request.getValue()) == 3
        if (request.getValue().equals("3")) {           //if request is eligible handle it

            EducationDAO educationDAO = new EducationDAO(entityManager);
            Education education = educationDAO.selectEducationByEducationName("FHR");
            return education;
        } else {
            handleRequest(request);
        }
        return null;
    }
}
