/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.penza.aabr.neoflextask.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import ru.penza.aabr.neoflextask.entities.AuthorBooks;
import ru.penza.aabr.neoflextask.entities.AuthorBooksPK;

/**
 *
 * @author mkpte
 */
@Named
@RequestScoped
public class CriteriaApi {
    @PersistenceContext
    private EntityManager entityManager;
    
    @Resource
    private UserTransaction userTransaction;
    
    private List<AuthorBooks> matchingList;
    
    private int publish = 2000;
    private int publishEnd = 2010;
    private String title = "tter";
    private String sp = "Smith,Servantes";
    private List<String> sps = new ArrayList<>();
    private Boolean cbx1,cbx2,cbx3,cbx4,cbx5;
    private int opt;
    
    private int updatedRows;
    private int deletedRows;

    public CriteriaApi() {
        cbx1 = true;
        cbx2 = false;
        cbx3 = false;
        cbx4 = false;
        cbx5 = false;
        opt = 0;
    }
    
    public String doQuery(){
        if(cbx1) 
        { 
            findAll();
            opt = 0;
            return "viewAuthorBooks_1";
        }
        if(cbx2) 
        { 
            //findAuthor();
            opt += 8;
        }
        if(cbx3) 
        { 
            //findTitle(title);
            opt += 4;
        }
        if(cbx4) 
        { 
            //findDiapazon(publish,publishEnd);
            opt += 2;
        }
        if(cbx5) 
        { 
            findLimit(publish);
            opt = 0;
            return "viewAuthorBooks_1";
        }
        findAuthorBooks();
    
        return "viewAuthorBooks_1";
    }
    
    public void findAuthorBooks(){
        title = "%" + title + "%";
        String[] msp = sp.split(",");
        sps = Arrays.asList(msp);
        int b = publish;
        int e = publishEnd;
        
        String retVal = "confirmation";
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<AuthorBooks> criteriaQuery = criteriaBuilder.createQuery(AuthorBooks.class);
            Root<AuthorBooks> root = criteriaQuery.from(AuthorBooks.class);

            Metamodel metamodel = entityManager.getMetamodel();
            EntityType<AuthorBooks> abEntityType = metamodel.entity(AuthorBooks.class);
            
            SingularAttribute<AuthorBooks, String> abAttribute
                    = abEntityType.getDeclaredSingularAttribute("bookTitle",String.class);
            Path<String> path = root.get(abAttribute);
            Predicate predicate = criteriaBuilder.like(path, title);
            
            SingularAttribute<AuthorBooks, Integer> abAttribute2
                    = abEntityType.getDeclaredSingularAttribute("bookPublishing",Integer.class);
            Path<Integer> path2 = root.get(abAttribute2);
            Predicate predicate1 = criteriaBuilder.greaterThan(path2, b);
            Predicate predicate2 = criteriaBuilder.lessThan(path2, e);
            
            SingularAttribute<AuthorBooks, String> abAttribute3
                    = abEntityType.getDeclaredSingularAttribute("authorName",String.class);
            Path<String> path3 = root.get(abAttribute3);
            
            Predicate predicate3 = root.get("authorName").in(sps);
            
            switch(opt){
            case 2:
                //findDiapazon();
                predicate = criteriaBuilder.and(predicate1,predicate2);
                break;
            case 4:
                //findTitle();
                break;
            case 8:
                //findAuthor();
                predicate = predicate3;
                break;
            case 12:
                //find12();
                predicate = criteriaBuilder.and(predicate,predicate3);
                break;
            case 14:
                //find14();
                predicate = criteriaBuilder.and(predicate,predicate1,predicate2,predicate3);
                break;
            case 6:
                predicate = criteriaBuilder.and(predicate1,predicate2,predicate);
                break;
            case 10:
                //find10();
                predicate = criteriaBuilder.and(predicate1,predicate2,predicate3);
                break;
            }
            
            criteriaQuery = criteriaQuery.where(predicate);

            TypedQuery typedQuery = entityManager.createQuery(criteriaQuery);
            matchingList = typedQuery.getResultList();
        } catch(Exception ex){
            retVal = "errors";
            Logger.getLogger(CriteriaApi.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return retVal;
        //return retVal;
    }
    
    public void findAll(){
        String retVal = "confirmation";
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<AuthorBooks> criteriaQuery = criteriaBuilder.createQuery(AuthorBooks.class);
            Root<AuthorBooks> root = criteriaQuery.from(AuthorBooks.class);
            
            criteriaQuery.select(root);
            
            Query query = entityManager.createQuery(criteriaQuery);
            matchingList = query.getResultList();
        }catch(Exception ex){
            retVal = "errors";
            Logger.getLogger(CriteriaApi.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return retVal;
        //return retVal;
    }
    
    public void findLimit(int b){
        String retVal = "confirmation";
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<AuthorBooks> criteriaQuery = criteriaBuilder.createQuery(AuthorBooks.class);
            Root<AuthorBooks> root = criteriaQuery.from(AuthorBooks.class);
            
            criteriaQuery.select(root).where(criteriaBuilder.lessThan(root.get("bookPublishing").as(Integer.class), b));
            //criteriaQuery.select(root).where(criteriaBuilder.greaterThan(root.get(AuthorBooks_.bookPublishing), b));
            
            Query query = entityManager.createQuery(criteriaQuery);
            matchingList = query.getResultList();
        }catch(Exception ex){
            retVal = "errors";
            Logger.getLogger(CriteriaApi.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return retVal;
        //return retVal;
    }
    
    public void findDiapazon(int b,int e){
        String retVal = "confirmation";
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<AuthorBooks> criteriaQuery = criteriaBuilder.createQuery(AuthorBooks.class);
            Root<AuthorBooks> root = criteriaQuery.from(AuthorBooks.class);
            
            Metamodel metamodel = entityManager.getMetamodel();
            EntityType<AuthorBooks> abEntityType = metamodel.entity(AuthorBooks.class);
            
            SingularAttribute<AuthorBooks, Integer> abAttribute
                    = abEntityType.getDeclaredSingularAttribute("bookPublishing",Integer.class);
            Path<Integer> path = root.get(abAttribute);
            Predicate predicate = criteriaBuilder.greaterThan(path, b);
            Predicate predicate2 = criteriaBuilder.lessThan(path, e);
            Predicate predicate3 = criteriaBuilder.and(predicate,predicate2);
            criteriaQuery = criteriaQuery.where(predicate3);

            TypedQuery query = entityManager.createQuery(criteriaQuery);
            matchingList = query.getResultList();
        }catch(Exception ex){
            retVal = "errors";
            Logger.getLogger(CriteriaApi.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return retVal;
        //return retVal;
    }
    
    public void findTitle(String title){
        title = "%" + title + "%";
        String retVal = "confirmation";
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<AuthorBooks> criteriaQuery = criteriaBuilder.createQuery(AuthorBooks.class);
            Root<AuthorBooks> root = criteriaQuery.from(AuthorBooks.class);
            
            Metamodel metamodel = entityManager.getMetamodel();
            EntityType<AuthorBooks> abEntityType = metamodel.entity(AuthorBooks.class);
            
            SingularAttribute<AuthorBooks, String> abAttribute
                    = abEntityType.getDeclaredSingularAttribute("bookTitle",String.class);
            Path<String> path = root.get(abAttribute);
            
            Predicate predicate = criteriaBuilder.like(path, title);
            criteriaQuery = criteriaQuery.where(predicate);

            TypedQuery query = entityManager.createQuery(criteriaQuery);
            matchingList = query.getResultList();
        }catch(Exception ex){
            retVal = "errors";
            Logger.getLogger(CriteriaApi.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return retVal;
        //return retVal;
    }
    
    public void findAuthor(){
        String[] msp = sp.split(",");
        sps = Arrays.asList(msp);
        
        String retVal = "confirmation";
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<AuthorBooks> criteriaQuery = criteriaBuilder.createQuery(AuthorBooks.class);
            Root<AuthorBooks> root = criteriaQuery.from(AuthorBooks.class);
            
            Metamodel metamodel = entityManager.getMetamodel();
            EntityType<AuthorBooks> abEntityType = metamodel.entity(AuthorBooks.class);
            
            SingularAttribute<AuthorBooks, String> abAttribute
                    = abEntityType.getDeclaredSingularAttribute("authorName",String.class);
            Path<String> path = root.get(abAttribute);
            
            //Predicate predicate = criteriaBuilder.like(path, msp[0]);
            Predicate predicate = root.get("authorName").in(sps);
            criteriaQuery = criteriaQuery.where(predicate);
            //criteriaQuery.select(root).where(root.get("authorName").in(sps));

            TypedQuery query = entityManager.createQuery(criteriaQuery);
            matchingList = query.getResultList();
        }catch(Exception ex){
            retVal = "errors";
            Logger.getLogger(CriteriaApi.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return retVal;
        //return retVal;
    }
    
    public String updateData(){
        String retVal = "confirmation";
        try{
            userTransaction.begin();
            
            insertTempData();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaUpdate<AuthorBooks> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(AuthorBooks.class);
            Root<AuthorBooks> root = criteriaUpdate.from(AuthorBooks.class);
            criteriaUpdate.set("bookTitle", "New York");
            criteriaUpdate.where(criteriaBuilder.equal(root.get("bookTitle"), "New Yorc"));

            Query query = entityManager.createQuery(criteriaUpdate);

            updatedRows = query.executeUpdate();
            
            userTransaction.commit();
        } catch(IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException ex){
            retVal = "error";
            Logger.getLogger(CriteriaApi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return retVal;
    }
    
    private void insertTempData() throws NotSupportedException,
            SystemException, RollbackException, HeuristicMixedException,
            HeuristicRollbackException{
        AuthorBooks ab = entityManager.find(AuthorBooks.class, new AuthorBooksPK(1L,1L));

        entityManager.persist(ab);
    }
    
    public String deleteData(){
        String retVal = "confirmation";
        
        try {
            userTransaction.begin();
            
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaDelete<AuthorBooks> criteriaDelete = criteriaBuilder.createCriteriaDelete(AuthorBooks.class);
            Root<AuthorBooks> root = criteriaDelete.from(AuthorBooks.class);
            criteriaDelete.where(criteriaBuilder.or(criteriaBuilder.equal(
                    root.get("bookTitle"), "New York"),
                    criteriaBuilder.equal(root.get("bookTitle"), "New York")));

            Query query = entityManager.createQuery(criteriaDelete);

            deletedRows = query.executeUpdate();
            
            userTransaction.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException ex) {
            Logger.getLogger(CriteriaApi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return retVal;
    }

    public List<AuthorBooks> getMatchingList() {
        return matchingList;
    }

    public void setMatchingList(List<AuthorBooks> matchingList) {
        this.matchingList = matchingList;
    }

    public int getUpdatedRows() {
        return updatedRows;
    }

    public void setUpdatedRows(int updatedRows) {
        this.updatedRows = updatedRows;
    }

    public int getDeletedRows() {
        return deletedRows;
    }

    public void setDeletedRows(int deletedRows) {
        this.deletedRows = deletedRows;
    }

    public int getPublish() {
        return publish;
    }

    public void setPublish(int publish) {
        this.publish = publish;
    }

    public int getPublishEnd() {
        return publishEnd;
    }

    public void setPublishEnd(int publishEnd) {
        this.publishEnd = publishEnd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

    public List<String> getSps() {
        return sps;
    }

    public void setSps(List<String> sps) {
        this.sps = sps;
    }

    public Boolean getCbx1() {
        return cbx1;
    }

    public void setCbx1(Boolean cbx1) {
        this.cbx1 = cbx1;
    }

    public Boolean getCbx2() {
        return cbx2;
    }

    public void setCbx2(Boolean cbx2) {
        this.cbx2 = cbx2;
    }

    public Boolean getCbx3() {
        return cbx3;
    }

    public void setCbx3(Boolean cbx3) {
        this.cbx3 = cbx3;
    }

    public Boolean getCbx4() {
        return cbx4;
    }

    public void setCbx4(Boolean cbx4) {
        this.cbx4 = cbx4;
    }

    public Boolean getCbx5() {
        return cbx5;
    }

    public void setCbx5(Boolean cbx5) {
        this.cbx5 = cbx5;
    }

    public int getOpt() {
        return opt;
    }

    public void setOpt(int opt) {
        this.opt = opt;
    }
}
