package sr.unasat.DP.decorator;

public class DeclinedReason implements Reason {

    @Override
    public String getDesc() {
        return "Decline ";
    }
}
