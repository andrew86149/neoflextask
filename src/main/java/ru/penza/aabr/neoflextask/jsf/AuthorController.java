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
import ru.penza.aabr.neoflextask.ejb.AuthorEJB;
import ru.penza.aabr.neoflextask.entities.Author;

/**
 *
 * @author mkpte
 */
@Named
@RequestScoped
public class AuthorController {
    @Inject
    private AuthorEJB authorEJB;
    
    private Author author = new Author();
    
    public String doCreateAuthor() {
        authorEJB.createAuthor(author);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Author created",
            "The author" + author.getLastName() + " has been created with id=" + author.getAuthorId()));
        return "newAuthor.xhtml";
    }

    public void doFindAuthorById() {
        author = authorEJB.findAuthorById(author.getAuthorId());
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
