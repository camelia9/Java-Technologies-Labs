package timer;

import java.util.Date;
import javax.ejb.*;

/**
 *
 * @author brusu
 */
@Stateless
public class TimerSessionBean {
    
    @Schedule(second="1", minute="*",hour="*", persistent=false)
    public void doWork(){
        Runtime runtime = Runtime.getRuntime();
        System.out.println(
                "[" + new Date() + "] Total memory used: " + 
                ((runtime.totalMemory() - runtime.freeMemory()) / (1 << 20)) + " MB"
        );
    }
}
