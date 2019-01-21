package sr.unasat.App;


import sr.unasat.DP.chainOfResponsibility.*;
import sr.unasat.DP.decorator.InsufficientPapersReason;
import sr.unasat.DP.decorator.NoCollateralReason;
import sr.unasat.DP.decorator.Reason;
import sr.unasat.DP.decorator.ReasonDecorator;
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

/*
        studentDAO.selectAllStudent();

        Student updateStudent = studentDAO.selectStudentByFirstName("Tesa");
        updateStudent.setFirstName("Tessa");
        studentDAO.updateStudent(updateStudent);

*/


        //Application  CRUD


        //education many to one naar type
        //BEGIN --------------- TYPE EN EDUCATION

        //Type stap1
        TypeDAO typeDAO = new TypeDAO(entityManager);
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
        EducationDAO educationDAO = new EducationDAO(entityManager);
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


        //connectie met type
        //  Education education_con =educationDAO.selectEducationByEducationName("Adek");

        //END ------------------- TYPE EN EDUCATION


        //BEGIN ------------- ADDRESS EN STUDENT

        //Student many to many naar address
        //student one to many naar appl

        //stap 1
        StudentDAO studentDAO = new StudentDAO(entityManager);
/*
        Student student1 = new Student();
        student1.setStudent_id(2);
        student1.setLastName("Atmo");
        student1.setFirstName("Brooke");
        student1.setDate_of_birth (Date.valueOf("1998-11-01"));
        //student1.setGender("v");
        student1.setTelephone_number(8444452);
        studentDAO.createStudent(student1);

     //   DELETE STUDENT
        Student deleteStudent = studentDAO.selectStudentByFirstName("asd");
       studentDAO.deleteStudent(deleteStudent);
*/


        //set zodat adres in ene lijst kan zijn

        AddressDAO addressDAO = new AddressDAO(entityManager);
       /*  Set<Address> addresses = new HashSet<>();
        Address address1 = new Address();
        address1.setAddress_id(2);
        address1.setDistrict("Paramaribo");
        address1.setStreetname("hello");
        addresses.add(address1);
         entityManager.persist(address1);

        //connect addres met student

        Address address1 = addressDAO.selectAddressByStreetname("hello");
        Student student1 = studentDAO.selectStudentByFirstName("Ruth");

        addresses.add(address1);

        //ikmoet set addres oproepen
        student1.setAddress(addresses);
        studentDAO.insertAddress(address1);
        entityManager.persist(student1);
*/
        // END STUDENT EN ADDRESS-----------------------------


        //Status one to many naar appl
/*
        StatusDAO statusDAO = new StatusDAO(entityManager);
        Status status1 = new Status();
       // status1.setStatus_id(2);
        status1.setStatus("DECLINED");
        statusDAO.createStatus(status1);

*/
        //Tender one to one naar appl
  /*      TenderDAO tenderDAO = new TenderDAO(entityManager);
        Tender tender4 = new Tender();
        //tender1.setTender_id(1);
        tender4.setTender_description("Niet goed ingevuld");
        tenderDAO.createTender(tender4);
*/
        //----------------------------------------------------------------

        //Connectie van alle tabellen met applicatie

        ApplicationDAO applicationDAO = new ApplicationDAO(entityManager);

        //    Application application1 = new Application();

//        //education
        //   Education education1 = educationDAO.selectEducationBy(2);
        //     application1.setEducation(education1);
//
////        //status
        StatusDAO statusDAO = new StatusDAO(entityManager);
   /*     Status status1 = statusDAO.selectStatusById(1);
        application1.setStatus(status1);
////*/
////        //tender
        TenderDAO tenderDAO = new TenderDAO(entityManager);
/*        Tender tender2 = tenderDAO.selectTenderBy(2);
        application1.setTender(tender2);
//
//        //student
        Student student = studentDAO.selectStudentBy(2);
        application1.setStudent(student);
//
////
        application1.setNote("Why");

        java.util.Date date = new java.util.Date();
        application1.setDate(date);
        applicationDAO.createApplication(application1);
////*/

        //SEARCH AN APPLICATION
        // System.out.println(applicationDAO.selectApplicationById(1));

/*
        // FDP Create GENDER
        GenderFactory genderFactory = new GenderFactory();
        Gender gender = new Gender();
        sr.unasat.DP.factory.Gender genderString = genderFactory.getGender("MALE");
        gender.setName(genderString.getName());
        GenderDAO genderDAO = new GenderDAO(entityManager);
        genderDAO.createGender(gender);


        GenderFactory genderFactory2 = new GenderFactory();
        Gender gender2 = new Gender();
        sr.unasat.DP.factory.Gender genderString2 = genderFactory2.getGender("FEMALE");
        gender2.setName(genderString2.getName());
        GenderDAO genderDAO1 = new GenderDAO(entityManager);
        genderDAO1.createGender(gender2);
*/


        //    SCANNER
        Scanner userInput = new Scanner(System.in);
        //System.in =the standard input stream

        System.out.println("Which opion do you want to choose. (1 / 2 )" +
                "Option 1. Send a new request " +
                "Option 2. Get a rapport   ");
        String newOrExistingApp = userInput.next();
        Application application1 = new Application();
        if (newOrExistingApp.equals("1")) {
            System.out.println("Creating new request");

            application1.setDate(new Date());
            System.out.println("Is student registered? (Y/N)");
            String studentRegistered = userInput.next();
            if (studentRegistered.equals("Y")) {
                System.out.println("Search registered student by name");
                String searchStudent = userInput.next();
                Student foundStudent = studentDAO.selectStudentByFirstName(searchStudent);
                if (foundStudent != null) {
                    System.out.println("Student " + searchStudent + "found");
                    application1.setStudent(foundStudent);
                } else {
                    System.out.println("Student not found");
                }

                //EDUCATION FOUND STUDENT


                System.out.println("Search registered education by Education name");
                System.out.println("ADEK / UNASAT / FHR");
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
                }


                //TENDER FOUNS STUDENT
                System.out.println("Write why you want to request a  tender");
                String tenderDescription = userInput.next();
                Tender tender = new Tender(tenderDescription);
                application1.setTender(tender);

                //Request tender
                System.out.println("Request tender? (Y/N)");
                String requestTender = userInput.next();
                if (requestTender.equals("Y")) {


                    System.out.println("Invoice of application");


                    Student student = studentDAO.selectStudentByFirstName(searchStudent);
                    System.out.println("Student naam: " + student.getFirstName() + " " + student.getLastName());
                    System.out.println("Student date of birth: " + student.getDate_of_birth());
                    System.out.println("Student telephone number: " + student.getTelephone_number());
                    System.out.println("Student gender: " + student.getGender());
                    System.out.println("Student address:" + student.getAddress());

                    Education education = educationDAO.selectEducationByEducationName(searchEducation);
                    System.out.println("Education title:" + education.getTitle());
                    System.out.println("Education name:" + education.getEducation_name());
                    System.out.println("Education amount:" + education.getAmount());
                    System.out.println("Education Type:" + education.getType());
                    System.out.println("Tender description:" + tender.getTender_description());


                    //STATUS FOUND STUDENT
                    System.out.print("Application approved ? (Y/N) ");
                    String approved = userInput.next();
                    if (approved.equals("Y")) {
                        Status status = statusDAO.selectStatusById(1); // approved
                        application1.setStatus(status);
                        application1.setNote("No Reason");
                        applicationDAO.createApplication(application1);
                        System.out.println("Student can make transaction");
                    } else {

                        Status status = statusDAO.selectStatusById(2); // declined
                        application1.setStatus(status);

                        System.out.println("Choose between 1 and 2 which reason the application is being declined for");
                        System.out.println("1. Insufficient Papers! ");
                        System.out.println("2. No Collateral! ");
                        System.out.println("3. Both reasons");

                        System.out.println("Which reason?");
                        String declinedReason = userInput.next();
                        //Decorator
                        Reason reason = new ReasonDecorator();
                        switch (declinedReason) {
                            case "1":
                                reason = new InsufficientPapersReason(reason);
                                System.out.println(reason.getDesc());
                                break;
                            case "2":
                                reason = new NoCollateralReason(reason);
                                System.out.println(reason.getDesc());
                                break;
                            case "3":
                                reason = new InsufficientPapersReason(reason);
                                reason = new NoCollateralReason(reason);
                                System.out.println(reason.getDesc());
                                break;
                            default:
                                System.out.println("Wrong number! Chose between 1/ 2/ 3");


                        }
                        //SAVEN IN APPLICATION
                        application1.setNote(reason.getDesc());
                        applicationDAO.createApplication(application1);

                    }
                } else {
                    System.out.println("Student does not want to request a tender");
                }

            } else {

                //NEW STUDENT
                System.out.println("Student registration:");


                System.out.print("Enter your lastname: ");
                String lastname = userInput.next();

                System.out.print("Enter your firstname: ");
                String firstname = userInput.next();

                System.out.print("Enter your date of birth: DD-MM- YYYY ");
                String date = userInput.next();
                SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");

                java.util.Date dateOfBirth = format.parse(date);

                System.out.print("Enter your phone number: ");
                int telephone_number = userInput.nextInt();

                System.out.println("Choose gender! " + "1. Male " + "OR " + "2. Female");

                Student student = new Student(lastname, firstname, dateOfBirth, telephone_number);


                GenderDAO genderDAO = new GenderDAO(entityManager);
                String gender = userInput.next();
                switch (gender) {
                    case "1":
                        Gender gender1 = genderDAO.selectGenderById(1);
                        student.setGender(gender1);
                        break;
                    case "2":
                        Gender gender2 = genderDAO.selectGenderById(2);
                        student.setGender(gender2);
                        break;
                    default:
                        System.out.println("no match");
                }


                studentDAO.createStudent(student);

                System.out.println("More then one address? (Y/N)");
                String moreAddress = userInput.next();
                if (moreAddress.equals("Y")) {

                    //MORE ADDRESS NEW STUDENT
                    Set<Address> addresses = new HashSet<>();
                    System.out.print("Enter your district: ");
                    String district1 = userInput.next();

                    System.out.print("Enter your Streetname: ");
                    String streetname1 = userInput.next();

                    Address address = new Address(district1, streetname1);
                    addresses.add(address);

                    System.out.print("Enter your district: ");
                    String district2 = userInput.next();

                    System.out.print("Enter your Streetname: ");
                    String streetname2 = userInput.next();

                    Address address1 = new Address(district2, streetname2);
                    addresses.add(address1);

                    // connect addres met student
                    Student student3 = studentDAO.selectStudentByFirstName(firstname);
                    //ikmoet set addres oproepen
                    student3.setAddress(addresses);
                    studentDAO.updateStudent(student3);


                    Student student5 = studentDAO.selectStudentByFirstName(firstname);
                    application1.setStudent(student5);


                    //EDUCATION NEW STUDENT

                    System.out.println("Search registered education by Education name");
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
                    }


                    System.out.println("Tender description: ");
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
                    Education education = educationDAO.selectEducationByEducationName(searchEducation);
                    System.out.println("Education title:" + education.getTitle());
                    System.out.println("Education name:" + education.getEducation_name());
                    System.out.println("Education amount:" + education.getAmount());
                    System.out.println("Education Type:" + education.getType());
                    System.out.println("Tender description:" + tender1.getTender_description());


                    //STATUS NEW STUDENT
                    System.out.print("Application approved ? (Y/N) ");
                    String approved = userInput.next();
                    if (approved.equals("Y")) {
                        Status status = statusDAO.selectStatusById(1); // approved
                        application1.setStatus(status);
                        application1.setNote("No Reason");
                        applicationDAO.createApplication(application1);
                        System.out.println("Student can make transaction");
                    } else {

                        Status status = statusDAO.selectStatusById(2); // declined
                        application1.setStatus(status);

                        System.out.println("Choose between 1 and 2 which reason the application is being declined for");
                        System.out.println("1. Insufficient Papers! ");
                        System.out.println("2. No Collateral! ");
                        System.out.println("3. Both reasons");

                        System.out.println("Which reason?");
                        String declinedReason = userInput.next();
                        //Decorator
                        Reason reason = new ReasonDecorator();
                        switch (declinedReason) {
                            case "1":
                                reason = new InsufficientPapersReason(reason);
                                System.out.println(reason.getDesc());
                                break;
                            case "2":
                                reason = new NoCollateralReason(reason);
                                System.out.println(reason.getDesc());
                                break;
                            case "3":
                                reason = new InsufficientPapersReason(reason);
                                reason = new NoCollateralReason(reason);
                                System.out.println(reason.getDesc());
                                break;
                            default:
                                System.out.println("Wrong number! Chose between 1/ 2/ 3");


                        }
                        //SAVEN IN APPLICATION
                        application1.setNote(reason.getDesc());
                        applicationDAO.createApplication(application1);

                    }


                } else {

                    //ONE ADDRESS NEW STUDENT
                    System.out.print("Enter your district: ");
                    String district = userInput.next();

                    System.out.print("Enter your Streetname: ");
                    String streetname = userInput.next();


                    Set<Address> addresses = new HashSet<>();

                    Address address = new Address(district, streetname);

                    addresses.add(address);
                    entityManager.persist(address);

                    // connect addres met student
                    Student student4 = studentDAO.selectStudentByFirstName(firstname);


                    //ikmoet set addres oproepen
                    student4.setAddress(addresses);
                    studentDAO.updateStudent(student4);


                    Student student6 = studentDAO.selectStudentByFirstName(firstname);
                    application1.setStudent(student6);


                    //EDUCATION NEW STUDENT


                    System.out.println("Search registered education by Education name");
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
                    }


                    System.out.println("Tender description: ");
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
                    Education education = educationDAO.selectEducationByEducationName(searchEducation);
                    System.out.println("Education title:" + education.getTitle());
                    System.out.println("Education name:" + education.getEducation_name());
                    System.out.println("Education amount:" + education.getAmount());
                    System.out.println("Education Type:" + education.getType());
                    System.out.println("Tender description:" + tender1.getTender_description());


                    //STATUS NEW STUDENT
                    System.out.print("Application approved ? (Y/N) ");
                    String approved = userInput.next();
                    if (approved.equals("Y")) {
                        Status status = statusDAO.selectStatusById(1); // approved
                        application1.setStatus(status);
                        application1.setNote("No Reason");
                        applicationDAO.createApplication(application1);
                        System.out.println("Student can make transaction");
                    } else {
                        Status status = statusDAO.selectStatusById(2); // Declined
                        application1.setStatus(status);

                        System.out.println("Choose between 1 and 2 which reason the application is being declined for");
                        System.out.println("1. Insufficient Papers! ");
                        System.out.println("2. No Collateral! ");
                        System.out.println("3. Both reasons");

                        System.out.println("Which reason?");
                        String declinedReason = userInput.next();
                        //Decorator
                        Reason reason = new ReasonDecorator();
                        switch (declinedReason) {
                            case "1":
                                reason = new InsufficientPapersReason(reason);
                                System.out.println("Application declined");
                                System.out.println(reason.getDesc());
                                break;
                            case "2":
                                reason = new NoCollateralReason(reason);
                                System.out.println("Application declined");
                                System.out.println(reason.getDesc());
                                break;
                            case "3":
                                reason = new InsufficientPapersReason(reason);
                                reason = new NoCollateralReason(reason);
                                System.out.println("Application declined");
                                System.out.println(reason.getDesc());
                                break;
                            default:
                                System.out.println("Wrong number! Chose between 1/ 2/ 3");


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
                    "2. \n" +
                    "annual statement with approved and rejected loans and most and least loans " +
                    "that have been rejected and approved." +
                    "3. afgekeurde en goedgekeurde leningen per school");

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
                        System.out.println("Student address:" + application.getStudent().getAddress());
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

                    System.out.println("Overview of the year annual");
                    List<Application> applicationList1 = rapportingDAO.selectJaaroverzicht();
                    for (Application application : applicationList1) {
                        System.out.println("Application ID: " + application.getApplication_id());
                        System.out.println("Student naam: " + application.getStudent().getFirstName() + " " + application.getStudent().getLastName());
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


                    }


                    break;
                case "3":

/////
                    System.out.println(rapportingDAO.selectDeclinedBySchool());

                    break;
                default:
                    System.out.println("no match");
            }

        }

    }


}


