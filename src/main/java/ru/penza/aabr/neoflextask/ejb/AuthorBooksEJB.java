/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.penza.aabr.neoflextask.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import ru.penza.aabr.neoflextask.entities.Author;
import ru.penza.aabr.neoflextask.entities.AuthorBooks;
import ru.penza.aabr.neoflextask.entities.Book;

/**
 *
 * @author mkpte
 */
@Stateless
public class AuthorBooksEJB {
    @Inject
    private EntityManager em;
    
    private List<AuthorBooks> authorBooks;
    private AuthorBooks authorBook;
    private Author author;
    private Book book;
    // 1    0001
    public List<AuthorBooks> findAll(){
        authorBooks = em.createQuery("SELECT e FROM AuthorBooks e ORDER BY e.bookId",AuthorBooks.class).getResultList();
        for(AuthorBooks ab : authorBooks){
            author = em.find(Author.class, ab.getAuthorId());
            book = em.find(Book.class, ab.getBookId());
            ab.setAuthorName(author.getLastName());
            ab.setBookTitle(book.getTitle());
            ab.setBookPublishing(book.getPublishing());
        }
        return authorBooks;
    }
    // 2    0010
    public List<AuthorBooks> findDiapazon(int b,int e){
        authorBooks = em.createQuery(
                "SELECT e FROM AuthorBooks e WHERE e.bookPublishing > :bg AND e.bookPublishing < :en",
                AuthorBooks.class)
                .setParameter("bg", b)
                .setParameter("en", e)
                .getResultList();
        return authorBooks;
    }
    
    public List<AuthorBooks> findLimit(int publish){
        authorBooks = em.createQuery(
                "SELECT e FROM AuthorBooks e WHERE e.bookPublishing < :param",
                AuthorBooks.class).setParameter("param", publish)
                .getResultList();
        return authorBooks;
    }
    // 4    0100
    public List<AuthorBooks> findByTitle(String title){
        title = "%" + title + "%";
        authorBooks = em.createQuery("SELECT e FROM AuthorBooks e WHERE e.bookTitle LIKE :param",
                AuthorBooks.class)
                .setParameter("param", title)
                .getResultList();
        return authorBooks;
    }
    // 8    1000
    public List<AuthorBooks> findByAuthor(List<String> sp){
        authorBooks = em.createQuery("SELECT e FROM AuthorBooks e WHERE e.authorName IN :spisok",
                AuthorBooks.class).setParameter("spisok", sp)
                .getResultList();
        return authorBooks;
    }
    
    // 2+4+8    1110    14
    public List<AuthorBooks> findBy248(int b,int e,String title,List<String> sp){
        authorBooks = em.createQuery(
                "SELECT e FROM AuthorBooks e WHERE (e.bookPublishing > :bg) AND (e.bookPublishing < :en) AND (e.bookTitle LIKE :param) AND (e.authorName IN :spisok)",
                AuthorBooks.class)
                .setParameter("bg", b)
                .setParameter("en", e)
                .setParameter("param", title)
                .setParameter("spisok", sp)
                .getResultList();
        return authorBooks;
    }
    
    // 2+4+0    0110    6
    public List<AuthorBooks> findBy240(int b,int e,String title,List<String> sp){
        title = "%" + title + "%";
        authorBooks = em.createQuery(
                "SELECT e FROM AuthorBooks e WHERE (e.bookPublishing > :bg) AND (e.bookPublishing < :en) AND (e.bookTitle LIKE :param)",
                AuthorBooks.class)
                .setParameter("bg", b)
                .setParameter("en", e)
                .setParameter("param", title)
                .getResultList();
        return authorBooks;
    }
    
    // 2+0+8    1010    10
    public List<AuthorBooks> findBy208(int b,int e,String title,List<String> sp){
        authorBooks = em.createQuery(
                "SELECT e FROM AuthorBooks e WHERE (e.bookPublishing > :bg) AND (e.bookPublishing < :en) AND (e.authorName IN :spisok)",
                AuthorBooks.class)
                .setParameter("bg", b)
                .setParameter("en", e)
                .setParameter("spisok", sp)
                .getResultList();
        return authorBooks;
    }
    
    // 0+4+8    1100    12
    public List<AuthorBooks> findBy048(int b,int e,String title,List<String> sp){
        //title = "%" + title + "%";
        authorBooks = em.createQuery(
                "SELECT e FROM AuthorBooks e WHERE e.authorName IN :spisok",
                AuthorBooks.class)
                .setParameter("spisok", sp)
                .getResultList();
        return authorBooks;
    }
}
