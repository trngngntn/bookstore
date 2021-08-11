package fa.training.utils.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection{
    private Connection connection;
    private boolean used;
    private Thread close;

    public DBConnection(String connStr, String usr, String pwd) throws DBException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(connStr, usr, pwd);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DBException("UnableToCreateConnection");
        }
    }

    public Connection getConnection(){
        return connection;
    }

    protected void setTimeout(long time){
        close = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(time);
                    close();
                    System.out.println("DBConn: Connection closed!");
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                    System.out.println("DBConn: Terminate closing connection");
                }
            }
        });
        close.start();
    }

    protected void renew(){
        if(!close.isInterrupted()){
            close.interrupt();
        }
    }

    protected void close(){
        try {
            DBConnectionPool.deleteConnection(this);
            if(!connection.isClosed()){
                connection.close();
            }
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
        }
    }
}
