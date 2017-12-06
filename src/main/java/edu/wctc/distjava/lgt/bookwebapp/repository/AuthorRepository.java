package edu.wctc.distjava.lgt.bookwebapp.repository;

import edu.wctc.distjava.lgt.bookwebapp.model.Author;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jlombardo
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>, Serializable {
//custom methods
//public abstract List<Author> findByAuthorName(@Param ("authorName") String authorName);  

    //a version of the above custom method but with authorname in the method name, without that Spring doesn't know what to search for
//@Query("SELECT a from Author where a.authorName = :authorName")
//public abstract List<Author> findByName(@Param ("authorName") String authorName); 
	// example of a projection query
    @Query("SELECT a.authorName FROM Author a")
    public Object[] findAllWithNameOnly();
}
