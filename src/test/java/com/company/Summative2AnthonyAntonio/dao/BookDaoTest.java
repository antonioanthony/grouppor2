package com.company.Summative2AnthonyAntonio.dao;

import com.company.Summative2AnthonyAntonio.dto.Author;
import com.company.Summative2AnthonyAntonio.dto.Book;
import com.company.Summative2AnthonyAntonio.dto.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookDaoTest {

    @Autowired
    public BookDao bookDao;
    @Autowired
    public AuthorDao authorDao;
    @Autowired
    public PublisherDao publisherDao;
    @Before
    public void setUp() throws Exception {
        // clean out the test db
        List<Author> aList = authorDao.getAllAuthors();
        for (Author a: aList){
            authorDao.deleteAuthor(a.getAuthorId());
        }
        List<Publisher> pList = publisherDao.getAllPublishers();
        for(Publisher p: pList){
            publisherDao.deletePublisher(p.getPublisherId());
        }
        List<Book> bList = bookDao.getAllBooks();
        for (Book b : bList) {
            bookDao.deleteBook(b.getBookId());
        }

    }
    @Test
    public void  addGetDeleteBook() {
        //have to set up publisher and author
        Publisher pub = new Publisher();
        pub.setName("Mark Jones");
        pub.setStreet("Abingdon Lane");
        pub.setCity("Norfolk");
        pub.setState("VA");
        pub.setPostalCode("23456");
        pub.setPhone("757-920-3492");
        pub.setEmail("mark.jones@gmail.com");

        pub =publisherDao.addPublisher(pub);



        Author auth = new Author();
        auth.setFirstName("Julia");
        auth.setLastNname("Jackson");
        auth.setStreet("WoodLane Drive");
        auth.setCity("Newport News");
        auth.setState("VA");
        auth.setPostalCode("23064");
        auth.setPhone("757-488-2941");
        auth.setEmail("julia.jackson@gmail.com");
        auth =authorDao.addAuthor(auth);

        Book book = new Book();
        book.setIsbn("978-3-16-0");
        book.setPublicDate(LocalDate.of(2005,12,13));
        book.setAuthorId(auth.getAuthorId());
        book.setTitle("Happy Moon");
        book.setPublisherId(pub.getPublisherId());
        book.setPrice(new BigDecimal( "15.99"));
        //Act
        book = bookDao.addBook(book);
        Book book2 = bookDao.getBook(book.getBookId());
        //Assert
        assertEquals(book,book2);
        //Act
        bookDao.deleteBook(book.getBookId());
        book2 = bookDao.getBook(book.getBookId());
        //Assert
        assertNull(book2);
    }
    @Test
    public void getAllBooks() {
        //have to set up publisher and author
        Publisher pub = new Publisher();
        pub.setName("Mark Jones");
        pub.setStreet("Abingdon Lane");
        pub.setCity("Norfolk");
        pub.setState("VA");
        pub.setPostalCode("23456");
        pub.setPhone("757-920-3492");
        pub.setEmail("mark.jones@gmail.com");
        pub=publisherDao.addPublisher(pub);

        Author auth = new Author();
        auth.setFirstName("Julia");
       auth.setLastNname("Jackson");
        auth.setStreet("WoodLane Drive");
        auth.setCity("Newport News");
        auth.setState("VA");
        auth.setPostalCode("23064");
        auth.setPhone("757-488-2941");
        auth.setEmail("julia.jackson@gmail.com");
        auth =authorDao.addAuthor(auth);
        //Arrange
        Book book = new Book();
        book.setIsbn("978-5-12-0");
        book.setPublicDate(LocalDate.of(2019,8,23));
        book.setAuthorId(auth.getAuthorId());
        book.setTitle("Everything Everything");
        book.setPublisherId(pub.getPublisherId());
        book.setPrice(new BigDecimal("17.99"));
        //Act
        book = bookDao.addBook(book);
        //Arrange
        book = new Book();
        book.setIsbn("978-3-15-0");
        book.setPublicDate(LocalDate.of(2019, 4, 10));
        book.setAuthorId(auth.getAuthorId());
        book.setTitle("Sun is also a Star");
        book.setPublisherId(pub.getPublisherId());
        book.setPrice(new BigDecimal("18.99"));
        //Act
        book= bookDao.addBook(book);
        List<Book> bookList = bookDao.getAllBooks();
        //Assert
        assertEquals(bookList.size(),2);
    }
    @Test
    public void getBooksByAuthor() {
        //need to create a publisher and author first
        Publisher pub = new Publisher();
        pub.setName("mack jones");
        pub.setStreet("Abingdon Lane");
        pub.setCity("Norfolk");
        pub.setState("VA");
        pub.setPostalCode("23456");
        pub.setPhone("757-920-3492");
        pub.setEmail("mark.jones@gmail.com");
        pub =publisherDao.addPublisher(pub);

        Author auth = new Author();
        auth.setFirstName("Julia");
        auth.setLastNname("Jackson");
        auth.setStreet("WoodLane Drive");
        auth.setCity("Newport News");
        auth.setState("VA");
        auth.setPostalCode("23064");
        auth.setPhone("757-488-2941");
        auth.setEmail("julia.jackson@gmail.com");

        auth =authorDao.addAuthor(auth);
        Author auth1 = new Author();
        auth1.setFirstName("Robert");
        auth1.setLastNname("Jones");
        auth1.setStreet("CloverField Lane");
        auth1.setCity("Hampton");
        auth1.setState("VA");
        auth1.setPostalCode("23174");
        auth1.setPhone("757-487-3871");
        auth1.setEmail("robert.jones@yahoomail.com");

        auth1 = authorDao.addAuthor(auth1);
        //Arrange
        Book book = new Book();
        book.setIsbn("978-5-12-0");
        book.setPublicDate(LocalDate.of(2017, 3, 11));
        book.setAuthorId(auth.getAuthorId());
        book.setTitle("Everything Everything");
        book.setPublisherId(pub.getPublisherId());
        book.setPrice(new BigDecimal("17.99"));
        //Act
        book= bookDao.addBook((book));
        //Arrange
        Book book1 = new Book();
        book1.setIsbn("978-3-15");
        book1.setPublicDate(LocalDate.of(2019, 4, 10));
        book1.setAuthorId(auth1.getAuthorId());
        book1.setTitle("Sun is also a Star");
        book1.setPublisherId(pub.getPublisherId());
        book1.setPrice(new BigDecimal("18.99"));
        //Act
        book1=bookDao.addBook(book1);
        //Arrange
        Book book2 = new Book();
        book2.setIsbn("978-9-18-0");
        book2.setPublicDate(LocalDate.of(2016, 8, 20));
        book2.setAuthorId(auth1.getAuthorId());
        book2.setTitle("Animal Farm");
        book2.setPublisherId(pub.getPublisherId());
        book2.setPrice(new BigDecimal("13.99"));
        //Act
        book2=bookDao.addBook(book2);
        List<Book> aList =bookDao.getAllBooksByAuthor(auth.getAuthorId());
        //Assert
        assertEquals(1,aList.size());
        //Act
        List<Book>bList = bookDao.getAllBooksByAuthor(auth1.getAuthorId());
        //Assert
        assertEquals(2,bList.size());
    }
    @Test
    public void updateBook() {
        //have to set up publisher and author
        Publisher pub = new Publisher();
        pub.setName("Mark Jones");
        pub.setStreet("Abingdon Lane");
        pub.setCity("Norfolk");
        pub.setState("VA");
        pub.setPostalCode("23456");
        pub.setPhone("757-920-3492");
        pub.setEmail("mark.jones@gmail.com");
        pub = publisherDao.addPublisher(pub);
        Author auth = new Author();
        auth.setFirstName("Julia");
        auth.setLastNname("Jackson");
        auth.setStreet("WoodLane Drive");
        auth.setCity("Newport News");
        auth.setState("VA");
        auth.setPostalCode("23064");
        auth.setPhone("757-488-2941");
        auth.setEmail("julia.jackson@gmail.com");
        auth = authorDao.addAuthor(auth);

        Book book = new Book();
        book.setIsbn("978-3-16-0");
        book.setPublicDate(LocalDate.of(2003,05,12));
        book.setAuthorId(auth.getAuthorId());
        book.setTitle("Happy Moon");
        book.setPublisherId(pub.getPublisherId());
        book.setPrice(new BigDecimal("15.99"));
        //Act
        book = bookDao.addBook(book);
        //Arrange
        book.setIsbn("978-4-17-0");
        book.setPublicDate(LocalDate.of(2003,8,23));
        book.setTitle("The MockingJay");
        book.setPrice(new BigDecimal("17.99"));
        //Act
        bookDao.updateBook(book);
        Book book2 = bookDao.getBook(book.getBookId());
        //Assert
        assertEquals(book2, book);
    }
}