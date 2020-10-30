/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.penza.aabr.neoflextask.jsf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import ru.penza.aabr.neoflextask.ejb.AuthorBooksEJB;
import ru.penza.aabr.neoflextask.entities.AuthorBooks;

/**
 *
 * @author mkpte
 */
@Named
@RequestScoped
public class AuthorBooksController {
    @Inject
    private AuthorBooksEJB abEJB;
    
    private List<AuthorBooks> abList;
    private int publish = 2000;
    private int publishEnd = 2010;
    private String title = "tter";
    private String sp = "Smith,Servantes";
    private List<String> sps = new ArrayList<>();
    private Boolean cbx1,cbx2,cbx3,cbx4,cbx5;
    private int opt;

    public AuthorBooksController() {
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
            return "viewAuthorBooks";
        }
        if(cbx2) 
        { 
            //findAuthor();
            opt += 8;
        }
        if(cbx3) 
        { 
            //findTitle();
            opt += 4;
        }
        if(cbx4) 
        { 
            //findDiapazon();
            opt += 2;
        }
        if(cbx5) 
        { 
            findLimit();
            opt = 0;
            return "viewAuthorBooks";
        }
        
        switch(opt){
            case 2:
                findDiapazon();
                break;
            case 4:
                findTitle();
                break;
            case 8:
                findAuthor();
                break;
            case 12:
                //find12();
                findAll();
                break;
            case 14:
                //find14();
                findAll();
                break;
            case 6:
                find6();
                break;
            case 10:
                //find10();
                findAll();
                break;
        }
    
        return "viewAuthorBooks";
    }
    
    public void findAll(){
        abList = abEJB.findAll();
    }
    
    public void find6(){ abList = abEJB.findBy240(publish, publishEnd, title, sps); }
    public void find14(){ abList = abEJB.findBy248(publish, publishEnd, title, sps); }
    public void find12(){ abList = abEJB.findBy048(publish, publishEnd, title, sps); }
    public void find10(){ abList = abEJB.findBy208(publish, publishEnd, title, sps); }
    
    public void findDiapazon(){
        abList = abEJB.findDiapazon(publish,publishEnd);
    }
    
    public void findLimit(){
        abList = abEJB.findLimit(publish);
    }
    
    public void findTitle(){
        abList = abEJB.findByTitle(title);
    }
    
    public void findAuthor(){
        String[] msp = sp.split(",");
        sps = Arrays.asList(msp);
        abList = abEJB.findByAuthor(sps);
    }

    public List<AuthorBooks> getAbList() {
        return abList;
    }

    public void setAbList(List<AuthorBooks> abList) {
        this.abList = abList;
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
