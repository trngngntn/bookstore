package fa.training.CPMS;

import fa.training.utils.db.DBConnection;
import fa.training.utils.db.DBConnectionPool;

import java.io.*;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DBConnection dbConn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            dbConn = DBConnectionPool.getConn();
            statement = dbConn.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM `test`");
            request.setAttribute("dbMesg", "OK!");
            if(resultSet.next()){
                request.setAttribute("mesg", resultSet.getString(1));
            } else {
                request.setAttribute("mesg", "Failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("dbMesg", "Failed!");
        }
        request.getRequestDispatcher("jsp/testdb.jsp").forward(request, response);
    }

    public void destroy() {
    }
}