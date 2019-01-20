package sr.unasat.App;


import sr.unasat.dao.*;
import sr.unasat.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;

public class App {

    public static void main(String[] args) throws ParseException {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCE");
        EntityManager entityManager = emf.createEntityManager();
//
////STATUS CRUD
        StatusDAO statusDAO = new StatusDAO(entityManager);
//        entityManager.getTransaction().begin();
//        statusDAO.createStatus(2,"TEST");
//        entityManager.getTransaction().commit();
//        System.out.println("Persisted " + statusDAO);
//
//        statusDAO.selectAllStatus();
//       // statusDAO.selectAddressById(1);
//        //statusDAO.selectAddressByStatus("Goed1")
//
//        Status updateStatus = statusDAO.selectAddressByStatus("GOED");
//        updateStatus.setStatus("Goed1");
//        statusDAO.updateStatus(updateStatus);
//
//        Status deleteStatus = statusDAO.selectAddressByStatus("test");
//        statusDAO.deleteAddressByStatus(deleteStatus);
////
//
//        //Type CRUD
//
        TypeDAO typeDAO = new TypeDAO(entityManager);
//        entityManager.getTransaction().begin();
//        typeDAO.createType(2,"MBO");
//        entityManager.getTransaction().commit();
//        System.out.println("Persisted" + typeDAO);
//
//        typeDAO.selectAllType();
//
//        Type updateType = typeDAO.selectTypeByTypeEducation("HBO");
//        updateType.setType_education("MBO");
//        typeDAO.updateType(updateType);
//
//        Type deleteType = typeDAO.selectTypeByTypeEducation("MBO");
//        typeDAO.deleteTypeByTypeEducation(deleteType);
//
//
//        //Tender CRUD
//
        TenderDAO tenderDAO = new TenderDAO(entityManager);
//        entityManager.getTransaction().begin();
//        tenderDAO.createTender(1,"");
//        entityManager.getTransaction().commit();
//        System.out.println("Persisted" + tenderDAO);
//
//        tenderDAO.selectAllTender();
//
//        Tender updateTender = tenderDAO.selectTenderById("HBO");
//        updateTender.setTender_id("MBO");
//        tenderDAO.updateTender(updateTender);
//
//        Tender deleteTender = tenderDAO.selectTenderBy("MBO");
//        tenderDAO.deleteTender(deleteTender);
//
//
//        //Address CRUD
        AddressDAO addressDAO = new AddressDAO(entityManager);
//        entityManager.getTransaction().begin();
//        addressDAO.createAddress(1, "Commewijne", "Tamanredjo 1");
//        entityManager.getTransaction().commit();
//
//        addressDAO.selectAllAddress();
//
//        Address updateAddress = addressDAO.selectAddressByStreetname("Tamanredjo 1");
//        updateAddress.setStreetname("Tamanredjo 2");
//        addressDAO.updateAddress(updateAddress);
//
//        Address deleteAddress = addressDAO.selectAddressByStreetname("Tamanredjo 2");
//        addressDAO.deleteAddressByStreetname(deleteAddress);
//
//
//        //STUDENT CRUD
        StudentDAO studentDAO = new StudentDAO(entityManager);
//        entityManager.getTransaction().begin();
//        studentDAO.createStudent(1,"Atmo", "Tessa", , "V", 7229239 );
//        entityManager.getTransaction().commit();
//

//        Student student = new Student();
//        student.setStudent_id(1);
//        student.setLastName("Atmo");
//        student.setFirstName("Tessa");
//        student.setDate_of_birth (Date.valueOf("01/11/1998"));
//        student.setGender("v");
//        student.setTelephone_number(7229239);
//        studentDAO.createStudent(student);

//        List<Student> students = studentDAO.selectAllStudent();
//        for (Student student : students) {
//            System.out.println("ID: " + student.getStudent_id()
//                    + " | Naam: " + student.getLastName() + " " + student.getLastName()
//                    + " | Geboortedatum: " + student.getDate_of_birth()
//                    + " | Gender: " + student.getGender()
//                    + " | Telefoon: " + student.getTelephone_number());


//        studentDAO.selectAllStudent();
//
//        Student updateStudent = studentDAO.selectStudentByFirstName("Tesa");
//        updateStudent.setFirstName("Tessa");
//        studentDAO.updateStudent(updateStudent);
//
//        Student deleteStudent = studentDAO.selectStudentByFirstName("Tessa");
//        studentDAO.deleteStudent(deleteStudent);
//
//
//        //Education CRUD
        EducationDAO educationDAO = new EducationDAO(entityManager);
//        entityManager.getTransaction().begin();
//        educationDAO.createEducation(1, "Software  engineer" , "UNASAT", 4000 );
//        entityManager.getTransaction().commit();
//
//        educationDAO.selectAllEducation();
//
//        Education updateEducation = educationDAO.selectEducationByEducationName("UNASATs");
//        updateEducation.setEducation_name("UNASAT");
//        educationDAO.updateEducation(updateEducation);
//
//        Education deleteEducation = educationDAO.selectEducationByEducationName("UNASAT");
//        educationDAO.deleteEducation(deleteEducation);


        //ApprovedApplication  CRUD


        //education many to one naar type
        //BEGIN --------------- TYPE EN EDUCATION

        //Type stap1
//        Type type1 = new Type();
//        type1.setType_id(2);
//        type1.setType_education("MBO");
//        typeDAO.createType(type1);

//
//        // education one to many naar appl
//
        // stap2
//        Education education1 = new Education();
//        education1.setEducation_id(2);
//        education1.setTitle("software");
//        education1.setEducation_name("Unasat");
//        education1.setAmount(1000);
//
//        //find gebruiken
//        Type type2 = typeDAO.selectTypeByTypeEducation("HBO");
////        System.out.println(type1.getEducation());
//        education1.setType(type2);
//        educationDAO.createEducation(education1);
//
//
//        //connectie met type
//        Education education_con =educationDAO.selectEducationByEducationName("Adek");

        //END ------------------- TYPE EN EDUCATION


        //BEGIN ------------- ADDRESS EN STUDENT
//
//        //Student many to many naar address
//        //student one to many naar appl
//
        //stap 1
//        Student student1 = new Student();
//        student1.setStudent_id(2);
//        student1.setLastName("Atmo");
//        student1.setFirstName("Brooke");
//        student1.setDate_of_birth (Date.valueOf("1998-11-01"));
//        student1.setGender("v");
//        student1.setTelephone_number(8444452);
//        studentDAO.createStudent(student1);

        //set zodat adres in ene lijst kan zijn
        // Set<Address> addresses = new HashSet<>();

//        Address address1 = new Address();
//        address1.setAddress_id(2);
//        address1.setDistrict("Paramaribo");
//        address1.setStreetname("hello");
//        addresses.add(address1);
        // entityManager.persist(address1);

        //connect addres met student

//        Address address1 = addressDAO.selectAddressByStreetname("hello");
//        Student student1 = studentDAO.selectStudentByFirstName("Ruth");
//
//        addresses.add(address1);
//
//        //ikmoet set addres oproepen
//        student1.setAddress(addresses);
//        studentDAO.insertAddress(address1);
//        entityManager.persist(student1);

        // END STUDENT EN ADDRESS-----------------------------


        //Status one to many naar appl
//        Status status1 = new Status();
//        status1.setStatus_id(2);
//        status1.setStatus("Slecht");
//        statusDAO.createStatus(status1);


//        //Tender one to one naar appl
//        Tender tender1 = new Tender();
//        tender1.setTender_id(1);
//        tender1.setTender_description("Offerte 1");
//        tenderDAO.createTender(tender1);

        //----------------------------------------------------------------

        //Connectie van alle tabellen met applicatie

        ApplicationDAO applicationDAO = new ApplicationDAO(entityManager);

        Application application1 = new Application();
        application1.setApplication_id(1);

        //education
        Education education1 = educationDAO.selectEducationBy(1);
        application1.setEducation(education1);

        //status
        Status status1 = statusDAO.selectStatusById(1);
        application1.setStatus(status1);

        //tender
        Tender tender1 = tenderDAO.selectTenderBy(1);
        application1.setTender(tender1);

        //student
        Student student = studentDAO.selectStudentBy(4);
        application1.setStudent(student);

        applicationDAO.createApplication(application1);


        //RAPPORTAGE



        //applicationDAO.selectAllApplication();

        //Overzicht van de leningen en aan wie het geleend wordt
        //alle app


        //jaaroverzicht met goedgekeurde em afgekeurde
        //meeste afgekeurd en goedgekeurd
        //minst afgekeurd en goedgekeurd


        //AFGEKEURDE PER SCHOOL






//        List<Student> students = studentDAO.selectAllStudent();
//        for (Student student : students) {
//            System.out.println("ID: " + student.getStudent_id()
//                    + " | Naam: " + student.getLastName() + " " + student.getLastName()
//                    + " | Geboortedatum: " + student.getDate_of_birth()
//                    + " | Gender: " + student.getGender()
//                    + " | Telefoon: " + student.getTelephone_number());
//
//        }

//
        // FDP Create gender
//        GenderFactory genderFactory = new GenderFactory();
//        Gender gender = new Gender();
//        sr.unasat.DP.factory.Gender genderString = genderFactory.getGender("MALE");
//        gender.setName(genderString.getName());
//        GenderDAO genderDAO = new GenderDAO(entityManager);
//        genderDAO.createGender(gender);
//
//
//        GenderFactory genderFactory2 = new GenderFactory();
//        Gender gender2 = new Gender();
//        sr.unasat.DP.factory.Gender genderString2 = genderFactory2.getGender("FEMALE");
//        gender2.setName(genderString2.getName());
//        GenderDAO genderDAO1 = new GenderDAO(entityManager);
//        genderDAO1.createGender(gender2);


//
//
//
//
//
//        //SCANNER
//
//        Scanner userInput = new Scanner(System.in);
//        //System.in =the standard input stream
//
//        System.out.println("Send a new request (Y) / (N) Already have an existing application  ");
//        String newOrExistingApp = userInput.next();
//        Application application1 = new Application();
//
//        // applicationDAO.selectAllApplication();
//
////
////
////     //   application1.setApplication_id(2);
//////
//        if (newOrExistingApp.equals("Y")) {
//            System.out.println("Creating new request");
//
//            System.out.println("Is student registered? (Y/N)");
//            String studentRegistered = userInput.next();
//            if (studentRegistered.equals("Y")) {
//                System.out.println("Search registered student by name");
//                String searchStudent = userInput.next();
//                Student foundStudent = studentDAO.selectStudentByFirstName(searchStudent);
//                if (foundStudent != null) {
//                    System.out.println("Student " + searchStudent + "found");
//                    application1.setStudent(foundStudent);
//                } else {
//                    System.out.println("Student not found");
//                }
//
//                System.out.println("Is the education of the student registered? (Y/N)");
//                String educationRegistered = userInput.next();
//                if (educationRegistered.equals("Y")) {
//                    System.out.println("Search registered education by Education name");
//                    System.out.println("ADEK / UNASAT / FHR");
//                    String searchEducation = userInput.next();
//                    //Chain of responsibility
//                    Handler h1 = new AdekHandler();
//                    Handler h2 = new UnasatHandler();
//                    Handler h3 = new FhrHandler();
//                    h1.setSuccessor(h2);
//                    h2.setSuccessor(h3);
//
//                    Education foundEducation = h1.handleRequest(new Request(searchEducation));
//
//                    if (foundEducation != null) {
//                        System.out.println("School " + foundEducation + "found");
//                        application1.setEducation(foundEducation);
//                    } else {
//                        System.out.println("School not found");
//                    }
//
//
//                    System.out.println("Write why you want to request a  tender");
//                    String tenderDescription = userInput.next();
//                    //kijken als dit goed is
//                    Tender tender = new Tender(tenderDescription);
//                    application1.setTender(tender);
//
//
//                    //Request tender
//                    System.out.println("Request tender? (Y/N)");
//                    String requestTender = userInput.next();
//                    if (requestTender.equals("Y")) {
//
//
//                        System.out.println("Invoice of application");
//
//                        Student student = studentDAO.selectStudentByFirstName(searchStudent);
//                        System.out.println("ID: " + student.getStudent_id()
//                                + " | Naam: " + student.getFirstName() + " " + student.getLastName()
//                                + " | Geboortedatum: " + student.getDate_of_birth()
//                                + " | Gender: " + student.getGender()
//                                + " | Telefoon: " + student.getTelephone_number()
//                                + " | Address: " + student.getAddress()
//                        );
//
//
//                        // List<Address> addresses = addressDAO.selectAllAddress();
////                        for (Address address : addresses) {
////                         System.out.println(
////                             " | District: " + address.getDistrict() + " " + student.getLastName()
////                             + " | Geboortedatum: " + student.getDate_of_birth()
////                             + " | Gender: " + student.getGender()
////                             + " | Telefoon: " + student.getTelephone_number());
////
////                        }
//
//                        Education education = educationDAO.selectEducationByEducationName(searchEducation);
//                        System.out.println(
//                                " | Title: " + education.getTitle()
//                                        + " | Education Name: " + education.getEducation_name()
//                                        + " | Amount: " + education.getAmount()
//                                        + " | Type: " + education.getType());
//
//
//                        //Tender tender1 = tenderDAO.selectTenderBy(tenderDescription);
//
//                        System.out.println(
//                                " | Description of tender: " + tender.getTender_description());
//
//
//                        System.out.print("Send application ? (Y/N) ");
//                        String save = userInput.next();
//                        if (save.equals("Y")) {
//                            System.out.println("ApprovedApplication is being processed");
//
////
////                            //education
//                            Education education1 = educationDAO.selectEducationBy(1);
////                         application1.setEducation(education1);
////
////        //status
////                         Status status1 = statusDAO.selectStatusById(1);
////                            application1.setStatus(status1);
////
////        //tender
////                         Tender tender1 = tenderDAO.selectTenderBy(1);
////                         application1.setTender(tender1);
////
////        //student
////                           Student student = studentDAO.selectStudentBy(0);
////                           application1.setStudent(student);
////
////                         applicationDAO.createApplication(application1);
////
//
//
//                            System.out.print("Application approved ? (Y/N) ");
//                            String approved = userInput.next();
//                            if (approved.equals("Y")) {
//                                System.out.println("Student can make transaction");
//                            } else {
//
//                                System.out.println("Choose between 1 and 2 which reason the application is being declined for");
//                                System.out.println("1. Insufficient Papers! ");
//                                System.out.println("2. No Collateral! ");
//                                System.out.println("3. Both reasons");
//
//                                System.out.println("Which reason?");
//                                String declinedReason = userInput.next();
//                                //Decorator
//                                Reason reason = new ReasonDecorator();
//                                switch (declinedReason) {
//                                    case "1":
//                                        reason = new InsufficientPapersReason(reason);
//                                        System.out.println(reason.getDesc());
//                                        break;
//                                    case "2":
//                                        reason = new NoCollateralReason(reason);
//                                        System.out.println(reason.getDesc());
//                                        break;
//                                    case "3":
//                                        reason = new InsufficientPapersReason(reason);
//                                        reason = new NoCollateralReason(reason);
//                                        System.out.println(reason.getDesc());
//                                        break;
//                                    default:
//                                        System.out.println("Wrong number! Chose between 1/ 2/ 3");
//
//
//                                }
//                                //SAVEN IN APPLICATION
//                                application1.setNote(reason.getDesc());
//                                applicationDAO.createApplication(application1);
//
//                            }
//                        }
//
//                    } else {
//                        System.out.println("Student does not want to request a tender");
//                    }
//
//
//                } else {
//                    System.out.print("Enter your title: ");
//                    String title = userInput.next();
//
//                    System.out.print("Enter your education name: ");
//                    String educationName = userInput.next();
//
//                    System.out.print("Enter your amount: ");
//                    int amount = userInput.nextInt();
//
//                    System.out.println(" Which type of education? HBO / MBO / PHD");
//
//                    Education education = new Education(title, educationName, amount);
//
//                    String educationType = userInput.next();
//                    switch (educationType) {
//                        case "HBO":
//                            Type type = typeDAO.selectTypeByTypeEducation("HBO");
//                            education.setType(type);
//                            break;
//                        case "MBO":
//                            Type type1 = typeDAO.selectTypeByTypeEducation("MBO");
//                            education.setType(type1);
//                            break;
//                        case "PHD":
//                            Type type2 = typeDAO.selectTypeByTypeEducation("PHD");
//                            education.setType(type2);
//                            break;
//                        default:
//                            System.out.println("no match");
//                    }
//
//                    educationDAO.createEducation(education);
//
//                    application1.setEducation(education);
//
//
//                    System.out.println("Tender description: ");
//                    String tenderDescription = userInput.next();
//
//                    //saven in application maar id moet ik zoeken en dan connecten?
//                    Tender tender = new Tender(tenderDescription);
//                    application1.setTender(tender);
//
//
//                    // application overview?
//                    System.out.print("Save student? (Y/N) ");
//                    String save = userInput.next();
//                    if (save.equals("Y")) {
//                        System.out.println("ApprovedApplication is being processed");
//
//
//                        System.out.print("ApprovedApplication InsufficientPapersReason? (Y/N) ");
//                        String approved = userInput.next();
//                        if (approved.equals("Y")) {
//                            System.out.println("Student can make transaction");
//                        } else {
//                            System.out.println("ApprovedApplication has been declined");
//                        }
//
//                    }
//
//
//                }
//
//
//            } else {
//
//                System.out.println("Student registration:");
//
//
//                System.out.print("Enter your lastname: ");
//                String lastname = userInput.next();
//
//                System.out.print("Enter your firstname: ");
//                String firstname = userInput.next();
//
//                System.out.print("Enter your date of birth: DD-MM- YYYY ");
//                String date = userInput.next();
//                SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
//
//                Date dateOfBirth = format.parse(date);
//
//                System.out.print("Enter your phone number: ");
//                int telephone_number = userInput.nextInt();
//
//
//                Student student = new Student(lastname, firstname, dateOfBirth, telephone_number);
//
//                //Student student1 =
//                studentDAO.createStudent(student);
//
//                //***** save wel in student tabel
//
//
//                // Student student2 = studentDAO.selectStudentByFirstName(firstname);
//                //  int studentId = student2.getStudent_id();
//
//
//                //id is nodig om erin te zetten andersgaat die join niet werken
//                //zelfde student met id moet ik finden.
//
//
//                System.out.println("More then one address? (Y/N)");
//                String moreAddress = userInput.next();
//                if (moreAddress.equals("Y")) {
//
////                            System.out.println("Search address by streetname name");
////                            String searchEducation = userInput.next();
////                            Education foundEducation = educationDAO.selectEducationByEducationName(searchEducation);
////                            if (foundEducation != null) {
////                                System.out.println("School " + foundEducation + "found");
////                                application1.setEducation(foundEducation);
////                            } else {
////                                System.out.println("School not found");
////                            }
////
//
//                    //more addres i dont have
//                    Set<Address> addresses = new HashSet<>();
//                    System.out.print("Enter your district: ");
//                    String district1 = userInput.next();
//
//                    System.out.print("Enter your Streetname: ");
//                    String streetname1 = userInput.next();
//
//                    Address address = new Address(district1, streetname1);
//                    addresses.add(address);
//
//                    System.out.print("Enter your district: ");
//                    String district2 = userInput.next();
//
//                    System.out.print("Enter your Streetname: ");
//                    String streetname2 = userInput.next();
//
//                    Address address1 = new Address(district2, streetname2);
//                    addresses.add(address1);
//
//                    // connect addres met student
//                    //Address address3 = addressDAO.selectAddressByStreetname(streetname1);
//                    Student student3 = studentDAO.selectStudentByFirstName(firstname);
//                    //ikmoet set addres oproepen
//                    student3.setAddress(addresses);
//                    studentDAO.updateStudent(student3);
//
//
//                } else {
//
//                    //One Address
//                    System.out.print("Enter your district: ");
//                    String district = userInput.next();
//
//                    System.out.print("Enter your Streetname: ");
//                    String streetname = userInput.next();
//
//
//                    Set<Address> addresses = new HashSet<>();
//
//                    Address address = new Address(district, streetname);
//
//                    addresses.add(address);
//                    entityManager.persist(address);
//
//                    // connect addres met student
//
//                    Address address2 = addressDAO.selectAddressByStreetname(streetname);
//                    Student student4 = studentDAO.selectStudentByFirstName(firstname);
//
//
//                    //ikmoet set addres oproepen
//                    student4.setAddress(addresses);
//                    //studentDAO.insertAddress(address2);
//                    studentDAO.updateStudent(student4);
//                    //student saven in application ?
//                    // application1.setStudent(studentId);
//
//
//                    //education
//                    System.out.print("Enter your title: ");
//                    String title1 = userInput.next();
//
//                    System.out.print("Enter your education name: ");
//                    String educationName1 = userInput.next();
//
//                    System.out.print("Enter your amount: ");
//                    int amount1 = userInput.nextInt();
//
//                    System.out.println(" Which type of education? HBO / MBO / PHD");
//
//                    Education education1 = new Education(title1, educationName1, amount1);
//
//                    String educationType1 = userInput.next();
//                    switch (educationType1) {
//                        case "HBO":
//                            Type type = typeDAO.selectTypeByTypeEducation("HBO");
//                            education1.setType(type);
//                            break;
//                        case "MBO":
//                            Type type1 = typeDAO.selectTypeByTypeEducation("MBO");
//                            education1.setType(type1);
//                            break;
//                        case "PHD":
//                            Type type2 = typeDAO.selectTypeByTypeEducation("PHD");
//                            education1.setType(type2);
//                            break;
//                        default:
//                            System.out.println("no match");
//                    }
//
//                    educationDAO.createEducation(education1);
//
//                    application1.setEducation(education1);
//
//
//                    System.out.println("Tender description: ");
//                    String tenderDescription1 = userInput.next();
//
//                    //saven in application maar id moet ik zoeken en dan connecten?
//                    Tender tender1 = new Tender(tenderDescription1);
//                    application1.setTender(tender1);
//
//
//                    // application overview?
//                    System.out.print("Save student? (Y/N) ");
//                    String save1 = userInput.next();
//                    if (save1.equals("Y")) {
//                        System.out.println("ApprovedApplication is being processed");
//
//
//                    }
//                    System.out.print("ApprovedApplication InsufficientPapersReason? (Y/N) ");
//                    String approved = userInput.next();
//                    switch (approved) {
//                        case "Y":
//                            Status status = statusDAO.selectStatusById(1);
//                            //? moet ik hier het saven
//                            System.out.println("Student can make transaction");
//                            break;
//                        case "N":
//                            Status status1 = statusDAO.selectStatusById(2);
//                            //moet ik saven??
//                            System.out.println("ApprovedApplication has been declined");
//                            break;
//                        default:
//                            System.out.println("no match");
//                    }
//
//
//                }
//
//
//            }
//
//        } else {
//                            System.out.println("Searching existing application");
//                            applicationDAO.selectApplicationById(1);
//
//
//            System.out.print("ApprovedApplication InsufficientPapersReason? (Y/N) ");
//                            String approved = userInput.next();
//                            if (approved.equals("Y")) {
//
//                                System.out.println("Student can make transaction");
//                            } else {
//                                System.out.println("ApprovedApplication has been declined");
//                            }
//
//                            //Status
//                            System.out.print("Enter your status: ");
//                            String status = userInput.next();
//
//                            Status status1 = new Status(status);
//
//
//                            //status
//                            application1.setStatus(status1);
//                            entityManager.persist(application1);
//
//            System.out.println("ApprovedApplication has a status");
//                        }
//
//
//                        // applicationDAO.createApplication(application1);
//
//
                    }


}