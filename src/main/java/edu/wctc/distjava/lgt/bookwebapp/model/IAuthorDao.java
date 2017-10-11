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

    List<Author> getListOfAuthors() throws SQLException, ClassNotFoundException;

    public abstract int removeAuthorById(Integer id) throws ClassNotFoundException, SQLException;

}
