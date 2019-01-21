package sr.unasat.DP.factory;

public class GenderFactory {

    public Gender getGender(String string) {

        switch (string) {
            case "MALE":
                return new Male();

            case "FEMALE":
                return new Female();

            default:
                System.out.println("no match");
        }
        return null;
    }
}

//create object without exposing creation logic
