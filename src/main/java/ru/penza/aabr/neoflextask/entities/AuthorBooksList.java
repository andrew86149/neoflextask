/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.penza.aabr.neoflextask.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author mkpte
 */
@XmlRootElement
@XmlSeeAlso(AuthorBooks.class)
public class AuthorBooksList extends ArrayList<AuthorBooks>{

    public AuthorBooksList() {
        super();
    }

    public AuthorBooksList(Collection<? extends AuthorBooks> c) {
        super(c);
    }
    
    public List<AuthorBooks> getAuthorBooks(){
        return this;
    }
    
    public void setAuthorBooks(List<AuthorBooks> abooks){
        this.addAll(abooks);
    }
}
