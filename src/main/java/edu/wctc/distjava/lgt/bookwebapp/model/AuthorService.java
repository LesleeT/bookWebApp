/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.lgt.bookwebapp.model;

import edu.wctc.distjava.lgt.bookwebapp.repository.AuthorRepository;
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
@Service
public class AuthorService {

@Autowired
private AuthorRepository aRepo;
    public AuthorService() {
       
    }
    
   public List<Author> findAll(){
       return aRepo.findAll();
   }

   //dataaccessexception is a converter that throws meaningful messages - can add to every method
   public Author findById(String id) throws DataAccessException{
       return aRepo.findOne(Integer.parseInt(id));
   }
    public void removeAuthorById(String id) throws Exception {
        Integer value = Integer.parseInt(id);
        aRepo.delete(value);
        
    }

    public void addAuthor(String name)
            throws Exception {
        Author a = new Author();
        Date dateAdded = new Date();
        a.setAuthorName(name);
        a.setDateAdded(dateAdded);
        a.setBookSet(new HashSet());//have an empty set so null isn't returned 
        
        aRepo.save(a);
    }

    public void updateAuthor(String id, String name) {
        Author a = findById(id);
        a.setAuthorName(name);
        aRepo.save(a);       
    }


}
