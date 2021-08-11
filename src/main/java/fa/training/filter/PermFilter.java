package fa.training.filter;

import fa.training.dao.EmployeeDAO;
import fa.training.servlet.LoginServlet;
import fa.training.utils.HashUtils;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "PermFilter")
public class PermFilter implements Filter {
    private static final String[] GUEST_PERM = {"assets", "login", "register"};
    private static final String[] STAFF_PERM = {"assets", "login", "logout", "register", "car", "bookingOffice", "parkingLot", "ticket", "trip"};
    private static final String[] DENIED = {".jsp"};

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        //System.out.println(httpRequest.getRequestURI() + " " + httpRequest.getPathInfo());
        //System.out.println("Passing filter");
        Integer uid = (Integer) httpRequest.getSession().getAttribute("activeUid");
        boolean valid = false;
        if (uid == null) {
            for (String path : GUEST_PERM) {
                if (httpRequest.getRequestURI().startsWith(httpRequest.getContextPath() + "/" + path)) {
                    valid = true;
                }
            }
        } else if (uid == 0) {
            valid = true;
            for (String path : DENIED) {
                if (httpRequest.getRequestURI().endsWith(path)) {
                    valid = false;
                }
            }
        }
        // comment
        else {
            for (String path : STAFF_PERM) {
                if (httpRequest.getRequestURI().startsWith(httpRequest.getContextPath() + "/" + path)) {
                    valid = true;
                }
            }
        }
        if (valid) {
            chain.doFilter(request, response);
        } else {
            if (uid == null) {
                Cookie[] cookies = httpRequest.getCookies();
                String savedUid = null /*= CookieUtils.getValue(cookies, "saved-uid")*/,
                        savedKey = null /*= CookieUtils.getValue(cookies, "saved-key")*/;
                if (cookies != null)
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("saved-uid")) {
                            savedUid = cookie.getValue();
                        }
                        if (cookie.getName().equals("saved-key")) {
                            savedKey = cookie.getValue();
                        }
                    }
                try {
                    if (savedUid != null && savedKey != null && !savedUid.equals("") && !savedKey.equals("")) {
                        uid = Integer.parseInt(savedUid);
                        EmployeeDAO employeeDAO = new EmployeeDAO();
                        String pwdHash = employeeDAO.getPwdHash(uid);
                        String calKey = HashUtils.generateCookieKey(uid, pwdHash);
                        System.out.println(savedKey + " : " + calKey);
                        if (savedKey.equals(calKey)) {
                            httpRequest.getSession().setAttribute("activeUid", uid);
                            chain.doFilter(request, response);
                        }
                    } else {
                        request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
                    }
                } catch (Exception e) {
                }
                //httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
                //System.out.println(httpRequest.getContextPath() + "/login");
            } else {
                httpRequest.getRequestDispatcher("/jsp/access-denied.jsp").forward(request, response);
            }
        }
        //System.out.println("After chain");
    }
}
