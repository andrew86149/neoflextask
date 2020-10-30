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
import ru.penza.aabr.neoflextask.entities.Book;

/**
 *
 * @author mkpte
 */
@Named
@Stateless
public class BookEJB {
    @Inject
    private EntityManager em;
    
    public Book createBook(Book book) {
        em.persist(book);
        return book;
    }
    
    public Book updateBook(Book book){
        Book bk = em.merge(book);
        return bk;
    }

    public List<Book> findAllBooks() {
        return em.createNamedQuery("findAllBooks", Book.class).getResultList();
    }

    public Book findBookById(Long id) {
        return em.find(Book.class, id);
    }
}
