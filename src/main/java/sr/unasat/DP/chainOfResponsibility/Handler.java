package sr.unasat.DP.chainOfResponsibility;

import sr.unasat.entities.Education;

public abstract class Handler {
    protected Handler m_successor;

    public void setSuccessor(Handler successor) {
        m_successor = successor;
    }

    public abstract Education handleRequest(Request request);
}
