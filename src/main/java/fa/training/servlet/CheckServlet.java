package fa.training.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CheckServlet", value = "/check")
public class CheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");
        String email = request.getParameter("email");
        String licensePlate = request.getParameter("licensePlate");
        if(account != null){

        }
        if(email != null){

        }
        if(licensePlate != null){

        }
    }
}
