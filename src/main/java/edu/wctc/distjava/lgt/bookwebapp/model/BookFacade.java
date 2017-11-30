/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.lgt.bookwebapp.model;

import java.sql.SQLException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Leslee
 */
@Stateless
public class BookFacade extends AbstractFacade<Book> {

    @PersistenceContext(unitName = "book_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEm() {
        return em;
    }

    public BookFacade() {
        super(Book.class);
    }

    //add or update a book
    public void addOrUpdateBook(String bookId, String title, String isbn, String authorId) {
        Book book = null;
        if (bookId == null || bookId.isEmpty()) {
            //must be new record
            book = new Book();
        } else {
            //must be updated record
            book = new Book(new Integer(bookId));
        }
        
        book.setTitle(title);
        book.setIsbn(isbn);
        Author author = getEm().find(Author.class, new Integer(authorId));
        book.setAuthor(author);
        
        getEm().merge(book);
    }
    
     public int removeBookById(String id)
        throws SQLException, ClassNotFoundException, NumberFormatException{
        if(id == null || Integer.parseInt(id) <= 0){
            throw new IllegalArgumentException("Id must be an integer greater than 0");
        }
        Integer value = Integer.parseInt(id);
        int recordsDeleted = 0;
        String jpql = "DELETE FROM Book b WHERE b.bookId = :id";
        Query q = getEm().createQuery(jpql);
        q.setParameter("id", value);
        recordsDeleted = q.executeUpdate();
        
        return recordsDeleted;
    }
    
    public void updateBook(String bookId, String title, String isbn, String authorId){
        Book book = getEm().find(Book.class, new Integer(bookId));
        book.setTitle(title);
        book.setIsbn(isbn);
        Author author = getEm().find(Author.class, new Integer(authorId));
        book.setAuthor(author);
        getEm().merge(book);
        
    }
    
    public void addBook(String title, String isbn, String authorId){
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);
        Author author = getEm().find(Author.class, new Integer(authorId));
        book.setAuthor(author);
        getEm().merge(book);
    }   

}
