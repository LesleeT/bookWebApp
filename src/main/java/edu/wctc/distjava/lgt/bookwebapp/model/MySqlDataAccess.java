package edu.wctc.distjava.lgt.bookwebapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.Vector;

public class MySqlDataAccess implements DataAccess {

    private final int ALL_RECORDS = 0;
    private final boolean DEBUG = true;

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement pstmt;

    @Override
    public void openConnection(String driverClass,
            String url, String userName, String password)
            throws ClassNotFoundException, SQLException {

        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);
    }

    @Override
    public void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    @Override
    public final int updateRecord(String tableName, List<String> colNames, List<Object> colValues, String pkField, Object pkValue)
            throws ClassNotFoundException, SQLException {
        String sql = "UPDATE " + tableName + " SET ";
        //update does not use parentheses in string joiner
        List<String> cols = new ArrayList();
        cols.add("author_name");
        cols.add("date_added");
        StringJoiner sj = new StringJoiner(", ", "", "");
        for (String col : colNames) {
            sj.add(col + " = ? ");
        }

        sql += sj.toString();

        //sj is full of data-must clear it by recreating it
        sj = new StringJoiner(" ", "", "");
        for (Object value : colValues) {
            sj.add("");
        }

        sql += " WHERE " + pkField + " = ?;";
        //sql += sj.toString();

        if (DEBUG) {
            System.out.println(sql);
        }

        pstmt = conn.prepareStatement(sql);
        for (int i = 1; i <= colValues.size(); i++) {
            pstmt.setObject(i, colValues.get(i - 1));
        }

        pstmt.setObject(colValues.size() +1, pkValue);
        System.out.println(sql);
        return pstmt.executeUpdate();
    }

    @Override
    public final int createRecord(String tableName, List<String> colNames, List<Object> colValues)
            throws ClassNotFoundException, SQLException {

        String sql = "INSERT INTO " + tableName + " ";
        StringJoiner sj = new StringJoiner(", ", "(", ")");
        for (String col : colNames) {
            sj.add(col);
        }

        sql += sj.toString();
        sql += " VALUES ";

        //sj is full of data-must clear it by recreating it
        sj = new StringJoiner(", ", "(", ")");
        for (Object value : colValues) {
            sj.add("?");
        }
        sql += sj.toString();
        if (DEBUG) {
            System.out.println(sql);
        }

        pstmt = conn.prepareStatement(sql);
        for (int i = 1; i <= colValues.size(); i++) {
            pstmt.setObject(i, colValues.get(i - 1));
        }

        return pstmt.executeUpdate();
    }

    /**
     * Returns records from a table. Requires and open connection.
     *
     * @param tableName
     * @param maxRecords
     * @return
     * @throws SQLException
     */
    @Override
    public final int deleteRecordById(String tableName, String pkColName, Object pkValue)
            throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM " + tableName + " WHERE " + " author_id" + " = ? ";

        pstmt = conn.prepareStatement(sql);
        pstmt.setObject(1, pkValue);
        //int recsDeleted = pstmt.executeUpdate();
        System.out.println(sql);
        return pstmt.executeUpdate();
    }

    public final List<Map<String, Object>> findRecordById(String tableName, String pkColName, Object pkValue) throws SQLException {

        String sql = "SELECT * FROM " + tableName + " WHERE " + pkColName + " = ? ";

        pstmt = conn.prepareCall(sql);
        pstmt.setObject(1, pkValue);

        if (DEBUG) { 
            System.out.println(sql);
        }

        rs = pstmt.executeQuery();

        Map<String, Object> record = new LinkedHashMap<>();

        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();

        List<Map<String, Object>> data = new Vector<>();

        while (rs.next()) {
            record = new LinkedHashMap<>();
            for (int colNum = 1; colNum <= colCount; colNum++) {
                record.put(rsmd.getColumnName(colNum), rs.getObject(colNum));
            }
            data.add(record);
        }

        return data;
    }

    @Override
    public final List<Map<String, Object>> getAllRecords(String tableName, int maxRecords)
            throws SQLException, ClassNotFoundException {

        List<Map<String, Object>> rawData = new Vector<>();
        String sql = "";

        if (maxRecords > ALL_RECORDS) {
            sql = "select * from " + tableName + " limit " + maxRecords;
        } else {
            sql = "select * from " + tableName;
        }

        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);

        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        Map<String, Object> record = null;

        while (rs.next()) {
            record = new LinkedHashMap<>();
            for (int colNum = 1; colNum <= colCount; colNum++) {
                record.put(rsmd.getColumnName(colNum), rs.getObject(colNum));
            }
            rawData.add(record);
        }

        return rawData;
    }

//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//
//        DataAccess db = new MySqlDataAccess();
//
//        db.openConnection("com.mysql.jdbc.Driver",
//                "jdbc:mysql://localhost:3306/book",
//                "root", "admin");
//
//        //update
//        int recsUpdated = db.updateRecord("author",
//                Arrays.asList("author_name", "date_added"),
//                Arrays.asList("Michael Brown", new java.util.Date()),
//                "author_id", 31);
//
//        db.closeConnection();
//        System.out.println("Recs updated " + recsUpdated);

        //create
//        int recsAdded = db.createRecord("author",
//                Arrays.asList("author_name","date_added"),
//                Arrays.asList("Star Shine","2010-02-11"));
//
//        db.closeConnection();
//        System.out.println("Recs created " + recsAdded);
        //delete
        //int recsDeleted = db.deleteRecordById("author", "author_id", 25);
        //System.out.println("Number of records deleted: " + recsDeleted);
        //retrieve
        //List<Map<String, Object>> list = db.getAllRecords("CUSTOMER", 0);
        //for (Map<String, Object> rec : list) {
        //  System.out.println(rec);
        //  db.closeConnection();
    }


