/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.penza.aabr.neoflextask.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mkpte
 */
@Entity
@Table(name = "AUTHOR_BOOKS")
@IdClass(value = AuthorBooksPK.class)
@XmlRootElement
public class AuthorBooks implements Serializable {
    @Id
    @Column(name = "AUTHOR_ID")
    private Long authorId;
    @Id
    @Column(name = "BOOK_ID")
    private Long bookId;
    
    private String authorName;
    private String bookTitle;
    private Integer bookPublishing;

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Integer getBookPublishing() {
        return bookPublishing;
    }

    public void setBookPublishing(Integer bookPublishing) {
        this.bookPublishing = bookPublishing;
    }
}
