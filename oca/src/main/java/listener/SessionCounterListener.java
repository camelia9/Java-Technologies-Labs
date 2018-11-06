package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener()

public class SessionCounterListener implements HttpSessionListener {

    private static final AtomicInteger counter;

    static {
        counter = new AtomicInteger(0);
    }

    public synchronized static int getNumberOfSessions(){
        return counter.get();
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        counter.incrementAndGet();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        counter.decrementAndGet();
    }


}
