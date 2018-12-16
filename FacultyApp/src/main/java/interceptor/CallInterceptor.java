package interceptor;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;


/**
 *
 * @author brusu
 */
public class CallInterceptor {
   @AroundInvoke
   public Object methodInterceptor(InvocationContext ctx) throws Exception {
      
      long begin = System.currentTimeMillis();
      Object result =  ctx.proceed();
      System.out.println("[INTERCEPTOR] CoursesBean method: "  + 
              ctx.getMethod().getName() + " end in " + 
              (System.currentTimeMillis() - begin) + " milliseconds");
      return result;
   }
}
