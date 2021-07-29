package fa.training.servlet;

import fa.training.enumeration.ResultFilter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class TicketServlet extends BaseServlet {
    @Override
    protected ResultFilter getDefaultResultFilter() {
        return ResultFilter.NAME;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setTitle(request, "Ticket Manager");
        setBaseJspPath("baseStaff.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        /*int id = getId(request);
        try {
            if (id == -1) {
                response.getWriter().print("invalid");
            } else {
                ticketDAO ticketDAO = new ticketDAO();
                if(ticketDAO.delete(id)){
                    response.getWriter().print("success");
                } else {
                    response.getWriter().print("failed");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
