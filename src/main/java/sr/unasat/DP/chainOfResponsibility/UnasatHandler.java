package sr.unasat.DP.chainOfResponsibility;

import sr.unasat.dao.EducationDAO;
import sr.unasat.entities.Education;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UnasatHandler extends Handler {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCE");
    EntityManager entityManager = emf.createEntityManager();

    @Override
    public Education handleRequest(Request request) {
        // int requestSender = Integer.valueOf(request.getValue());
        if (request.getValue().equals("2")) {           //if request is eligible handle it

            EducationDAO educationDAO = new EducationDAO(entityManager);
            Education education = educationDAO.selectEducationByEducationName("UNASAT");
            return education;
        }
        return null;
    }
}


