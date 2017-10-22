/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.lgt.bookwebapp.model;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author Leslee
 */
public class AuthorDao implements IAuthorDao {

    private String driverClass;
    private String url;
    private String userName;
    private String password;
    private DataAccess db;
    private final String AUTHOR_TBL = "author";
    private final String AUTHOR_PK = "author_id";
  

    public AuthorDao(String driverClass, String url,
            String userName, String password,
            DataAccess db) {

        setDriverClass(driverClass);
        setUrl(url);
        
        setUserName(userName);
        setPassword(password);
        setDb(db);
    }
    
    @Override
    public final Author findAuthorById(Object authorId) throws ClassNotFoundException, SQLException{
        db.openConnection(driverClass, url, userName, password);
        
        List<Map<String, Object>> rawData = db.findRecordById(AUTHOR_TBL, AUTHOR_PK, authorId);

        Author author = null;
        for (Map<String, Object> rec : rawData) {
            author = new Author();

            //data validation
            Object objRecId = rec.get("author_id");
            Integer recId = objRecId == null
                    ? 0 : Integer.parseInt(objRecId.toString());

            //set "into" the Author obj
            author.setAuthorId(recId);

            Object objName = rec.get("author_name");
            String authorName = objName == null ? "" : objName.toString();
            author.setAuthorName(authorName);

            Object objRecAdded = rec.get("date_added");
            Date recAdded = objRecAdded == null ? null : (Date) objRecAdded;
            author.setDateAdded(recAdded);
        }
        db.closeConnection();
        return author;
        
}

    @Override
    public final int removeAuthorById(Integer id) throws ClassNotFoundException, SQLException {
        if (id == null || id < 1) {
            throw new IllegalArgumentException("Id must be a whole number greater than zero.");
        }

        db.openConnection(driverClass, url, userName, password);

        int recsDeleted = db.deleteRecordById(AUTHOR_TBL, AUTHOR_PK, id);

        db.closeConnection();

        return recsDeleted;
    }
    
    @Override
     public final int addAuthor(List<String> colName, List<Object> colValues) 
             throws ClassNotFoundException, SQLException {
       db.openConnection(driverClass, url, userName, password);
       String tableName = "author";
       int addAuthorResult = db.createRecord(tableName, colName, colValues);
            
       db.closeConnection();
       return addAuthorResult; 
      
    }
     
    @Override
     public final int updateAuthor(List<String> colNames, List<Object> colValues, String pkField)
             throws ClassNotFoundException, SQLException{
         db.openConnection(driverClass, url, userName, password);
         
         int updateResult = db.updateRecord(AUTHOR_TBL, colNames, colValues, AUTHOR_PK, pkField);
         
         db.closeConnection();
         return updateResult;
         
     }

    @Override
    public final List<Author> getListOfAuthors()
            throws SQLException, ClassNotFoundException {

        db.openConnection(driverClass, url, userName, password);

        List<Author> list = new Vector<>();
        List<Map<String, Object>> rawData
                = db.getAllRecords(AUTHOR_TBL, 0);

        Author author = null;

        for (Map<String, Object> rec : rawData) {
            author = new Author();

            Object objRecId = rec.get(AUTHOR_PK);
            Integer recId = objRecId == null
                    ? 0 : Integer.parseInt(objRecId.toString());
            author.setAuthorId(recId);

            Object objName = rec.get("author_name");
            String authorName = objName == null ? "" : objName.toString();
            author.setAuthorName(authorName);

            Object objRecAdded = rec.get("date_added");
            Date recAdded = objRecAdded == null ? null : (Date) objRecAdded;
            author.setDateAdded(recAdded);

            list.add(author);

        }
        db.closeConnection();

        return list;
    }

    public DataAccess getDb() {
        return db;
    }

    public void setDb(DataAccess db) {
        this.db = db;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        AuthorDao dao = new AuthorDao(
//                "com.mysql.jdbc.Driver",
//                "jdbc:mysql://localhost:3306/book",
//                "root", "admin",
//                new MySqlDataAccess()
//        );
//        
//        int updateAuth = dao.updateAuthor(
//                Arrays.asList("author_name","date_added"),
//                Arrays.asList("Jane Smaine", new java.util.Date()), "6");

//        int addAuth = dao.addAuthor("author", 
//                Arrays.asList("author_name", "date_added"),
//                Arrays.asList("George Orwell", new java.util.Date()));
        //int recsDeleted = dao.removeAuthorById(25);

//        List<Author> list = dao.getListOfAuthors();
//        for (Author a : list) {
//            System.out.println(a.getAuthorId() + ", " + a.getAuthorName() + ", " + a.getDateAdded() + "\n");
//        }
    }

 


