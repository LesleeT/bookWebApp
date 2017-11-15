/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.lgt.bookwebapp.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Leslee
 */
@Stateless
public class AuthorServiceOld implements Serializable {

    private final String AUTHOR_TBL = "author";
    private final String AUTHOR_PK = "author_id";

    @PersistenceContext(unitName = "book_PU")
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public AuthorServiceOld() {
    }

    // Updated for JPA
    public List<Author> getAuthorList() throws Exception {

        String jpql = "select a from Author a";
        TypedQuery<Author> q = getEm().createQuery(jpql, Author.class);
        q.setMaxResults(500); // optional

        return q.getResultList();
    }

    public int removeAuthorById(String id) throws Exception {
        String jpql = "delete from Author a where a.authorId = :id";
        Query q = getEm().createQuery(jpql);
        q.setParameter("id", new Integer(id));
        return q.executeUpdate();
    }

    public void addAuthor(String name)
            throws Exception {
        Author a = new Author();
        a.setAuthorName(name);
        a.setDateAdded(new Date());
        getEm().merge(a);
    }

    public void updateAuthor(String id, String name) {
        Author a = getEm().find(Author.class, new Integer(id));
        a.setAuthorName(name);
        getEm().merge(a);
    }

    public final Author findAuthorById(Object authorId)
            throws SQLException, ClassNotFoundException {
        if (authorId == null) {
            throw new IllegalArgumentException("You must enter a valid author Id");
        }
        int id = Integer.parseInt(authorId.toString());
        Author a = getEm().find(Author.class, id);
        return a;

    }

    //    //updated method to reflect changes 
//    public final List<Author> getAuthorList()throws Exception{       
//        //List<Author> authorList = new ArrayList<>(); not necessary
//        String jpql = "select a from Author a";
//        TypedQuery<Author> q = getEm().createQuery(jpql, Author.class);
//        q.setMaxResults(500);//optional
//        //authorList = q.getResultList(); not necessary but still works 
//        return q.getResultList();
//    }
//    public final int updateAuthor(List<String> colNames, List<Object> colValues, String pkField)
//            throws ClassNotFoundException, SQLException {
//        //fix this later
//        //List<String> cols = new ArrayList();
//        //cols.add("author_name");
//        //cols.add("date_added");
//        //change cols back to colNames
//        return 0;
//    }
    //another way to delete author
    //wont use this because typically you wont be passing an author object from the controller
//    public void removeAuthor(Author author){
//        getEm().remove(getEm().merge(author));//merge it with what's in cache, once merged then you can remove it      
//    }
//    
}
