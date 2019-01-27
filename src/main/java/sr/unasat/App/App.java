package sr.unasat.App;


import sr.unasat.DP.chainOfResponsibility.*;
import sr.unasat.DP.decorator.InsufficientPapersReason;
import sr.unasat.DP.decorator.NoCollateralReason;
import sr.unasat.DP.decorator.Reason;
import sr.unasat.DP.decorator.ReasonDecorator;
import sr.unasat.DP.factory.StudentFactory;
import sr.unasat.dao.*;
import sr.unasat.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class App {


    public static void main(String[] args) throws ParseException {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCE");
        EntityManager entityManager = emf.createEntityManager();

        EducationDAO educationDAO = new EducationDAO(entityManager);
        StudentDAO studentDAO = new StudentDAO(entityManager);
        StatusDAO statusDAO = new StatusDAO(entityManager);

        ApplicationDAO applicationDAO = new ApplicationDAO(entityManager);
        Application application1 = new Application();


        //    SCANNER
        Scanner userInput = new Scanner(System.in);
        //System.in =the standard input stream

        System.out.println();
        System.out.println("***SELECT OPTIONS***");
        System.out.println("| 1. SEND NEW REQUEST" + ""
                + " | 2. SEARCH APPLICATION " + ""
                + " | 3. DELETE WRONG REGISTRATION " + ""
                + " | 4. GET RAPPORT " + "");
        System.out.println("ENTER OPTION NUMBER: ");
        System.out.print(">");

        String newOrExistingApp = userInput.next();
        if (newOrExistingApp.equals("1")) {
            System.out.println("CREATING NEW REQUEST");
            application1.setDate(new Date());
            System.out.println("IS STUDENT REGISTERED? (Y/N)");
            System.out.print(">");
            String studentRegistered = userInput.next();
            if (studentRegistered.equals("Y")) {
                System.out.println("SEARCH REGISTERED STUDENT BY FIRSTNAME");
                System.out.print(">");
                String searchStudent = userInput.next();
                Student foundStudent = studentDAO.selectStudentByFirstName(searchStudent);
                if (foundStudent != null) {
                    System.out.println("STUDENT " + searchStudent + " FOUND");
                    application1.setStudent(foundStudent);
                } else {
                    System.out.println("STUDENT NOT FOUND");
                }

                //EDUCATION FOUND STUDENT
                Handler adekHandler = new AdekHandler();
                Handler unasatHandler = new UnasatHandler();
                Handler fhrHandler = new FhrHandler();

                adekHandler.setSuccessor(unasatHandler);
                unasatHandler.setSuccessor(fhrHandler);

                System.out.println();
                System.out.println("***SELECT OPTIONS***");
                System.out.println("| 1. ADEK" + ""
                        + " | 2. UNASAT " + ""
                        + " | 3. FHR " + "");
                System.out.println("ENTER OPTION NUMBER: ");
                System.out.print(">");
                Integer option = userInput.nextInt();

                Application application = adekHandler.processRequest(new Request(option), application1);

                if (application.getEducation() != null) {
                    System.out.println("SCHOOL " + application.getEducation().getEducation_name() + " FOUND");
                    application1 = application;
                } else {
                    System.out.println("SCHOOL NOT FOUND");
                    System.out.println("THIS IS NOT CORRECT OPTION ");
                    System.out.println("APPLICATION EXIT ");
                    System.exit(0);

                }

                //TENDER FOUND STUDENT
                System.out.println("WRITE WHY YOU WANT TO REQUEST A TENDER");
                System.out.print(">");
                String tenderDescription = userInput.next();
                Tender tender = new Tender(tenderDescription);
                application1.setTender(tender);

                //Request tender
                System.out.println("REQUEST TENDER? (Y/N)");
                System.out.print(">");
                String requestTender = userInput.next();
                if (requestTender.equals("Y")) {

                    System.out.println("INVOICE OF APPLICATION");

                    Student student = studentDAO.selectStudentByFirstName(searchStudent);
                    System.out.println("STUDENT NAME : " + student.getFirstName() + " " + student.getLastName());
                    System.out.println("STUDENT DATE OF BIRTH: " + student.getDate_of_birth());
                    System.out.println("STUDENT TELEPHONE NUMBER: " + student.getTelephone_number());
                    System.out.println("STUDENT GENDER: " + student.getGender());
                    System.out.println("STUDENT ADDRESS:" + student.getAddress());
                    System.out.println("-----------------------------------------------------");
                    Education education = educationDAO.selectEducationByEducationName(application.getEducation().getEducation_name());
                    System.out.println("EDUCATION TITLE:" + education.getTitle());
                    System.out.println("EDUCATION NAME:" + education.getEducation_name());
                    System.out.println("EDUCATION AMOUNT:" + education.getAmount());
                    System.out.println("EDUCATION TYPE:" + education.getType().getType_education());
                    System.out.println("TENDER DESCRIPTION:" + tender.getTender_description());


                    //STATUS FOUND STUDENT
                    System.out.print("APPLICATION APPROVED? (Y/N) ");
                    System.out.print(">");
                    String approved = userInput.next();
                    if (approved.equals("Y")) {
                        Status status = statusDAO.selectStatusById(1); // approved
                        application1.setStatus(status);
                        application1.setNote("NO REASON");
                        applicationDAO.createApplication(application1);
                        System.out.println("STUDENT CAN MAKE TRANSACTION");
                    } else if (approved.equals("N")) {
                        Status status = statusDAO.selectStatusById(2); // declined
                        application1.setStatus(status);

                        System.out.println("CHOOSE BETWEEEN 1 AND 2 WHICH REASON THE APPLICATION IS BEING DECLINED FOR>");
                        System.out.println("1. INSUFFICIENT PAPEERS! ");
                        System.out.println("2. NO COLLATERAL! ");
                        System.out.println("3. BOTH REASONS");

                        System.out.println("WHICH REASON?");
                        String declinedReason = userInput.next();
                        //Decorator
                        Reason reason = new ReasonDecorator();
                        switch (declinedReason) {
                            case "1":
                                reason = new InsufficientPapersReason(reason);
                                System.out.println("APPLICATION DECLINED");
                                System.out.println(reason.getDesc());
                                break;
                            case "2":
                                reason = new NoCollateralReason(reason);
                                System.out.println("APPLICATION DECLINED");
                                System.out.println(reason.getDesc());
                                break;
                            case "3":
                                reason = new InsufficientPapersReason(reason);
                                reason = new NoCollateralReason(reason);
                                System.out.println("APPLICATION DECLINED");
                                System.out.println(reason.getDesc());
                                break;
                            default:
                                System.out.println("WRONG NUMBER! CHOOSE BETWEEN 1/ 2/ 3");
                        }
                        //SAVEN IN APPLICATION
                        application1.setNote(reason.getDesc());
                        applicationDAO.createApplication(application1);
                    } else {
                        System.out.println("WRONG INPUT ");
                        System.out.println("APPLICATION EXIT ");
                        System.exit(0);
                    }
                } else {
                    System.out.println("STUDENT DOES NOT WANT TO REQUEST A TENDER");
                    System.out.println("APPLICATION EXIT ");
                    System.exit(0);
                }
            } else if (studentRegistered.equals("N")) {

                //NEW STUDENT
                System.out.println("STUDENT REGISTRATION>");
                System.out.print("ENTER YOUR LASTNAMER ");
                System.out.print(">");
                String lastname = userInput.next();

                System.out.print("ENTER YOUR FIRSTNAME ");
                System.out.print(">");
                String firstname = userInput.next();

                System.out.print("ENTER YOUR DATE OF BIRTH: DD-MM- YYYY ");
                System.out.print(">");
                String date = userInput.next();
                SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");

                java.util.Date dateOfBirth = format.parse(date);

                System.out.print("ENTER YOUR PHONE NUMBER ");
                System.out.print(">");
                int telephone_number = userInput.nextInt();

                System.out.print("GENDER: MALE OR FEMALE ");
                System.out.print(">");
                String gender = userInput.next();

                Student student = new Student(lastname, firstname, dateOfBirth, telephone_number, gender);

                System.out.println("MORE THAN ONE ADDRESS? (Y/N)");
                System.out.print(">");
                String moreAddress = userInput.next();
                if (moreAddress.equals("Y")) {

                    //MORE ADDRESS NEW STUDENT
                    Set<Address> addresses = new HashSet<>();
                    System.out.print("ENTER YOUR DISTRICT ");
                    System.out.print(">");
                    String district1 = userInput.next();

                    System.out.print("ENTER YOUR STREETNAME ");
                    System.out.print(">");
                    String streetname1 = userInput.next();

                    Address address = new Address(district1, streetname1);
                    addresses.add(address);

                    System.out.print("ENTER YOUR DISTRICT ");
                    System.out.print(">");
                    String district2 = userInput.next();

                    System.out.print("ENTER YOUR STREETNAME ");
                    System.out.print(">");
                    String streetname2 = userInput.next();

                    Address address1 = new Address(district2, streetname2);
                    addresses.add(address1);

                    // set addres oproepen
                    student.setAddress(addresses);


                    StudentFactory studentFactory = new StudentFactory(entityManager);
                    Student studentFound = studentFactory.getStudent(firstname);
                    if (studentFound == null) studentFactory.createStudent(student);


                    Student student5 = studentDAO.selectStudentByFirstName(firstname);
                    application1.setStudent(student5);


                    Handler adekHandler = new AdekHandler();
                    Handler unasatHandler = new UnasatHandler();
                    Handler fhrHandler = new FhrHandler();

                    adekHandler.setSuccessor(unasatHandler);
                    unasatHandler.setSuccessor(fhrHandler);

                    System.out.println();
                    System.out.println("***SELECT OPTIONS***");
                    System.out.println("| 1. ADEK" + ""
                            + " | 2. UNASAT " + ""
                            + " | 3. FHR " + "");
                    System.out.println("ENTER OPTION NUMBER: ");
                    System.out.print(">");
                    Integer option = userInput.nextInt();

                    Application application = adekHandler.processRequest(new Request(option), application1);

                    if (application.getEducation() != null) {
                        System.out.println("School " + application.getEducation().getEducation_name() + " found");
                        application1 = application;
                    } else {
                        System.out.println("School not found");
                        System.out.println("THIS IS NOT CORRECT OPTION ");
                        System.out.println("APPLICATION EXIT ");
                        System.exit(0);
                    }

                    System.out.println("TENDER DESCRIPTION ");
                    System.out.print(">");
                    String tenderDescription1 = userInput.next();
                    Tender tender1 = new Tender(tenderDescription1);
                    application1.setTender(tender1);


                    System.out.println("INVOICE OF APPLICATION");

                    Student student1 = studentDAO.selectStudentByFirstName(firstname);
                    System.out.println("STUDENT NAME : " + student1.getFirstName() + " " + student1.getLastName());
                    System.out.println("STUDENT DATE OF BIRTH: " + student1.getDate_of_birth());
                    System.out.println("STUDENT TELEPHONE NUMBER: " + student1.getTelephone_number());
                    System.out.println("STUDENT GENDER: " + student1.getGender());
                    System.out.println("STUDENT ADDRESS:" + student1.getAddress());
                    System.out.println("-----------------------------------------------------");
                    Education education = educationDAO.selectEducationByEducationName(application.getEducation().getEducation_name());
                    System.out.println("EDUCATION TITLE:" + education.getTitle());
                    System.out.println("EDUCATION NAME:" + education.getEducation_name());
                    System.out.println("EDUCATION AMOUNT:" + education.getAmount());
                    System.out.println("EDUCATION TYPE:" + education.getType().getType_education());
                    System.out.println("TENDER DESCRIPTION:" + tender1.getTender_description());

                    //STATUS NEW STUDENT
                    System.out.print("APPLICATION APPROVED? (Y/N) ");
                    String approved = userInput.next();
                    System.out.print(">");
                    if (approved.equals("Y")) {
                        Status status = statusDAO.selectStatusById(1); // approved
                        application1.setStatus(status);
                        application1.setNote("NO REASON");
                        applicationDAO.createApplication(application1);
                        System.out.println("STUDENT CAN MAKE TRANSACTION");
                    } else if (approved.equals("N")) {

                        Status status = statusDAO.selectStatusById(2); // declined
                        application1.setStatus(status);

                        System.out.println("CHOOSE BETWEEEN 1 AND 2 WHICH REASON THE APPLICATION IS BEING DECLINED FOR>");
                        System.out.println("1. INSUFFICIENT PAPEERS! ");
                        System.out.println("2. NO COLLATERAL! ");
                        System.out.println("3. BOTH REASONS");

                        System.out.println("WHICH REASON?");
                        System.out.print(">");
                        String declinedReason = userInput.next();
                        //Decorator
                        Reason reason = new ReasonDecorator();
                        switch (declinedReason) {
                            case "1":
                                reason = new InsufficientPapersReason(reason);
                                System.out.println("APPLICATION DECLINED");
                                System.out.println(reason.getDesc());
                                break;
                            case "2":
                                reason = new NoCollateralReason(reason);
                                System.out.println("APPLICATION DECLINED");
                                System.out.println(reason.getDesc());
                                break;
                            case "3":
                                reason = new InsufficientPapersReason(reason);
                                reason = new NoCollateralReason(reason);
                                System.out.println("APPLICATION DECLINED");
                                System.out.println(reason.getDesc());
                                break;
                            default:
                                System.out.println("WRONG NUMBER! CHOOSE BETWEEN 1/ 2/ 3");
                        }
                        //SAVEN IN APPLICATION
                        application1.setNote(reason.getDesc());
                        applicationDAO.createApplication(application1);
                    } else {
                        System.out.println("THIS IS NOT CORRECT OPTION ");
                        System.out.println("APPLICATION EXIT ");
                        System.exit(0);
                    }
                } else if (moreAddress.equals("N")) {

                    //ONE ADDRESS NEW STUDENT
                    System.out.print("ENTER YOUR DISTRICT ");
                    System.out.print(">");
                    String district = userInput.next();

                    System.out.print("ENTER YOUR STREETNAME> ");
                    System.out.print(">");
                    String streetname = userInput.next();


                    Set<Address> addresses = new HashSet<>();
                    Address address = new Address(district, streetname);
                    addresses.add(address);
                    entityManager.persist(address);

                    //ikmoet set addres oproepen
                    student.setAddress(addresses);



                    StudentFactory studentFactory = new StudentFactory(entityManager);
                    Student studentFound = studentFactory.getStudent(firstname);
                    if (studentFound == null) studentFactory.createStudent(student);

                    Student student6 = studentDAO.selectStudentByFirstName(firstname);
                    application1.setStudent(student6);


                    Handler adekHandler = new AdekHandler();
                    Handler unasatHandler = new UnasatHandler();
                    Handler fhrHandler = new FhrHandler();

                    adekHandler.setSuccessor(unasatHandler);
                    unasatHandler.setSuccessor(fhrHandler);

                    System.out.println();
                    System.out.println("***SELECT OPTIONS***");
                    System.out.println("| 1. ADEK" + ""
                            + " | 2. UNASAT " + ""
                            + " | 3. FHR " + "");
                    System.out.println("ENTER OPTION NUMBER: ");
                    System.out.print(">");
                    Integer option = userInput.nextInt();

                    Application application = adekHandler.processRequest(new Request(option), application1);

                    if (application.getEducation() != null) {
                        System.out.println("School " + application.getEducation().getEducation_name() + " found");
                        application1 = application;
                    } else {
                        System.out.println("School not found");
                        System.out.println("THIS IS NOT CORRECT OPTION ");
                        System.out.println("APPLICATION EXIT ");
                        System.exit(0);

                    }
                    System.out.println("TENDER DESCRIPTION ");
                    System.out.print(">");
                    String tenderDescription1 = userInput.next();

                    Tender tender1 = new Tender(tenderDescription1);
                    application1.setTender(tender1);


                    System.out.println("INVOICE OF APPLICATION");

                    Student student1 = studentDAO.selectStudentByFirstName(firstname);
                    System.out.println("STUDENT NAME : " + student1.getFirstName() + " " + student1.getLastName());
                    System.out.println("STUDENT DATE OF BIRTH: " + student1.getDate_of_birth());
                    System.out.println("STUDENT TELEPHONE NUMBER: " + student1.getTelephone_number());
                    System.out.println("STUDENT GENDER: " + student1.getGender());
                    System.out.println("STUDENT ADDRESS:" + student1.getAddress());
                    System.out.println("-----------------------------------------------------");
                    Education education = educationDAO.selectEducationByEducationName(application.getEducation().getEducation_name());
                    System.out.println("EDUCATION TITLE:" + education.getTitle());
                    System.out.println("EDUCATION NAME:" + education.getEducation_name());
                    System.out.println("EDUCATION AMOUNT:" + education.getAmount());
                    System.out.println("EDUCATION TYPE:" + education.getType().getType_education());
                    System.out.println("TENDER DESCRIPTION:" + tender1.getTender_description());


                    //STATUS NEW STUDENT
                    System.out.print("APPLICATION APPROVED? (Y/N) ");
                    String approved = userInput.next();
                    System.out.print(">");
                    if (approved.equals("Y")) {
                        Status status = statusDAO.selectStatusById(1); // approved
                        application1.setStatus(status);
                        application1.setNote("NO REASON");
                        applicationDAO.createApplication(application1);
                        System.out.println("STUDENT CAN MAKE TRANSACTION");
                    } else if (approved.equals("N")) {
                        Status status = statusDAO.selectStatusById(2); // Declined
                        application1.setStatus(status);

                        System.out.println("CHOOSE BETWEEEN 1 AND 2 WHICH REASON THE APPLICATION IS BEING DECLINED FOR>");
                        System.out.println("1. INSUFFICIENT PAPEERS! ");
                        System.out.println("2. NO COLLATERAL! ");
                        System.out.println("3. BOTH REASONS");

                        System.out.println("WHICH REASON?");
                        System.out.print(">");
                        String declinedReason = userInput.next();
                        //Decorator
                        Reason reason = new ReasonDecorator();
                        switch (declinedReason) {
                            case "1":
                                reason = new InsufficientPapersReason(reason);
                                System.out.println("APPLICATION DECLINED");
                                System.out.println(reason.getDesc());
                                break;
                            case "2":
                                reason = new NoCollateralReason(reason);
                                System.out.println("APPLICATION DECLINED");
                                System.out.println(reason.getDesc());
                                break;
                            case "3":
                                reason = new InsufficientPapersReason(reason);
                                reason = new NoCollateralReason(reason);
                                System.out.println("APPLICATION DECLINED");
                                System.out.println(reason.getDesc());
                                break;
                            default:
                                System.out.println("WRONG NUMBER! CHOOSE BETWEEN 1/ 2/ 3");
                        }
                        //SAVEN IN APPLICATION
                        application1.setNote(reason.getDesc());
                        applicationDAO.createApplication(application1);
                    } else {
                        System.out.println("THIS IS NOT CORRECT OPTION ");
                        System.out.println("APPLICATION EXIT ");
                        System.exit(0);
                    }
                } else {
                    System.out.println("THIS IS NOT CORRECT OPTION ");
                    System.out.println("APPLICATION EXIT ");
                    System.exit(0);
                }
            } else {
                System.out.println("THIS IS NOT CORRECT OPTION ");
                System.out.println("APPLICATION EXIT ");
                System.exit(0);
            }

        } else if (newOrExistingApp.equals("2")) {
            //SEARCH APPLICATION
            System.out.println("SEARCH APPLICATION BY ID");
            System.out.println(">");
            int searchApp = userInput.nextInt();

            Application app = applicationDAO.selectApplicationById(searchApp);
            System.out.println("");
            System.out.println("-------------BEGIN-----------------");
            System.out.println("Date of application: " + app.getDate());
            System.out.println("Application ID: " + app.getApplication_id());
            System.out.println("------------------------------");
            System.out.println("Student naam: " + app.getStudent().getFirstName() + " " + app.getStudent().getLastName());
            System.out.println("Student date of birth: " + app.getStudent().getDate_of_birth());
            System.out.println("Student telephone number: " + app.getStudent().getTelephone_number());
            System.out.println("Student gender: " + app.getStudent().getGender());
            for (Address address : app.getStudent().getAddress()) {
                System.out.println("Student district:" + address.getDistrict());
                System.out.println("Student district:" + address.getStreetname());
            }
            System.out.println("------------------------------");
            System.out.println("Education title:" + app.getEducation().getTitle());
            System.out.println("Education name:" + app.getEducation().getEducation_name());
            System.out.println("Education amount:" + app.getEducation().getAmount());
            System.out.println("Education Type:" + app.getEducation().getType().getType_education());
            System.out.println("Tender description:" + app.getTender().getTender_description());
            System.out.println("Status:" + app.getStatus().getStatus());
            System.out.println("Note: " + app.getNote());
            System.out.println("------------END------------------");
            System.out.println("");

            System.out.println("APPLICATION EXIT ");
            System.exit(0);

        } else if (newOrExistingApp.equals("3")) {

            //DELETE WRONG REGISTRATION

            System.out.println("WRITE FIRSTNAME TO DELETE STUDENT RECORD");
            System.out.println(">");
            String deleteStudentFirstname = userInput.next();

            Student deleteStudent = studentDAO.selectStudentByFirstName(deleteStudentFirstname);
            studentDAO.deleteStudent(deleteStudent);
            System.out.println("Student " + deleteStudentFirstname + " has been deleted");

            System.out.println("APPLICATION EXIT ");
            System.exit(0);

        } else if (newOrExistingApp.equals("4")) {

            System.out.println("WHICH RAPPORT DO YOU WANT TO SEE?");
            System.out.println(""
                    + "| 1. OVERVIEW OF THE LOANS" + ""
                    + "| 2. ANNUAL STATEMENT WITH APPROVED AND REJECTED LOANS " + ""
                    + "| 3.DECLINED AND APPROVED LOANS PER SCHOOL" + "");

            RapportingDAO rapportingDAO = new RapportingDAO(entityManager);
            System.out.print(">");
            String whichRapport = userInput.next();
            switch (whichRapport) {
                case "1":
                    System.out.println("OVERVIEW OF THE LOANS");

                    Status status = statusDAO.selectAddressByStatus("APPROVED");
                    List<Application> applicationList = rapportingDAO.selectAllApplicationByApproved(status);
                    for (Application application : applicationList) {
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
                    System.out.println("APPLICATION EXIT ");
                    System.exit(0);
                    break;
                case "2":

                    System.out.println();
                    System.out.println("*ANNUAL REVIEW PER MONTH*");
                    System.out.println("*ENTER THE YEAR: ");
                    System.out.print(">");
                    int sYear = userInput.nextInt();

                    for (int i = 1; i <= 12; i++) {
                        List<Application> applicationList1 = rapportingDAO.selectJaaroverzicht(i, sYear);
                        System.out.println("MONTH: " + i);
                        if (applicationList1.isEmpty()) {
                            System.out.println("NO RECORDS FOUND.");
                        } else {
                            rapportingDAO.printMonthlyOverview(applicationList1);
                            System.out.println();
                        }
                    }

                    System.out.println("APPLICATION EXIT ");
                    System.exit(0);
                    break;
                case "3":

                    List<Application> applicationList4 = rapportingDAO.findAllSchoolApplications();
                    for (Application application : applicationList4) {
                        //ander education dan naam wijzen
                        // if () {
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
                    System.out.println("APPLICATION EXIT ");
                    System.exit(0);
                    break;
                default:
                    System.out.println("NO MATCH");
                    System.out.println("THIS IS NOT CORRECT OPTION ");
                    System.out.println("APPLICATION EXIT ");
                    System.exit(0);
            }
        } else System.exit(0);
    }
}