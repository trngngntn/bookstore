package fa.training.dao;

import fa.training.entity.BaseEntity;
import fa.training.meta.Meta;
import fa.training.utils.Parameters;
import fa.training.utils.db.DBConnection;
import fa.training.utils.db.DBConnectionPool;
import fa.training.utils.db.DBConnectionUtils;
import fa.training.utils.db.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDAO<T extends BaseEntity<T>> {
    final private Class<? extends Meta> meta;
    private Meta[] metaList;
    private Class<T> entity;
    private String tableName;

    protected BaseDAO(Class<? extends Meta> meta) {
        this.meta = meta;
        try {
            this.entity = (Class<T>) meta.getDeclaredMethod("getEntityClass").invoke(null);
            this.metaList = (Meta[]) meta.getDeclaredMethod("values").invoke(null);
            this.tableName = (String) meta.getDeclaredMethod("getDBTableName").invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getTotalSearchPage(Meta[] filter, String[] keyword) throws Exception {
        Object[] args = new Object[filter.length];
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(*) FROM (SELECT ");
        sql.append(String.format(" DENSE_RANK() OVER (ORDER BY `%s`) AS `sort` FROM `%s` WHERE ", metaList[0].getDBName() , tableName));

        for (int i = 0; i < filter.length; i++) {
            if (i > 0) {
                sql.append("AND ");
            }
            keyword[i] = keyword[i].trim();
            if (filter[i].isExclusive()) {
                sql.append(String.format("`%s` = ? ", filter[i].getDBName()));
            } else {
                keyword[i] = "%" + keyword[i] + "%";
                sql.append(String.format("`%s` LIKE ? ", filter[i].getDBName()));
            }
            args[i] = keyword[i];
        }
        sql.append(") tbl");
        System.out.println(sql);
        ResultSet resultSet = null;
        try {
            resultSet = getResultSet(sql.toString(), args);
            if (resultSet.next()) {
                int total = resultSet.getInt(1);
                return total / Parameters.PAGINATION_ENTRY_COUNT + (total % Parameters.PAGINATION_ENTRY_COUNT == 0 ? 0 : 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException();
        } finally {
            DBConnectionUtils.closeResultSet(resultSet);
        }
        return -1;
    }

    public int getTotalPage() throws Exception {
        final String SQL = String.format("SELECT COUNT(*) FROM `%s`", tableName);
        ResultSet resultSet = null;
        try {
            resultSet = getResultSet(SQL);
            if (resultSet.next()) {
                int total = resultSet.getInt(1);
                return total / Parameters.PAGINATION_ENTRY_COUNT + (total % Parameters.PAGINATION_ENTRY_COUNT == 0 ? 0 : 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException();
        } finally {
            DBConnectionUtils.closeResultSet(resultSet);
        }
        return -1;
    }

    public T get(Object id) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");

        for (Meta meta : metaList) {
            if (meta.getDBName() != null)
                sql.append(String.format("`%s`,", meta.getDBName()));
        }

        sql.deleteCharAt(sql.length() - 1);
        sql.append(String.format(" FROM `%s`", meta.getDeclaredMethod("getDBTableName").invoke(null)));
        sql.append(String.format(" WHERE `%s` = ?", metaList[0].getDBName()));
        ResultSet resultSet = null;
        try {
            resultSet = getResultSet(sql.toString(), id);
            if (resultSet.next()) {
                return parseResultSet(resultSet, metaList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException();
        } finally {
            DBConnectionUtils.closeResultSet(resultSet);
        }
        return null;
    }

    protected T get(Object id, Meta... metaList) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");

        for (Meta meta : metaList) {
            if (meta.getDBName() != null)
                sql.append(String.format("`%s`,", meta.getDBName()));
        }

        sql.deleteCharAt(sql.length() - 1);
        sql.append(String.format(" FROM `%s`", meta.getDeclaredMethod("getDBTableName").invoke(null)));
        sql.append(String.format(" WHERE `%s` = ?", metaList[0].getDBName()));
        ResultSet resultSet = null;
        try {
            resultSet = getResultSet(sql.toString(), id);
            if (resultSet.next()) {
                return parseResultSet(resultSet, metaList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException();
        } finally {
            DBConnectionUtils.closeResultSet(resultSet);
        }
        return null;
    }

    public List<T> getList(int index) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM (SELECT ");

        for (Meta meta : metaList) {
            if (meta.getDBName() != null)
                sql.append(String.format("`%s`,", meta.getDBName()));
        }

        sql.append(String.format(" DENSE_RANK() OVER (ORDER BY `%s`) AS `sort` FROM `%s`) tbl ", metaList[0].getDBName(), tableName));
        sql.append(" WHERE `sort` > ? AND `sort` <= ?");

        ResultSet resultSet = null;
        ArrayList<T> result = new ArrayList<>();
        try {
            resultSet = getResultSet(sql.toString(), (index - 1) * Parameters.PAGINATION_ENTRY_COUNT, index * Parameters.PAGINATION_ENTRY_COUNT);
            while (resultSet.next()) {
                result.add(parseResultSet(resultSet, metaList));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException();
        } finally {
            DBConnectionUtils.closeResultSet(resultSet);
        }
        return result;
    }

    public List<T> getList(int index, Meta... metaList) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM (SELECT ");

        for (Meta meta : metaList) {
            if (meta.getDBName() != null)
                sql.append(String.format("`%s`,", meta.getDBName()));
        }

        sql.append(String.format(" DENSE_RANK() OVER (ORDER BY `%s`) AS `sort` FROM `%s`) tbl ", metaList[0].getDBName(), tableName));
        sql.append(" WHERE `sort` > ? AND `sort` <= ?");

        ResultSet resultSet = null;
        ArrayList<T> result = new ArrayList<>();
        try {
            resultSet = getResultSet(sql.toString(), (index - 1) * Parameters.PAGINATION_ENTRY_COUNT, index * Parameters.PAGINATION_ENTRY_COUNT);
            while (resultSet.next()) {
                result.add(parseResultSet(resultSet, metaList));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException();
        } finally {
            DBConnectionUtils.closeResultSet(resultSet);
        }
        return result;
    }

    public List<T> search(Meta[] filter, String[] keyword, int index) throws Exception {
        Object[] args = new Object[filter.length + 2];
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM (SELECT ");

        for (Meta meta : metaList) {
            if (meta.getDBName() != null)
                sql.append(String.format("`%s`,", meta.getDBName()));
        }
        sql.append(String.format(" DENSE_RANK() OVER (ORDER BY `%s`) AS `sort` FROM `%s` WHERE ", metaList[0].getDBName(), tableName));

        for (int i = 0; i < filter.length; i++) {
            keyword[i] = keyword[i].trim();
            if (i > 0) {
                sql.append("AND ");
            }
            if (filter[i].isExclusive()) {
                sql.append(String.format("`%s` = ? ", filter[i].getDBName()));
            } else {
                keyword[i] = "%" + keyword[i] + "%";
                sql.append(String.format("`%s` LIKE ? ", filter[i].getDBName()));
            }
            args[i] = keyword[i];
        }
        sql.append(") tbl WHERE `sort` > ? AND `sort` <= ?");

        args[filter.length] = (index - 1) * Parameters.PAGINATION_ENTRY_COUNT;
        args[filter.length + 1] = index * Parameters.PAGINATION_ENTRY_COUNT;

        ResultSet resultSet = null;
        ArrayList<T> result = new ArrayList<>();
        try {
            resultSet = getResultSet(sql.toString(), args);
            while (resultSet.next()) {
                result.add(parseResultSet(resultSet, metaList));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException();
        } finally {
            DBConnectionUtils.closeResultSet(resultSet);
        }
        return result;
    }

    public boolean add(T newObj) throws Exception {
        ArrayList argList = new ArrayList();
        StringBuilder sql = new StringBuilder();
        sql.append(String.format("INSERT INTO `%s`(", tableName));
        for (int i = 0; i < metaList.length; i++) {
            if (metaList[i].getDBName() != null && !metaList[i].getDBName().equals("id")) {
                sql.append(String.format(" `%s`,", metaList[i].getDBName()));
                argList.add(newObj.get(metaList[i]));
            }
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" ) VALUES( ");
        for (Meta meta : metaList) {
            if (meta != null && meta.getDBName() != null && !meta.getDBName().equals("id"))
                sql.append(String.format(" ?,", meta.getDBName()));
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" )");
        try {
            return getResult(sql.toString(), argList.toArray()) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException();
        }
    }

    public boolean update(T newObj) throws Exception {
        Object[] args = new Object[metaList.length];
        StringBuilder sql = new StringBuilder();
        sql.append(String.format("UPDATE `%s` SET", tableName));
        for (int i = 1; i < metaList.length; i++) {
            if (metaList[i].getDBName() != null) {
                sql.append(String.format(" `%s` = ?,", metaList[i].getDBName()));
                args[i - 1] = newObj.get(metaList[i]);
            }
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(String.format(" WHERE `%s` = ?", metaList[0].getDBName()));
        args[args.length - 1] = newObj.get(metaList[0]);
        System.out.println(sql);
        try {
            return getResult(sql.toString(), args) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException();
        }
    }

    public boolean update(T newObj, Meta... metaList) throws Exception {
        Object[] args = new Object[metaList.length];
        StringBuilder sql = new StringBuilder();
        sql.append(String.format("UPDATE `%s` SET", tableName));
        for (int i = 1; i < metaList.length; i++) {
            if (metaList[i].getDBName() != null) {
                sql.append(String.format(" `%s` = ?,", metaList[i].getDBName()));
                args[i - 1] = newObj.get(metaList[i]);
            }
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(String.format(" WHERE `%s` = ?", metaList[0].getDBName()));
        args[args.length - 1] = newObj.get(metaList[0]);
        System.out.println(sql);
        try {
            return getResult(sql.toString(), args) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException();
        }
    }

    protected T parseResultSet(ResultSet resultSet, Meta... metaList) throws Exception {
        T result = entity.getDeclaredConstructor().newInstance();
        for (Meta meta : metaList) {
            if (meta != null && meta.getDBName() != null) { //exclude null meta and meta without DB name
                if (int.class.equals(meta.getType()) || Integer.class.equals(meta.getType())) {
                    result.set(meta, resultSet.getInt(meta.getDBName()));
                } else if (float.class.equals(meta.getType()) || Float.class.equals(meta.getType())) {
                    result.set(meta, resultSet.getFloat(meta.getDBName()));
                } else if (double.class.equals(meta.getType()) || Double.class.equals(meta.getType())) {
                    result.set(meta, resultSet.getDouble(meta.getDBName()));
                } else if (boolean.class.equals(meta.getType()) || Boolean.class.equals(meta.getType())) {
                    result.set(meta, resultSet.getBoolean(meta.getDBName()));
                } else if (String.class.equals(meta.getType())) {
                    result.set(meta, resultSet.getString(meta.getDBName()));
                } else if (Date.class.equals(meta.getType())) {
                    result.set(meta, resultSet.getDate(meta.getDBName()));
                } else if (Time.class.equals(meta.getType())) {
                    result.set(meta, resultSet.getTime(meta.getDBName()));
                } else {
                    throw new DBException("UnsupportedArgumentType");
                }
            }
        }
        return result;
    }

    protected void prepStatement(PreparedStatement statement, Object... args) throws Exception {
        int position = 0;
        for (Object arg : args) {
            ++position;
            if (arg == null){
                statement.setNull(position, Types.CHAR);
                continue;
            }
            if (Integer.TYPE.equals(arg.getClass()) || Integer.class.equals(arg.getClass())) {
                statement.setInt(position, (Integer) arg);
            } else if (Float.TYPE.equals(arg.getClass()) || Float.class.equals(arg.getClass())) {
                statement.setFloat(position, (Float) arg);
            } else if (Double.TYPE.equals(arg.getClass()) || Double.class.equals(arg.getClass())) {
                statement.setDouble(position, (Double) arg);
            } else if (Boolean.TYPE.equals(arg.getClass()) || Boolean.class.equals(arg.getClass())) {
                statement.setBoolean(position, (Boolean) arg);
            } else if (String.class.equals(arg.getClass())) {
                statement.setString(position, (String) arg);
            } else if (Date.class.equals(arg.getClass())) {
                statement.setDate(position, (Date) arg);
            } else if (Time.class.equals(arg.getClass())) {
                statement.setTime(position, (Time) arg);
            } else {
                throw new Exception("UnsupportedArgumentType");
            }
        }
    }

    protected ResultSet getResultSet(String prepSQL, Object... args) throws Exception {
        DBConnection dbConn = null;
        PreparedStatement statement = null;
        try {
            dbConn = DBConnectionPool.getConn();
            statement = dbConn.getConnection().prepareStatement(prepSQL);
            prepStatement(statement, args);
            return statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException();
        } finally {
            DBConnectionUtils.closeStatement(statement);
            DBConnectionPool.release(dbConn);
        }
    }

    protected int getResult(String prepSQL, Object... args) throws Exception {
        DBConnection dbConn = null;
        PreparedStatement statement = null;
        try {
            dbConn = DBConnectionPool.getConn();
            statement = dbConn.getConnection().prepareStatement(prepSQL);
            prepStatement(statement, args);
            return statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException();
        } finally {
            DBConnectionUtils.closeStatement(statement);
            DBConnectionPool.release(dbConn);
        }
    }
}
