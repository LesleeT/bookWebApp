/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.lgt.bookwebapp.model;

import java.sql.SQLException;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Leslee
 */
@Stateless
public class AuthorService extends AbstractFacade<Author> {

    @PersistenceContext(unitName = "book_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEm() {
        return em;
    }

    public AuthorService() {
        super(Author.class);
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


}
