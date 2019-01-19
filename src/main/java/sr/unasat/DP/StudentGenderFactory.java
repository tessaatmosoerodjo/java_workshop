package sr.unasat.DP;

public class StudentGenderFactory {

    public StudentGender getInstance(String string){
        if (string.equals("Male"))
            return new Male();
        else
            return new Female();
    }
}
