/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.penza.aabr.neoflextask.jsf;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import ru.penza.aabr.neoflextask.ejb.BookEJB;
import ru.penza.aabr.neoflextask.entities.Book;

/**
 *
 * @author mkpte
 */
@Named
@RequestScoped
public class BookController {
    @Inject
    private BookEJB bookEJB;
    
    private Book book = new Book();
    
    public String doCreateBook() {
        bookEJB.createBook(book);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Book created",
            "The book" + book.getTitle() + " has been created with id=" + book.getBookId()));
        return "newBook.xhtml";
    }

    public void doFindBookById() {
        book = bookEJB.findBookById(book.getBookId());
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
