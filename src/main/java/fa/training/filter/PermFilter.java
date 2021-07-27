package fa.training.filter;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "PermFilter")
public class PermFilter implements Filter {
    private static final String[] permit = {"assets", "login", "register"};
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        //System.out.println(httpRequest.getRequestURI() + " " + httpRequest.getPathInfo());
        Integer uid = (Integer) httpRequest.getSession().getAttribute("activeUid");
        boolean valid = false;
        if(uid == null){
            for(String path : permit){
                if(httpRequest.getRequestURI().startsWith(httpRequest.getContextPath() + "/" + path)){
                    valid = true;
                }
            }
        } else {
            valid = true;
        }
        if(valid){
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        }
        //System.out.println("After chain");
    }
}
