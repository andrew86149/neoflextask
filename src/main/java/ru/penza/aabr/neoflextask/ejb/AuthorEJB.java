/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.penza.aabr.neoflextask.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import ru.penza.aabr.neoflextask.entities.Author;

/**
 *
 * @author mkpte
 */
@Named
@Stateless
public class AuthorEJB {
    @Inject
    private EntityManager em;
    
    public Author createAuthor(Author author) {
        em.persist(author);
        return author;
    }
    
    public Author updateAuthor(Author author){
        Author atr = em.merge(author);
        return atr;
    }

    public List<Author> findAllAuthors() {
        return em.createNamedQuery("findAllAuthors", Author.class).getResultList();
    }

    public Author findAuthorById(Long id) {
        return em.find(Author.class, id);
    }
}
