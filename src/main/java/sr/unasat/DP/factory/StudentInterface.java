package sr.unasat.DP.factory;

import sr.unasat.entities.Student;

public interface StudentInterface {

    Student createStudent(Student student);

    Student getStudent(String firstname);
}
