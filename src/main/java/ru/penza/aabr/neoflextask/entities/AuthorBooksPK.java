/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.penza.aabr.neoflextask.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author mkpte
 */
public class AuthorBooksPK implements Serializable{
    public Long authorId;
    public Long bookId;

    public AuthorBooksPK() {
    }

    public AuthorBooksPK(Long authorId, Long bookId) {
        this.authorId = authorId;
        this.bookId = bookId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.authorId);
        hash = 59 * hash + Objects.hashCode(this.bookId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AuthorBooksPK other = (AuthorBooksPK) obj;
        if (!Objects.equals(this.authorId, other.authorId)) {
            return false;
        }
        if (!Objects.equals(this.bookId, other.bookId)) {
            return false;
        }
        return true;
    }
}
