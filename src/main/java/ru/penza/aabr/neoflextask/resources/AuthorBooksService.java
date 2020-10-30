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
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import ru.penza.aabr.neoflextask.ejb.AuthorBooksEJB;
import ru.penza.aabr.neoflextask.entities.AuthorBooks;
import ru.penza.aabr.neoflextask.other.CriteriaApi;

/**
 *
 * @author mkpte
 * @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
 * @Consumes(MediaType.APPLICATION_JSON)
 * @Produces(MediaType.APPLICATION_XML)
 */
@Path("abservice")
public class AuthorBooksService {
    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    AuthorBooksEJB abejb;
    @Inject
    CriteriaApi api;
    @Context
    private UriInfo uriInfo;
    
    private List<AuthorBooks> matchingList;
    
    @GET
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<AuthorBooks> findAll(){
        //api.findAll();
        //return api.getMatchingList();
        matchingList = abejb.findAll();
        return matchingList;
    }
    
    @GET
    @Path("authorbooks")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<AuthorBooks> findAuthorBooks(@MatrixParam("opt") int opt,@MatrixParam("sp") String sp){
        api.setSp(sp);
        api.setOpt(opt);
        api.findAuthorBooks();
        return api.getMatchingList();
    }
    @GET
    @Path("authorbooks2/{opt}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<AuthorBooks> findAuthorBooks2(@PathParam("opt") int opt){
        //api.setSp("Marx,Engels");
        api.setOpt(opt);
        api.findAuthorBooks();
        return api.getMatchingList();
    }
    @GET
    @Path("authorbooks3")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<AuthorBooks> findAuthorBooks3(@MatrixParam("opt") int opt,
            @MatrixParam("sp") String sp,
            @MatrixParam("title") String title,
            @MatrixParam("bg") int bg,@MatrixParam("en") int en){
        //api.setOpt(14);
        //api.setSp("Marx,Engels");
        //api.setPublish(1959);
        //api.setPublishEnd(2021);
        //api.setTitle("Book");

        
        api.setSp(sp);
        api.setTitle(title);
        api.setPublish(bg);
        api.setPublishEnd(en);
        
        api.setOpt(opt);
        
        api.findAuthorBooks();
        return api.getMatchingList();
    }
    /*
    @GET
    public List<AuthorBooks> findTitle(@QueryParam("title") String title){
        api.findTitle(title);
        return api.getMatchingList();
    }*/
    
    @GET
    @Path("publish/{publish}")
    @Produces(MediaType.APPLICATION_XML)
    public List<AuthorBooks> findLimit(@PathParam("publish") int pub){
        api.findLimit(pub);
        return api.getMatchingList();
    }
    
    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_XML)
    public List<AuthorBooks> findDiapazon(@MatrixParam("bg") int bg, @MatrixParam("en") int en){
        api.findDiapazon(bg, en);
        return api.getMatchingList();
    }
    
    @GET
    @Path("title/{title}")
    @Produces(MediaType.APPLICATION_XML)
    public List<AuthorBooks> findTitle(@PathParam("title") String str){
        api.findTitle(str);
        return api.getMatchingList();
    }
    
    @GET
    @Path("json")
    public Response getBooks(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AuthorBooks> criteriaQuery = criteriaBuilder.createQuery(AuthorBooks.class);
        Root<AuthorBooks> root = criteriaQuery.from(AuthorBooks.class);
        criteriaQuery.select(root);
        Query query = entityManager.createQuery(criteriaQuery);
        matchingList = query.getResultList();
        return Response.ok(matchingList).build();
    }
}
