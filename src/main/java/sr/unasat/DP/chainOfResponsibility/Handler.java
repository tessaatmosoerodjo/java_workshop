package sr.unasat.DP.chainOfResponsibility;


import sr.unasat.entities.Application;
import sr.unasat.entities.Education;

public abstract class Handler {

    protected Handler successor;
    Education education;

    // public abstract Education handleRequest();

    abstract protected int getOption();

    abstract protected Application getMethod(Application application);

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }


    public Application processRequest(Request request, Application application) {
        if (request.getOption() == this.getOption()) {
            return getMethod(application);
        } else if (successor != null) {
            successor.processRequest(request, application);
        }
        return application;
    }

}



//recieve object -> pass request along the chain till object handles it
