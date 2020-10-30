/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.penza.aabr.neoflextask.ejb;

import java.util.ArrayList;
import java.util.Collection;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import ru.penza.aabr.neoflextask.entities.Author;
import ru.penza.aabr.neoflextask.entities.Book;

/**
 *
 * @author mkpte
 */
@Singleton
@Startup
public class DBPopulator {
    @Inject
    private BookEJB bookEJB;
    
    @Inject
    private AuthorEJB authorEJB;
    
    //private Logger logger = Logger.getLogger("org.agoncal.book.javaee7");
    
    @PostConstruct
    private void createDummyData(){
        bookEJB.createBook(new Book("The Hitchhiker's Guide to the Galaxy", 23.99f, "Science fiction comedy book", 354, Boolean.TRUE,1999));
        bookEJB.createBook(new Book("Harry Potter and the Goblet of Fire", 19.79f, "Science fiction (Book 4)", 734, Boolean.TRUE,1998));
        bookEJB.createBook(new Book("Java EE 6 with GlassFish 3", 31.49f, "Just fantastic", 354, Boolean.TRUE,2000));
        bookEJB.createBook(new Book("The Elements of Style", 6.64f, "A masterpiece in the art of clear and concise writing.", 105, Boolean.FALSE,2001));
        bookEJB.createBook(new Book("Harry Potter And The Order Of The Phoenix", 9.35f, "Science fiction (Book 1)", 870, Boolean.TRUE,2007));
        bookEJB.createBook(new Book("The Difference Between God and Larry Ellison", 11.99f, "God Doesn't Think He's Larry Ellison", 420, Boolean.FALSE,2008));
        bookEJB.createBook(new Book("The Children of Hurin", 17.16f, "The first complete book by J.R.R. Tolkien in three decades", 313, Boolean.TRUE,2009));
        bookEJB.createBook(new Book("The Da Vinci Code", 17.79f, "Thriller", 454, Boolean.FALSE,2010));
        bookEJB.createBook(new Book("La carte et le territoire", 24.99f, "Prix Goncourt 2010", 428, Boolean.FALSE,2011));
        bookEJB.createBook(new Book("Une forme de vie", 18.99f, "Amelie Nothomb", 168, Boolean.FALSE,2012));
        bookEJB.createBook(new Book("To Kill a Mockingbird", 5.99f, "American classic", 281, Boolean.FALSE,2013));
        bookEJB.createBook(new Book("Fahrenheit 451", 7.99f, "Science fiction", 208, Boolean.FALSE,2014));
        bookEJB.createBook(new Book("Lolita", 14.99f, "Nabovok masterpiece", 368, Boolean.TRUE,2015));
        bookEJB.createBook(new Book("A Midsummer Night's Dream", 14.99f, "Shakespeare masterpiece", 288, Boolean.TRUE,2017));
        bookEJB.createBook(new Book("I bastioni del coraggio", 18.99f, "Anno domini 1548. Una grande storia d'amore, ...", 457, Boolean.FALSE,2018));
        bookEJB.createBook(new Book("Harjunpaa ja pahan pappi", 32.49f, "Finns read crime novels", 300, Boolean.FALSE,2016));
        bookEJB.createBook(new Book("El ingenioso hidalgo don Quijote de la Mancha", 13.99f, "Classical Don Quijote", 108, Boolean.TRUE,2019));
        bookEJB.createBook(new Book("The Lord of the Rings", 50.4f, "Science fiction comedy book", 1216, Boolean.TRUE,2020));
        bookEJB.createBook(new Book("Heidis Lehr- und Wanderjahre", 68f, "Inhalt: Die Heidi-Bucher erzahlen...", 160, Boolean.TRUE,2018));
        bookEJB.createBook(new Book("l Nome della Rosa", 34.99f, "Science fiction comedy book", 354, Boolean.FALSE,2019));        
        
        bookEJB.createBook(new Book("Евгений Онегин", 15.99f, "Знаменитая поэма", 68, Boolean.FALSE, 1998));
        bookEJB.createBook(new Book("Бородино", 14.99f, "Поэма о войне 1812 года", 78, Boolean.FALSE,2001));
        bookEJB.createBook(new Book("Война и мир", 34.99f, "Роман о войне 1812 года", 568, Boolean.TRUE,2002));
        
        Book book = new Book();
        book.setTitle("Капитанская дочка");// id=24
        book.setPublishing(1990);
        book.setDescription("Роман о временах восстания Пугачёва");
        book.setIllustrations(Boolean.FALSE);
        book.setNbOfPage(150);
        book.setPrice(500f);
        bookEJB.createBook(book);
        
        Collection<Book> books = new ArrayList<>();
        books.add(book);
        book = bookEJB.findBookById(21L);
        books.add(book);
        
        Author author = new Author();
        author.setAuthorId(1L);
        author.setLastName("Пушкин");
        author.setBiogr("Отечество нам Царское село");
        author.setBooks(books);
        authorEJB.createAuthor(author);
        
        books = new ArrayList<>();
        book = bookEJB.findBookById(22L);
        books.add(book);
        authorEJB.createAuthor(new Author(2L,"Лермонтов",books));
        books = new ArrayList<>();
        book = bookEJB.findBookById(23L);
        books.add(book);
        authorEJB.createAuthor(new Author(3L,"Толстой",books));
        
        books = new ArrayList<>();
        book = bookEJB.findBookById(2L);
        books.add(book);
        book = bookEJB.findBookById(5L);
        books.add(book);
        author = new Author(4L,"Joan Roulling",books);
        authorEJB.createAuthor(author);
        
        books = new ArrayList<>();
        book = bookEJB.findBookById(13L);
        books.add(book);
        authorEJB.createAuthor(new Author(5L,"Набоков",books));
        
        books = new ArrayList<>();
        book = bookEJB.findBookById(3L);
        books.add(book);
        authorEJB.createAuthor(new Author(6L,"Heffelfinger",books));
        
        books = new ArrayList<>();
        book = bookEJB.findBookById(17L);
        books.add(book);
        authorEJB.createAuthor(new Author(7L,"Servantes",books));
        
        books = new ArrayList<>();
        book = new Book();
        book.setTitle("First Book");
        book.setPrice(150f);
        book.setPublishing(1958);
        books.add(book);
        bookEJB.createBook(book);
        
        book = new Book();
        book.setTitle("Second Book");
        book.setPrice(50f);
        book.setPublishing(1960);
        books.add(book);
        bookEJB.createBook(book);
        
        book = new Book();
        book.setTitle("Third Book");
        book.setPrice(450f);
        book.setPublishing(1968);
        books.add(book);
        bookEJB.createBook(book);
        
        authorEJB.createAuthor(new Author(8L,"Smith",books));
        authorEJB.createAuthor(new Author(9L,"Marx",books));
        authorEJB.createAuthor(new Author(10L,"Engels",books));
        
        books = new ArrayList<>();
        book = bookEJB.findBookById(1L);
        books.add(book);
        authorEJB.createAuthor(new Author(11L,"Дуглас Адамс",books));
        
        books = new ArrayList<>();
        book = bookEJB.findBookById(4L);
        books.add(book);
        authorEJB.createAuthor(new Author(12L,"William Strunk",books));
        
        books = new ArrayList<>();
        book = bookEJB.findBookById(6L);
        books.add(book);
        authorEJB.createAuthor(new Author(13L,"Mike Wilson",books));
        
        books = new ArrayList<>();
        book = bookEJB.findBookById(7L);
        books.add(book);
        book = bookEJB.findBookById(18L);
        books.add(book);
        authorEJB.createAuthor(new Author(14L,"J. R. R. Tolkien",books));
        //logger.log(Level.INFO, "&&&&&&&&&&&&&& Inserted {0} Books", bookEJB.findAllBooks().size());
    }
}
