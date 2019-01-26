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

    public Education foundEducation;

    public static void main(String[] args) throws ParseException {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCE");
        EntityManager entityManager = emf.createEntityManager();

        EducationDAO educationDAO = new EducationDAO(entityManager);
        StudentDAO studentDAO = new StudentDAO(entityManager);
        AddressDAO addressDAO = new AddressDAO(entityManager);
        TypeDAO typeDAO = new TypeDAO(entityManager);
        StatusDAO statusDAO = new StatusDAO(entityManager);
        TenderDAO tenderDAO = new TenderDAO(entityManager);

        ApplicationDAO applicationDAO = new ApplicationDAO(entityManager);
        Application application1 = new Application();


        //    SCANNER
        Scanner userInput = new Scanner(System.in);
        //System.in =the standard input stream

        System.out.println("WHICH OPTION DO YOU WANT TO CHOOSE (1 / 2 )|" +
                "OPTION 1. SEND NEW REQUEST| " +
                "OPTION 2. GET RAPPORT| ");

        //search
        //delete wrong registration of student
        String newOrExistingApp = userInput.next();
        if (newOrExistingApp.equals("1")) {
            System.out.println("CREATING NEW REQUEST");

            application1.setDate(new Date());
            System.out.println("IS STUDENT REGISTERED? (Y/N)");
            String studentRegistered = userInput.next();
            if (studentRegistered.equals("Y")) {
                System.out.println("SEARCH REGISTERED STUDENT BY FIRSTNAME");
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
//                fhrHandler.setSuccessor(adekHandler);

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


                //TENDER FOUNS STUDENT
                System.out.println("WRITE WHY YOU WANT TO REQUEST A TENDER");
                String tenderDescription = userInput.next();
                Tender tender = new Tender(tenderDescription);
                application1.setTender(tender);

                //Request tender
                System.out.println("REQUEST TENDER? (Y/N)");
                String requestTender = userInput.next();
                if (requestTender.equals("Y")) {


                    System.out.println("INVOICE OF APPLICATION");


                    Student student = studentDAO.selectStudentByFirstName(searchStudent);
                    System.out.println("STUDENT NAME : " + student.getFirstName() + " " + student.getLastName());
                    System.out.println("STUDENT DATE OF BIRTH: " + student.getDate_of_birth());
                    System.out.println("STUDENT TELEPHONE NUMBER: " + student.getTelephone_number());
                    System.out.println("STUDENT GENDER: " + student.getGender());
                    System.out.println("STUDENT ADDRESS:" + student.getAddress());

                    Education education = educationDAO.selectEducationByEducationName("ADEK");
                    System.out.println("EDUCATION TITLE:" + education.getTitle());
                    System.out.println("EDUCATION NAME:" + education.getEducation_name());
                    System.out.println("EDUCATION AMOUNT:" + education.getAmount());
                    System.out.println("EDUCATION TYPE:" + education.getType());
                    System.out.println("TENDER DESCRIPTION:" + tender.getTender_description());


                    //STATUS FOUND STUDENT
                    System.out.print("Application approved ? (Y/N) ");
                    String approved = userInput.next();
                    if (approved.equals("Y")) {
                        Status status = statusDAO.selectStatusById(1); // approved
                        application1.setStatus(status);
                        application1.setNote("No Reason");
                        applicationDAO.createApplication(application1);
                        System.out.println("StudentInterface can make transaction");
                    } else {

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
                    }
                } else {
                    System.out.println("STUDENT DOES NOT WANT TO REQUEST A TENDER");
                    System.exit(0);
                }

            } else {

                //NEW STUDENT
                System.out.println("STUDENT REGISTRATION>");


                System.out.print("ENTER YOUR LASTNAMER> ");
                String lastname = userInput.next();

                System.out.print("ENTER YOUR FIRSTNAME> ");
                String firstname = userInput.next();

                System.out.print("ENTER YOUR DATE OF BIRTH: DD-MM- YYYY >");
                String date = userInput.next();
                SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");

                java.util.Date dateOfBirth = format.parse(date);

                System.out.print("ENTER YOUR PHONE NUMBER> ");
                int telephone_number = userInput.nextInt();


                System.out.print("GENDER: MALE OR FEMALE ");
                String gender = userInput.next();

                Student student = new Student(lastname, firstname, dateOfBirth, telephone_number, gender);


                //studentDAO.createStudent(student);

                System.out.println("MORE THAN ONE ADDRESS? (Y/N)");
                String moreAddress = userInput.next();
                if (moreAddress.equals("Y")) {

                    //MORE ADDRESS NEW STUDENT
                    Set<Address> addresses = new HashSet<>();
                    System.out.print("ENTER YOUR DISTRICT> ");
                    String district1 = userInput.next();

                    System.out.print("ENTER YOUR STREETNAME> ");
                    String streetname1 = userInput.next();

                    Address address = new Address(district1, streetname1);
                    addresses.add(address);

                    System.out.print("ENTER YOUR DISTRICT> ");
                    String district2 = userInput.next();

                    System.out.print("ENTER YOUR STREETNAME> ");
                    String streetname2 = userInput.next();

                    Address address1 = new Address(district2, streetname2);
                    addresses.add(address1);

                    // connect addres met student
                    //Student student3 = studentDAO.selectStudentByFirstName(firstname);
                    //ikmoet set addres oproepen
                    student.setAddress(addresses);


                    StudentFactory studentFactory = new StudentFactory(entityManager);
                    Student studentFound = studentFactory.getStudent(firstname);
                    if (studentFound == null) studentFactory.createStudent(student);


                    Student student5 = studentDAO.selectStudentByFirstName(firstname);
                    application1.setStudent(student5);


                    //EDUCATION NEW STUDENT
/*
                    System.out.println("Search registered education by Education name");
                    System.out.println("Select number 1 -> ADEK  / 2. -> UNASAT / 3. -> FHR");
                    String searchEducation = userInput.next();
                    Handler h1 = new AdekHandler();
                    Handler h2 = new UnasatHandler();
                    Handler h3 = new FhrHandler();
                    h1.setSuccessor(h2);
                    h2.setSuccessor(h3);

                    Education foundEducation = h1.handleRequest(new Request(searchEducation));

                    if (foundEducation != null) {
                        System.out.println("School " + foundEducation + "found");
                        application1.setEducation(foundEducation);
                    } else {
                        System.out.println("School not found");
                    }*/

                    Handler adekHandler = new AdekHandler();
                    Handler unasatHandler = new UnasatHandler();
                    Handler fhrHandler = new FhrHandler();

                    adekHandler.setSuccessor(adekHandler);
                    unasatHandler.setSuccessor(unasatHandler);
                    fhrHandler.setSuccessor(fhrHandler);


                    System.out.println();
                    System.out.println("***SELECT OPTIONS***");
                    System.out.println("| 1. ADEK" + ""
                            + " | 2. UNASAT " + ""
                            + " | 3. FHR " + "");
                    System.out.println("ENTER OPTION NUMBER: ");
                    System.out.print(">");

//                            Integer option = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
//                            adekHandler.processRequest(new Request(option));
//                        }


                    System.out.println("TENDER DESCRIPTION> ");
                    String tenderDescription1 = userInput.next();
                    Tender tender1 = new Tender(tenderDescription1);
                    application1.setTender(tender1);


                    System.out.println("Invoice of application");


                    Student student1 = studentDAO.selectStudentByFirstName(firstname);
                    System.out.println("Student naam: " + student1.getFirstName() + " " + student.getLastName());
                    System.out.println("Student date of birth: " + student1.getDate_of_birth());
                    System.out.println("Student telephone number: " + student1.getTelephone_number());
                    System.out.println("Student gender: " + student1.getGender());
                    System.out.println("Student address:" + student1.getAddress());
                    Education education = educationDAO.selectEducationByEducationName("UNASAT");
                    System.out.println("Education title:" + education.getTitle());
                    System.out.println("Education name:" + education.getEducation_name());
                    System.out.println("Education amount:" + education.getAmount());
                    System.out.println("Education Type:" + education.getType());
                    System.out.println("Tender description:" + tender1.getTender_description());


                    //STATUS NEW STUDENT
                    System.out.print("APPLICATION APPROVED? (Y/N) ");
                    String approved = userInput.next();
                    if (approved.equals("Y")) {

                        Status status = statusDAO.selectStatusById(1); // approved
                        application1.setStatus(status);
                        application1.setNote("NO REASON");
                        applicationDAO.createApplication(application1);
                        System.out.println("STUDENT CAN MAKE TRANSACTION");
                    } else {

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
                    }
                } else {

                    //ONE ADDRESS NEW STUDENT
                    System.out.print("ENTER YOUR DISTRICT> ");
                    String district = userInput.next();

                    System.out.print("ENTER YOUR STREETNAME> ");
                    String streetname = userInput.next();


                    Set<Address> addresses = new HashSet<>();
                    Address address = new Address(district, streetname);
                    addresses.add(address);
                    entityManager.persist(address);

                    // connect addres met student
                    // Student student4 = studentDAO.selectStudentByFirstName(firstname);
                    //ikmoet set addres oproepen
                    student.setAddress(addresses);
                    //  studentDAO.updateStudent(student4);


                    StudentFactory studentFactory = new StudentFactory(entityManager);
                    Student studentFound = studentFactory.getStudent(firstname);
                    if (studentFound == null) studentFactory.createStudent(student);

                    Student student6 = studentDAO.selectStudentByFirstName(firstname);
                    application1.setStudent(student6);

                    //EDUCATION NEW STUDENT
                  /*  System.out.println("Search registered education by Education name");
                    System.out.println("Select number 1 -> ADEK  / 2. -> UNASAT / 3. -> FHR");
                    String searchEducation = userInput.next();
                    //Chain of responsibility
                    Handler h1 = new AdekHandler();
                    Handler h2 = new UnasatHandler();
                    Handler h3 = new FhrHandler();
                    h1.setSuccessor(h2);
                    h2.setSuccessor(h3);

                    Education foundEducation = h1.handleRequest(new Request(searchEducation));
                    if (foundEducation != null) {
                        System.out.println("School " + foundEducation + "found");
                        application1.setEducation(foundEducation);
                    } else {

                        System.out.println("School not found");
                    }*/


                    Handler adekHandler = new AdekHandler();
                    Handler unasatHandler = new UnasatHandler();
                    Handler fhrHandler = new FhrHandler();

                    adekHandler.setSuccessor(adekHandler);
                    unasatHandler.setSuccessor(unasatHandler);
                    fhrHandler.setSuccessor(fhrHandler);


                    System.out.println();
                    System.out.println("***SELECT OPTIONS***");
                    System.out.println("| 1. ADEK" + ""
                            + " | 2. UNASAT " + ""
                            + " | 3. FHR " + "");
                    System.out.println("ENTER OPTION NUMBER: ");
                    System.out.print(">");

//                            Integer option = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
//                            adekHandler.processRequest(new Request(option));
//

                    System.out.println("TENDER DESCRIPTION> ");
                    String tenderDescription1 = userInput.next();

                    Tender tender1 = new Tender(tenderDescription1);
                    application1.setTender(tender1);


                    System.out.println("Invoice of application");



                    Student student1 = studentDAO.selectStudentByFirstName(firstname);
                    System.out.println("Student naam: " + student1.getFirstName() + " " + student.getLastName());
                    System.out.println("Student date of birth: " + student1.getDate_of_birth());
                    System.out.println("Student telephone number: " + student1.getTelephone_number());
                    System.out.println("Student gender: " + student1.getGender());
                    System.out.println("Student address:" + student1.getAddress());
                    Education education = educationDAO.selectEducationByEducationName("FHR");
                    System.out.println("Education title:" + education.getTitle());
                    System.out.println("Education name:" + education.getEducation_name());
                    System.out.println("Education amount:" + education.getAmount());
                    System.out.println("Education Type:" + education.getType());
                    System.out.println("Tender description:" + tender1.getTender_description());


                    //STATUS NEW STUDENT
                    System.out.print("APPLICATION APPROVED? (Y/N) ");
                    String approved = userInput.next();
                    if (approved.equals("Y")) {
                        Status status = statusDAO.selectStatusById(1); // approved
                        application1.setStatus(status);
                        application1.setNote("NO REASON");
                        applicationDAO.createApplication(application1);
                        System.out.println("STUDENT CAN MAKE TRANSACTION");
                    } else {
                        Status status = statusDAO.selectStatusById(2); // Declined
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
                    }
                }
            }
        } else {

            System.out.println("Which rapport do you want to see?" +
                    "1. Overview of the loans" +
                    "2. annual statement with approved and rejected loans  " +
                    "3.afgekeurde en goedgekeurde leningen per school" +
                    "4. Most approved loans" +
                    "5. Most declined loans");

            RapportingDAO rapportingDAO = new RapportingDAO(entityManager);
            String whichRapport = userInput.next();
            switch (whichRapport) {
                case "1":
                    System.out.println("Overview of the loans");

                    Status status = statusDAO.selectAddressByStatus("APPROVED");
                    List<Application> applicationList = rapportingDAO.selectAllApplicationByApproved(status);
                    for (Application application : applicationList) {
                        System.out.println("Application ID: " + application.getApplication_id());
                        System.out.println("Student naam: " + application.getStudent().getFirstName() + " " + application.getStudent().getLastName());
                        System.out.println("Student date of birth: " + application.getStudent().getDate_of_birth());
                        System.out.println("Student telephone number: " + application.getStudent().getTelephone_number());
                        System.out.println("Student gender: " + application.getStudent().getGender());
                        for (Address address : application.getStudent().getAddress()) {
                            System.out.println("Student district:" + address.getDistrict());
                            System.out.println("Student district:" + address.getStreetname());
                        }
                        System.out.println("Education title:" + application.getEducation().getTitle());
                        System.out.println("Education name:" + application.getEducation().getEducation_name());
                        System.out.println("Education amount:" + application.getEducation().getAmount());
                        System.out.println("Education Type:" + application.getEducation().getType());
                        System.out.println("Tender description:" + application.getTender().getTender_description());
                        System.out.println("Status:" + application.getStatus().getStatus());
                        System.out.println("Note: " + application.getNote());
                        System.out.println("Date of application: " + application.getDate());
                    }
                    break;
                case "2":

                    System.out.println();
                    System.out.println("*ANNUAL REVIEW PER MONTH*");
                    System.out.println("*ENTER THE YEAR: ");
                    int sYear = userInput.nextInt();
                    // Integer year = Integer.parseInt(sYear);

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

                    break;
                case "3":

                    List<Application> applicationList4 = rapportingDAO.findAllSchoolApplications();
                    for (Application application : applicationList4) {
                        //ander education dan naam wijzen
                        // if () {
                        System.out.println(" Education name: " + application.getEducation().getEducation_name());
                        System.out.println("Application ID: " + application.getApplication_id());
                        System.out.println("Student name: " + application.getStudent().getFirstName() + " " + application.getStudent().getLastName());
                        System.out.println("Student date of birth: " + application.getStudent().getDate_of_birth());
                        System.out.println("Student telephone number: " + application.getStudent().getTelephone_number());
                        System.out.println("Student gender: " + application.getStudent().getGender());
                        System.out.println("Student address:" + application.getStudent().getAddress());
                        System.out.println("Education title:" + application.getEducation().getTitle());
                        System.out.println("Education name:" + application.getEducation().getEducation_name());
                        System.out.println("Education amount:" + application.getEducation().getAmount());
                        System.out.println("Education Type:" + application.getEducation().getType());
                        System.out.println("Tender description:" + application.getTender().getTender_description());
                        System.out.println("Status:" + application.getStatus().getStatus());
                        System.out.println("Note: " + application.getNote());
                        System.out.println("Date of application: " + application.getDate());
                        // }
                    }
                    break;
                case "4":
                    System.out.println("Most Approved application");

                    Status statusApproved1 = statusDAO.selectAddressByStatus("APPROVED");


                    List<Object[]> application4 = rapportingDAO.findCountApproved(statusApproved1);
                    System.out.println("Approved application" + application4);

                    break;
                case "5":
                    System.out.println("Most Declined application");

                    Status statusDeclined1 = statusDAO.selectAddressByStatus("DECLINED");
                    List<Object[]> application5 = rapportingDAO.findCountDeclined(statusDeclined1);
                    System.out.println("Approved declined" + application5);
                    break;
                default:
                    System.out.println("no match");
            }
        }

/*
        studentDAO.selectAllStudent();

        StudentInterface updateStudent = studentDAO.selectStudentByFirstName("et");
        updateStudent.setFirstName("Tessa");
        studentDAO.updateStudent(updateStudent);

*/
        //Application  CRUD
        //education many to one naar type
        //BEGIN --------------- TYPE EN EDUCATION

        //Type stap1
/*
        Type type1 = new Type();
        //type1.setType_id(1);
        type1.setType_education("HBO");
        typeDAO.createType(type1);

        Type type2 = new Type();
       // type1.setType_id(2);
        type2.setType_education("MBO");
        typeDAO.createType(type2);

        Type type3 = new Type();
        //type1.setType_id(3);
        type3.setType_education("PHD");
        typeDAO.createType(type3);
*/
        // education one to many naar appl

        // stap2
/*
        Education education1 = new Education();
        education1.setTitle("Software");
        education1.setEducation_name("ADEK");
        education1.setAmount(6500);

//        //find gebruiken
        Type type2 = typeDAO.selectTypeByTypeEducation("PHD");
        education1.setType(type2);
        educationDAO.createEducation(education1);
*/
        //END ------------------- TYPE EN EDUCATION


        //BEGIN ------------- ADDRESS EN STUDENT

        //StudentInterface many to many naar address
        //student one to many naar appl

        //stap 1
/*
        StudentInterface student1 = new StudentInterface();
        student1.setStudent_id(2);
        student1.setLastName("Atmo");
        student1.setFirstName("Brooke");
        student1.setDate_of_birth (Date.valueOf("1998-11-01"));
        StudentInterface gender1 = genderDAO.selectGenderById(2);
        student.setGender(gender1);
        student1.setTelephone_number(8444452);
        studentDAO.createStudent(student1);

 */
        //set zodat adres in ene lijst kan zijn
       /*  Set<Address> addresses = new HashSet<>();
        Address address1 = new Address();
        address1.setAddress_id(2);
        address1.setDistrict("Paramaribo");
        address1.setStreetname("hello");
        addresses.add(address1);
         entityManager.persist(address1);

        //connect addres met student

        Address address1 = addressDAO.selectAddressByStreetname("hello");
        StudentInterface student1 = studentDAO.selectStudentByFirstName("Ruth");

        addresses.add(address1);

        //ikmoet set addres oproepen
        student1.setAddress(addresses);
        studentDAO.insertAddress(address1);
        entityManager.persist(student1);
*/
        // END STUDENT EN ADDRESS-----------------------------


        //Status one to many naar appl
/*

        Status status1 = new Status();
       // status1.setStatus_id(2);
        status1.setStatus("DECLINED");
        statusDAO.createStatus(status1);

*/
        //Tender one to one naar app

        /*
        Tender tender4 = new Tender();
        //tender1.setTender_id(1);
        tender4.setTender_description("Niet goed ingevuld");
        tenderDAO.createTender(tender4);
*/
        //----------------------------------------------------------------

        //Connectie van alle tabellen met applicatie

      /*  //education
           Education education1 = educationDAO.selectEducationBy(2);
            application1.setEducation(education1);

        //status
        Status status1 = statusDAO.selectStatusById(1);
        application1.setStatus(status1);

       //tender
        Tender tender2 = tenderDAO.selectTenderBy(2);
        application1.setTender(tender2);

        //student
        StudentInterface student = studentDAO.selectStudentBy(2);
        application1.setStudent(student);

        application1.setNote("Why");

        java.util.Date date = new java.util.Date();
        application1.setDate(date);
        applicationDAO.createApplication(application1);
*/

        //SEARCH AN APPLICATION
        // System.out.println(applicationDAO.selectApplicationById(1));


        //   DELETE STUDENT
/*
        StudentInterface deleteStudent = studentDAO.selectStudentByFirstName("asd");
        studentDAO.deleteStudent(deleteStudent);
*/

/*
        // FDP Create GENDER
        StudentFactory genderFactory = new StudentFactory();
        StudentInterface gender = new StudentInterface();
        sr.unasat.DP.factory.StudentInterface genderString = genderFactory.getGender("MALE");
        gender.setName(genderString.StudentInterface());
        GenderDAO genderDAO = new GenderDAO(entityManager);
        genderDAO.createGender(gender);


        StudentFactory genderFactory2 = new StudentFactory();
        StudentInterface gender2 = new StudentInterface();
        sr.unasat.DP.factory.StudentInterface genderString2 = genderFactory2.getGender("FEMALE");
        gender2.setName(genderString2.StudentInterface());
        GenderDAO genderDAO1 = new GenderDAO(entityManager);
        genderDAO1.createGender(gender2);
*/








    }
}