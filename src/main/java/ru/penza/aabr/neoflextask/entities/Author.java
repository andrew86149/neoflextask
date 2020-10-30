/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.penza.aabr.neoflextask.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author mkpte
 */
@Entity
@Table(name = "AUTHORS")
@NamedQueries({
    @NamedQuery(name = "findAllAuthors", query = "SELECT e FROM Author e"),
    @NamedQuery(name = "findByAuthorId", query = "SELECT e FROM Author e WHERE e.authorId = :authorId"),
    @NamedQuery(name = "findByName", query = "SELECT e FROM Author e WHERE e.lastName = :name")})
public class Author implements Serializable {
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "AUTHOR_ID")
    private Long authorId;
    @Size(max = 55)
    @Column(name = "LAST_NAME")
    private String lastName;
    @Size(max = 255)
    @Column(name = "BIOGR")
    private String biogr;
    @ManyToMany
    @JoinTable(name = "AUTHOR_BOOKS", 
      joinColumns = @JoinColumn(name = "AUTHOR_ID", 
          referencedColumnName = "AUTHOR_ID"), 
          inverseJoinColumns = @JoinColumn(name = "BOOK_ID", 
              referencedColumnName = "BOOK_ID"))
    private Collection<Book> books;

    public Author() {
    }

    public Author(Long authorId, String lastName) {
        this.authorId = authorId;
        this.lastName = lastName;
    }

    public Author(Long authorId, String lastName, Collection<Book> books) {
        this.authorId = authorId;
        this.lastName = lastName;
        this.books = books;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBiogr() {
        return biogr;
    }

    public void setBiogr(String biogr) {
        this.biogr = biogr;
    }

    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Author{authorId=").append(authorId);
        sb.append(", lastName=").append(lastName);
        sb.append(", biogr=").append(biogr);
        sb.append('}');
        return sb.toString();
    }
}
