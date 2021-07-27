package fa.training.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseServlet extends HttpServlet {
    private String baseJspPath;
    private final static String JSP_ROOT = "/jsp/";

    protected void setBaseJspPath(String path) {
        baseJspPath = path;
    }

    protected int getId(HttpServletRequest request){
        String idS = request.getPathInfo();
        if(idS == null){
            return -1;
        } else {
            idS = idS.substring(1);
        }
        int id = 0;
        try{
            id = Integer.parseInt(idS);
        } catch (NumberFormatException e){
            return -1;
        }
        return id;
    }

    protected int getIndex(HttpServletRequest request){
        String indexS = request.getParameter("index");
        int index = 1;
        if(indexS == null) return index;
        try{
            index = Integer.parseInt(indexS);
        } catch (NumberFormatException e){
            return -1;
        }
        return index;
    }

    protected void setTitle(HttpServletRequest request, String title) {
        request.setAttribute("pageTitle", title);
    }

    protected void forwardToJsp(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        if (request.getParameter("raw") != null) {
            request.getRequestDispatcher(JSP_ROOT + path).forward(request, response);
        } else {
            request.setAttribute("page", path);
            request.getRequestDispatcher(JSP_ROOT + baseJspPath).forward(request, response);
        }
    }

    protected void forwardToServlet(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }
}
