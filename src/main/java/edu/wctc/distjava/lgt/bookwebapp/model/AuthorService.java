/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.lgt.bookwebapp.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Leslee
 */
public class AuthorService {

    private IAuthorDao authorDao;

    public AuthorService(IAuthorDao authorDao) {
        setAuthorDao(authorDao);
    }

    public final int removeAuthorById(String id) throws ClassNotFoundException, SQLException, NumberFormatException {
        if (id == null) {
            throw new IllegalArgumentException("I.D. must be a whole number greater than zero.");
        }
        Integer value = Integer.parseInt(id);
        return authorDao.removeAuthorById(value);
    }

    public final int addAuthor(List<String> colName, List<Object> colValues)
            throws ClassNotFoundException, SQLException {
        return authorDao.addAuthor(colName, colValues);
    }

    public final List<Author> getAuthorList() throws SQLException, ClassNotFoundException {
        return authorDao.getListOfAuthors();
    }

    public final int updateAuthor(List<String> colNames, List<Object> colValues, String pkField)
            throws ClassNotFoundException, SQLException {
        return authorDao.updateAuthor(colNames, colValues, pkField);
    }

    public final Author findAuthorById(Object authorId)
            throws SQLException, ClassNotFoundException {
        if (authorId == null) {
            throw new IllegalArgumentException("You must enter a valid author Id");
        }
        return authorDao.findAuthorById(authorId);

    }

    public final IAuthorDao getAuthorDao() {
        return authorDao;
    }

    public void setAuthorDao(IAuthorDao authorDao) {
        this.authorDao = authorDao;
    }

//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        IAuthorDao dao = new AuthorDao(
//                "com.mysql.jdbc.Driver",
//                "jdbc:mysql://localhost:3306/book",
//                "root", "admin",
//                new MySqlDataAccess()
//        );
//        AuthorService authorService = new AuthorService(dao);
//
//        int recsDeleted = authorService.removeAuthorById("6");
//
//        List<Author> list = authorService.getAuthorList();
//        for (Author a : list) {
//            System.out.println(a.getAuthorId() + ", " + a.getAuthorName() + ", " + a.getDateAdded() + "\n");
//        }
//    }
}
