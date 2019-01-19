package sr.unasat.DP.decorator;

public class NoCollateralReason extends ReasonDecorator {
    private final Reason reason;

    public NoCollateralReason(Reason reason) {
        this.reason = reason;
    }

    @Override
    public String getDesc() {
        return reason.getDesc() + "- No Collarateral!";
    }
}
