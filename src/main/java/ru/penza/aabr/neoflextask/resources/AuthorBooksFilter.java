/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.penza.aabr.neoflextask.resources;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import ru.penza.aabr.neoflextask.entities.AuthorBooks;
import ru.penza.aabr.neoflextask.other.CriteriaApi;

/**
 *
 * @author mkpte
 */
@Path("abfilter")
@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
public class AuthorBooksFilter {
    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    CriteriaApi api;
    
    @GET
    public List<AuthorBooks> findAuthorBooks(
            @QueryParam("opt") int opt,
            @QueryParam("sp") String sp,
            @QueryParam("title") String title,
            @QueryParam("bg") int bg,
            @QueryParam("en") int en){
        api.setOpt(opt);
        api.setSp(sp);
        api.setTitle(title);
        api.setPublish(bg);
        api.setPublishEnd(en);
        api.findAuthorBooks();
        
        return api.getMatchingList();
    }
}
