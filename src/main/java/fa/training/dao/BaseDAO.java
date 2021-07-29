package fa.training.dao;

import fa.training.entity.BaseEntity;
import fa.training.enumeration.ResultFilter;
import fa.training.meta.Meta;
import fa.training.utils.Parameters;
import fa.training.utils.db.DBConnection;
import fa.training.utils.db.DBConnectionPool;
import fa.training.utils.db.DBConnectionUtils;

import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDAO<T extends BaseEntity> {
    private Class meta;
    private Meta[] metaList;
    private Class entity;
    private String tableName;

    protected BaseDAO(Class meta) {
        this.meta = meta;
        try {
            this.entity = (Class) meta.getDeclaredMethod("getEntityClass").invoke(null);
            this.metaList = (Meta[]) meta.getDeclaredMethod("values").invoke(null);
            this.tableName = (String) meta.getDeclaredMethod("getDBTableName").invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            throw e;
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
        sql.append(" WHERE `id` = ?");
        ResultSet resultSet = null;
        try {
            resultSet = getResultSet(sql.toString(), id);
            if (resultSet.next()) {
                return parseResultSet(resultSet, metaList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
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
        sql.append(" WHERE `id` = ?");
        ResultSet resultSet = null;
        try {
            resultSet = getResultSet(sql.toString(), id);
            if (resultSet.next()) {
                return parseResultSet(resultSet, metaList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
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
            throw e;
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

        sql.append(String.format(" DENSE_RANK() OVER (ORDER BY `id`) AS `sort` FROM `%s`) tbl ", tableName));
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
            throw e;
        } finally {
            DBConnectionUtils.closeResultSet(resultSet);
        }
        return result;
    }

    public List<T> search(ResultFilter[] filter, String[] keyword, int index) throws Exception {
        Object[] args = new Object[filter.length + 2];
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM (SELECT ");

        for (Meta meta : metaList) {
            if (meta.getDBName() != null)
                sql.append(String.format("`%s`,", meta.getDBName()));
        }
        sql.append(String.format(" DENSE_RANK() OVER (ORDER BY `id`) AS `sort` FROM `%s` WHERE ", tableName));

        for (int i = 0; i < filter.length; i++) {
            keyword[i] = keyword[i].trim();
            if (filter[i].isExclusive()) {
                sql.append(String.format("`%s` = ? ", filter[i].getLabel()));
            } else {
                keyword[i] = "%" + keyword[i] + "%";
                sql.append(String.format("`%s` LIKE ? ", filter[i].getLabel()));
            }
            if (i > 0) {
                sql.append("AND ");
            }
            args[i] = keyword[i];
        }
        sql.append(") tbl WHERE `sort` > ? AND `sort` <= ?");

        System.out.println(sql.toString());
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
            throw e;
        } finally {
            DBConnectionUtils.closeResultSet(resultSet);
        }
        return result;
    }

    public boolean add(T newObj) throws Exception {
        return false;
    }

    public boolean update(T newObj) throws Exception {
        return false;
    }

    protected T parseResultSet(ResultSet resultSet, Meta... metaList) throws Exception {
        T result = (T) entity.getDeclaredConstructor().newInstance();
        for (Meta meta : metaList) {
            Method setMethod;
            try {
                setMethod = entity.getDeclaredMethod("set", Meta.class, Object.class);
            } catch (NoSuchMethodException e) {
                setMethod = entity.getSuperclass().getDeclaredMethod("set", Meta.class, Object.class);
            }
            if (meta.getDBName() != null) { //exclude meta without DB name
                if (int.class.equals(meta.getType()) || Integer.class.equals(meta.getType())) {
                    setMethod.invoke(result, meta, resultSet.getInt(meta.getDBName()));
                } else if (float.class.equals(meta.getType()) || Float.class.equals(meta.getType())) {
                    setMethod.invoke(result, meta, resultSet.getFloat(meta.getDBName()));
                } else if (double.class.equals(meta.getType()) || Double.class.equals(meta.getType())) {
                    setMethod.invoke(result, meta, resultSet.getDouble(meta.getDBName()));
                } else if (boolean.class.equals(meta.getType()) || Boolean.class.equals(meta.getType())) {
                    setMethod.invoke(result, meta, resultSet.getBoolean(meta.getDBName()));
                } else if (String.class.equals(meta.getType())) {
                    setMethod.invoke(result, meta, resultSet.getString(meta.getDBName()));
                } else if (Date.class.equals(meta.getType())) {
                    setMethod.invoke(result, meta, resultSet.getDate(meta.getDBName()));
                } else if (Time.class.equals(meta.getType())) {
                    setMethod.invoke(result, meta, resultSet.getTime(meta.getDBName()));
                } else {
                    throw new Exception("UnsupportedArgumentType");
                }
            }
        }
        return result;
    }

    protected void prepStatement(PreparedStatement statement, Object... args) throws Exception {
        for (int i = 0; i < args.length; i++) {
            if (int.class.equals(args[i].getClass()) || Integer.class.equals(args[i].getClass())) {
                statement.setInt(i + 1, (Integer) args[i]);
            } else if (float.class.equals(args[i].getClass()) || Float.class.equals(args[i].getClass())) {
                statement.setFloat(i + 1, (Float) args[i]);
            } else if (double.class.equals(args[i].getClass()) || Double.class.equals(args[i].getClass())) {
                statement.setFloat(i + 1, (Float) args[i]);
            } else if (boolean.class.equals(args[i].getClass()) || Boolean.class.equals(args[i].getClass())) {
                statement.setBoolean(i + 1, (Boolean) args[i]);
            } else if (String.class.equals(args[i].getClass())) {
                statement.setString(i + 1, (String) args[i]);
            } else if (Date.class.equals(args[i].getClass())) {
                statement.setDate(i + 1, (Date) args[i]);
            } else if (Time.class.equals(args[i].getClass())) {
                statement.setTime(i + 1, (Time) args[i]);
            } else {
                throw new Exception("UnsupportedArgumentType");
            }
        }
    }

    protected ResultSet getResultSet(String prepSQL, Object... args) throws Exception {
        DBConnection dbConn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            dbConn = DBConnectionPool.getConn();
            statement = dbConn.getConnection().prepareStatement(prepSQL);
            prepStatement(statement, args);
            return statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBConnectionUtils.closeStatement(statement);
            DBConnectionPool.release(dbConn);
        }
    }

    protected int getResult(String prepSQL, Object... args) throws Exception {
        DBConnection dbConn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            dbConn = DBConnectionPool.getConn();
            statement = dbConn.getConnection().prepareStatement(prepSQL);
            prepStatement(statement, args);
            return statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBConnectionUtils.closeStatement(statement);
            DBConnectionPool.release(dbConn);
        }
    }
}
