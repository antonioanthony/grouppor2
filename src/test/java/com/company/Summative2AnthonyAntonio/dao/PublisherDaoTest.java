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
public class PublisherDaoTest {
        @Autowired
        public PublisherDao dao;
        @Autowired
        public AuthorDao authorDao;
        @Autowired
        public BookDao bookDao;
        @Before
        public void setUp() throws Exception {
            // clean out the test db
            List<Book> bList = bookDao.getAllBooks();
            for (Book b : bList) {
                bookDao.deleteBook(b.getBookId());
            }
            List<Author> aList = authorDao.getAllAuthors();
            for (Author a: aList){
                authorDao.deleteAuthor(a.getAuthorId());
            }
            List<Publisher> pList = dao.getAllPublishers();
            for(Publisher p: pList){
                dao.deletePublisher(p.getPublisherId());
            }
        }
        @Test
        public void addGetDeletePublisher() {
            Publisher pub = new Publisher();
            pub.setName("Alice");
            //pub.setLastName("Jones");
            pub.setStreet("Abingdon Lane");
            pub.setCity("Norfolk");
            pub.setState("VA");
            pub.setPostalCode("23456");
            pub.setPhone("757-459-3492");
            pub.setEmail("alice.jones@gmail.com");
            //Act
            pub = dao.addPublisher(pub);
            Publisher pub2 = dao.getPublisher(pub.getPublisherId());
            //Assert
            assertEquals(pub,pub2);
            //Act
            dao.deletePublisher(pub.getPublisherId());
            pub2 = dao.getPublisher(pub.getPublisherId());
            //Assert
            assertNull(pub2);
        }
        @Test
        public void getAllPublishers() {
            //Arrange
            Publisher pub = new Publisher();
            pub.setName("Blake");
            //pub.setLastName("walker");
            pub.setStreet("Baker Street");
            pub.setCity("Williamsburg");
            pub.setState("VA");
            pub.setPostalCode("233455");
            pub.setPhone("757-289-8622");
            pub.setEmail("blake.walker@gmail.com");
            //Act
            pub = dao.addPublisher(pub);
            //Arrange
            Publisher pub1= new Publisher();
            pub1.setName("Christian");
            //pub.setLastName("Smith");
            pub1.setStreet("MeadowField Drive");
            pub1.setCity("Williamsburg");
            pub1.setState("VA");
            pub1.setPostalCode("233455");
            pub1.setPhone("757-849-7542");
            pub1.setEmail("cwalker@gmail.com");
            //Act
            dao.addPublisher(pub1);
            List<Publisher> pubList = dao.getAllPublishers();
            //Assert
            assertEquals(2, pubList.size());
        }
        @Test
        public void updatePublisher() {
            Publisher pub = new Publisher();
            pub.setName("Alice");
            //pub.setLastName("Jones");
            pub.setStreet("Abingdon Lane");
            pub.setCity("Norfolk");
            pub.setState("VA");
            pub.setPostalCode("23456");
            pub.setPhone("757-459-3492");
            pub.setEmail("alice.jones@gmail.com");
            //Act
            pub = dao.addPublisher(pub);
            //Arrange
            pub.setName("Rebekah");
            //pub.setLastName("clarke");
            pub.setStreet("Rocky Road");
            pub.setCity("Richmond");
            pub.setPhone("757-989-4287");
            pub.setEmail("rpclarke@gmail.com");
            //Act
            dao.updatePublisher(pub);
            Publisher pub2 = dao.getPublisher(pub.getPublisherId());
            //Assert
            assertEquals(pub2, pub);

        }

}