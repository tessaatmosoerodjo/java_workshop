package sr.unasat.DP.decorator;

public class InsufficientPapersReason extends ReasonDecorator {
    private final Reason reason;

    public InsufficientPapersReason(Reason reason) {
        this.reason = reason;
    }

    @Override
    public String getDesc() {
        return reason.getDesc() + "- Insufficient Papers!";
    }

}
