package bean;

import listener.SessionCounterListener;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean(name="SessionCounterBean")
@ViewScoped
public class SessionCounterBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private int numberOfSessions;

    public int getNumberOfSessions(){
        System.out.println("get number of sessions");
        return SessionCounterListener.getNumberOfSessions();
    }
}
