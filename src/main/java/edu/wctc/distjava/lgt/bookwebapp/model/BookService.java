/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.lgt.bookwebapp.model;

import edu.wctc.distjava.lgt.bookwebapp.repository.AuthorRepository;
import edu.wctc.distjava.lgt.bookwebapp.repository.BookRepository;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Leslee
 */
@Service//("customAlias") - but its not necessary, default is just fine
public class BookService{

 @Autowired//can also replace with @Inject-inject is a jave EE and is supported by Spring
    private BookRepository bRepo;
 @Autowired
 private AuthorRepository aRepo;
 
    public BookService() {

    }
    
    public void addNewBook(String title, String isbn, String authorId){
        Author a = aRepo.findOne(Integer.parseInt(authorId));//authorId may need to be changed to author
        Book book = new Book();
        book.setAuthor(a);
        book.setIsbn(isbn);
        book.setTitle(title);
        bRepo.save(book);
    }
    
      
   public List<Book> findAll(){
       return bRepo.findAll();
   }

   //dataaccessexception is a converter that throws meaningful messages - can add to every method
   public Book findById(String id) throws DataAccessException{
       return bRepo.findOne(Integer.parseInt(id));
   }
    public void removeBookById(String id) throws Exception {
        Integer value = Integer.parseInt(id);
        bRepo.delete(value);
        
    }

    public void updateBook(String id, String title, String isbn, String authorId) {
        Book b = findById(id);
        Author author = aRepo.findOne(Integer.parseInt(authorId));
        b.setTitle(title);
        b.setAuthor(author);
        b.setIsbn(isbn);
        bRepo.save(b);       
        
    }  
    
    
    
    
//
//    //add or update a book
//    public void addOrUpdateBook(String bookId, String title, String isbn, String authorId) {
//        Book book = null;
//        if (bookId == null || bookId.isEmpty()) {
//            //must be new record
//            book = new Book();
//        } else {
//            //must be updated record
//            book = new Book(new Integer(bookId));
//        }
//        
//        book.setTitle(title);
//        book.setIsbn(isbn);
//        Author author = getEm().find(Author.class, new Integer(authorId));
//        book.setAuthor(author);
//        
//        getEm().merge(book);
//    }
//    
//     public int removeBookById(String id)
//        throws SQLException, ClassNotFoundException, NumberFormatException{
//        if(id == null || Integer.parseInt(id) <= 0){
//            throw new IllegalArgumentException("Id must be an integer greater than 0");
//        }
//        Integer value = Integer.parseInt(id);
//        int recordsDeleted = 0;
//        String jpql = "DELETE FROM Book b WHERE b.bookId = :id";
//        Query q = getEm().createQuery(jpql);
//        q.setParameter("id", value);
//        recordsDeleted = q.executeUpdate();
//        
//        return recordsDeleted;
//    }
//    
//    public void updateBook(String bookId, String title, String isbn, String authorId){
//        Book book = getEm().find(Book.class, new Integer(bookId));
//        book.setTitle(title);
//        book.setIsbn(isbn);
//        Author author = getEm().find(Author.class, new Integer(authorId));
//        book.setAuthor(author);
//        getEm().merge(book);
//        
//    }
//    
//    public void addBook(String title, String isbn, String authorId){
//        Book book = new Book();
//        book.setTitle(title);
//        book.setIsbn(isbn);
//        Author author = getEm().find(Author.class, new Integer(authorId));
//        book.setAuthor(author);
//        getEm().merge(book);
//    }   

}
