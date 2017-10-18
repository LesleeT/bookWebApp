/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.lgt.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Leslee
 */
public interface IAuthorDao {

    public abstract List<Author> getListOfAuthors() throws SQLException, ClassNotFoundException;

    public abstract int removeAuthorById(Integer id) throws ClassNotFoundException, SQLException;

    public abstract int updateAuthor(List<String> colNames, List<Object> colValues, String pkField) throws ClassNotFoundException, SQLException;

    public abstract int addAuthor(List<String> colName, List<Object> colValues)
            throws ClassNotFoundException, SQLException;

    public abstract Author findAuthorById(Object authorId) throws ClassNotFoundException, SQLException;

}
