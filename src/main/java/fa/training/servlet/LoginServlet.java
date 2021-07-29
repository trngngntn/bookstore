package fa.training.servlet;

import fa.training.dao.EmployeeDAO;
import fa.training.utils.HashUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
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
                int uid = Integer.parseInt(savedUid);
                EmployeeDAO employeeDAO = new EmployeeDAO();
                String pwdHash = employeeDAO.getPwdHash(uid);
                String calKey = HashUtils.generateCookieKey(uid, pwdHash);
                System.out.println(savedKey + " : " + calKey);
                if (savedKey.equals(calKey)) {
                    request.getSession().setAttribute("activeUid", uid);
                    response.sendRedirect("profile");
                }
            } else if (request.getSession().getAttribute("activeUid") != null) {
                response.sendRedirect("profile");
            } else {
                request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username.equals("admin") && password.equals("admin")) {
            request.getSession().setAttribute("activeUid", 0);
            response.sendRedirect("employee");
        } else {
            try {
                String rem = request.getParameter("rem");
                EmployeeDAO employeeDAO = new EmployeeDAO();
                int uid = employeeDAO.login(username, password);
                if (uid != -1) {
                    request.getSession().setAttribute("activeUid", uid);
                    if (rem != null && rem != "") {
                        response.addCookie(new Cookie("saved-uid", String.format("%d", uid)));
                        String pwdHash = employeeDAO.getPwdHash(uid);
                        String calKey = HashUtils.generateCookieKey(uid, pwdHash);
                        response.addCookie(new Cookie("saved-key", calKey));
                    }
                    response.sendRedirect("parkingLot");
                } else {
                    request.setAttribute("failed", true);
                    request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
                }
            } catch (Exception e) {

            }
        }

    }
}
