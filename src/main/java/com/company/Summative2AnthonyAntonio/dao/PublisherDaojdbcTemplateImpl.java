package com.company.Summative2AnthonyAntonio.dao;

import com.company.Summative2AnthonyAntonio.dto.Author;
import com.company.Summative2AnthonyAntonio.dto.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PublisherDaojdbcTemplateImpl implements PublisherDao{

    //prepared statement Strings
    private static final String INSERT_PUBLISHER_SQL=
            "insert into publisher(name,street, city,state,postal_code,phone,email) values(?,?,?,?,?,?,?)";
    private static final String SELECT_PUBLISHER_SQL=
            "select * from publisher where publisher_id=?";
    private static final String SELECT_ALL_PUBLISHER_SQL=
            "select * from publisher";
    private static final String DELETE_PUBLISHER_SQL=
            "delete from publisher where publisher_id=?";
    private static final String UPDATE_PUBLISHER_SQL=
            "update publisher set name=?,  city=?, state=?, postal_code=?, phone=?, email=? where publisher_id=?";
    //private static final String SELECT_PUBLISHER_BY_FIRSTNAME_SQL=
           // "select * from  where first_name= ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PublisherDaojdbcTemplateImpl(JdbcTemplate  jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Publisher getPublisher(int id) {
        try{
            return jdbcTemplate.queryForObject(SELECT_PUBLISHER_SQL, this::mapRowtoAuthor);
        }catch(EmptyResultDataAccessException e){
            return null;
        }

    }

    @Override
    public List<Publisher> getAllPublishers() {
        return jdbcTemplate.query(SELECT_ALL_PUBLISHER_SQL,this::mapRowtoAuthor);
    }

    @Override
    @Transactional
    public Publisher addPublisher(Publisher publisher) {
        jdbcTemplate.update(INSERT_PUBLISHER_SQL,
                publisher.getName(),
                //publisher.getLastName(),
                publisher.getStreet(),
                publisher.getCity(),
                publisher.getState(),
                publisher.getPostalCode(),
                publisher.getPhone(),
                publisher.getEmail());

        int pubId= jdbcTemplate.queryForObject("select last_insert_id()",Integer.class);
        publisher.setPublisherId(pubId);
        return publisher;
    }

    @Override
    public void updatePublisher(Publisher publisher) {
        jdbcTemplate.update(UPDATE_PUBLISHER_SQL,
                publisher.getName(),
                //publisher.getLastName(),
                publisher.getStreet(),
                publisher.getCity(),
                publisher.getState(),
                publisher.getPostalCode(),
                publisher.getPhone(),
                publisher.getEmail(),
                publisher.getPublisherId()
        );

    }

    @Override
    public void deletePublisher(int id) {

        jdbcTemplate.update(DELETE_PUBLISHER_SQL,id);
    }

    @Override
    public List<Publisher> getAllPublishersFirstName(String Firstname) {
        return null;
    }

    //@Override
    //public List<Publisher> getAllPublishersname(String name) {
        //return null;


    private Publisher mapRowtoAuthor(ResultSet rs, int rowNum) throws SQLException {

        Publisher pub = new Publisher();
        pub.setName(rs.getString("name"));
       // pub.setLastName(rs.getString("last_name"));
        pub.setStreet(rs.getString("street"));
        pub.setCity(rs.getString("city"));
        pub.setState(rs.getString("state"));
        pub.setPostalCode(rs.getString("postal_code"));
        pub.setPhone(rs.getString("phone"));
        pub.setEmail(rs.getString("email"));

        return pub;

    }
}
