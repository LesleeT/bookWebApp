/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.lgt.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Leslee
 */
public interface DataAccess {

    public abstract void closeConnection() throws SQLException;

    /**
     * Returns records from a table. Requires and open connection.
     *
     * @param tableName
     * @param maxRecords
     * @return
     * @throws SQLException
     */
    public abstract List<Map<String, Object>> getAllRecords(String tableName, int maxRecords) throws SQLException, ClassNotFoundException;

    public abstract int deleteRecordById(String tableName, String pkColName, Object pkValue)
            throws ClassNotFoundException, SQLException;

    public abstract void openConnection(String driverClass,
            String url, String userName, String password)
            throws ClassNotFoundException, SQLException;

    public abstract int createRecord(String tableName, List<String> colNames, List<Object> colValues)
            throws ClassNotFoundException, SQLException;

    public abstract int updateRecord(String tableName, List<String> colNames, List<Object> colValues, String pkField, Object pkValue)
            throws ClassNotFoundException, SQLException;

    public abstract List<Map<String, Object>> findRecordById(String tableName,
            String pkColName, Object pkValue)
            throws SQLException;

}
