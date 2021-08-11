package fa.training.utils.db;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class DBConnectionPool {
    private static final String CONN_STR = "jdbc:mariadb://cpl-cpms.duckdns.org:13306/CPL_CPMS";
    private static final String CONN_USR = "guest";
    private static final String CONN_PWD = "guest";
    private static final long MAXIMUM_CONCURRENT_CONN = 32;
    private static final long TIMEOUT_MILIS = 1000 * 60;

    private static ArrayList<DBConnection> freeConn;
    private static Set<DBConnection> busyConn;

    static{
        freeConn = new ArrayList<>();
        busyConn = new HashSet<>();
    }

    public static DBConnection getConn() throws Exception {
        DBConnection conn;
        // there are free connections
        if(freeConn.size() > 0){
            System.out.println("DBPool: Lend connection");
            conn = freeConn.get(0);
            conn.renew();
            freeConn.remove(0);
        } else if(busyConn.size() < MAXIMUM_CONCURRENT_CONN){
            conn = createConnection();
            System.out.println("DBPool: Create new connection, total: " + (freeConn.size() + busyConn.size() + 1));
        } else {
            System.out.println("DBPool: Wait for connection");
            CompletableFuture<DBConnection> cf = CompletableFuture.supplyAsync(() -> waitForConnection());
            while(!cf.isDone());
            try {
                conn = cf.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                throw new DBException();
            }
        }
        busyConn.add(conn);
        return conn;
    }

    public static void release(DBConnection dbConn){
        if(dbConn != null) {
            System.out.println("DBPool: Release connection");
            busyConn.remove(dbConn);
            freeConn.add(0, dbConn);
            dbConn.setTimeout(TIMEOUT_MILIS);
        }
    }

    private static DBConnection waitForConnection(){
        while(freeConn.size() == 0);
        System.out.println("DBPool: Wait for connection");
        return freeConn.get(0);
    }

    private static DBConnection createConnection() throws DBException {
        return new DBConnection(CONN_STR, CONN_USR, CONN_PWD);
    }

    protected static void deleteConnection(DBConnection dbConn){
        freeConn.remove(dbConn);
        System.out.println("DBPool: Delete connection, remain: " + (freeConn.size() + busyConn.size()));
    }
}
