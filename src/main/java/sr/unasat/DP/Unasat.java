package sr.unasat.DP;

public class Unasat implements SchoolChain {

    private SchoolChain chain;

    @Override
    public void setNextChain(SchoolChain nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void chosenSchool(School school) {


    }
}
