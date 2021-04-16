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

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AuthorDaoTest {

        @Autowired
        public AuthorDao dao;
        @Autowired
        public BookDao bookDao;
        @Autowired
        protected PublisherDao pubDao;

        @Before
        public void setUp() throws Exception {
            //clean up
            List<Author> aList= dao.getAllAuthors();
            for (Author a: aList){
                dao.deleteAuthor(a.getAuthorId());
            }
            List<Book> bookList= bookDao.getAllBooks();
            for (Book b: bookList){
                bookDao.deleteBook(b.getBookId());
            }
            List<Publisher> pList= pubDao.getAllPublishers();
            for (Publisher p: pList){
                pubDao.deletePublisher(p.getPublisherId());
            }
        }

        @Test
        public void addGetDeleteAuthor(){

            //arrange
            Author auth= new Author();
            auth.setFirstName("Antonio");
            auth.setLastNname("David");
            auth.setStreet("Ivystone way");
            auth.setCity("Chesapeake");
            auth.setState("VA");
            auth.setPostalCode("23320");
            auth.setPhone("757-222-1111");
            auth.setEmail("Antonio.David@hotmail.com");

            //act
            auth = dao.addAuthor(auth);
            Author auth2=dao.getAuthor(auth.getAuthorId());
            //assert
            assertEquals(auth,auth2);
            //act
            dao.deleteAuthor(auth.getAuthorId());
            auth2=dao.getAuthor(auth.getAuthorId());
            //assert
            assertNull(auth2);
        }
        @Test
        public void getAllAuthors(){
            //arrange
            Author auth= new Author();
            auth.setFirstName("tyrell");
            auth.setLastNname("parker");
            auth.setStreet("quail way");
            auth.setCity("Chesapeake");
            auth.setState("VA");
            auth.setPostalCode("23324");
            auth.setPhone("757-765-0876");
            auth.setEmail("tyrell.parker@yahoo.com");

            //act
            auth= dao.addAuthor(auth);

            //arrange
            auth = new Author();
            auth.setFirstName("Jessica");
            auth.setLastNname("Price");
            auth.setStreet("WoodOak Drive");
            auth.setCity("Newport News");
            auth.setState("VA");
            auth.setPostalCode("23064");
            auth.setPhone("757-945-2984");
            auth.setEmail("jessica.price@yahoomail.com");
            //Act
            auth = dao.addAuthor(auth);
            List<Author> authList = dao.getAllAuthors();
            //Assert
            assertEquals(authList.size(), 2);
        }
        @Test
        public void updateAuthor() {
            //Arrange
            Author auth = new Author();
            auth.setFirstName("Jacob");
            auth.setLastNname("Jackson");
            auth.setStreet("WoodLane Drive");
            auth.setCity("Newport News");
            auth.setState("VA");
            auth.setPostalCode("23064");
            auth.setPhone("757-844-2941");
            auth.setEmail("jacob.jackson@gmail.com");
            //Act
            auth = dao.addAuthor(auth);
            //Arrange
            auth.setFirstName("Whitney");
            auth.setLastNname("Harp");
            auth.setStreet("Waterfall Drive");
            auth.setCity("Williamsburg");
            auth.setState("VA");
            auth.setPostalCode("23165");
            auth.setPhone("757-937-7862");
            auth.setEmail("whitharp@gmail.com");
            //Act
            auth = dao.addAuthor(auth);
            Author auth2 = dao.getAuthor(auth.getAuthorId());
            //Assert
            assertEquals(auth2,auth);
        }






}