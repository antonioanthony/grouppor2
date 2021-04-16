package com.company.Summative2AnthonyAntonio.dao;

import com.company.Summative2AnthonyAntonio.dto.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class  AuthorDaojdbcTempImpl implements AuthorDao{

        //prepared statement Strings
        private static final String INSERT_AUTHOR_SQL=
                "insert into author( first_name, last_name, street, city,state,postal_code,phone,email) values(?,?,?,?,?,?,?,?)";
        private static final String SELECT_AUTHOR_SQL=
                "select *from author where author_id=?";
        private static final String SELECT_ALL_AUTHORS_SQL=
                "select *from author";
        private static final String DELETE_AUTHOR_SQL=
                "delete from author where author_id= ?";
        private static final String UPDATE_AUTHOR_SQL=
                "update author set first_name=?, last_name=?, city=?, state=?, postal_code=?, phone=?, email=? where id=?";
        private static final String SELECT_AUTHORS_BY_FIRSTNAME_SQL=
                "select * from author where first_name= ?";

        private JdbcTemplate jdbcTemplate;

        @Autowired
        public AuthorDaojdbcTempImpl (JdbcTemplate  jdbcTemplate){
            this.jdbcTemplate = jdbcTemplate;
        }

        @Override
        public Author getAuthor(int id) {

            try {

                return jdbcTemplate.queryForObject(SELECT_AUTHOR_SQL, this::mapRowtoAuthor,id);
            }catch(EmptyResultDataAccessException e){

                return null;
            }


        }

        @Override
        public List<Author> getAllAuthors() {
            return jdbcTemplate.query(SELECT_ALL_AUTHORS_SQL, this::mapRowtoAuthor);
    }

        @Override
        @Transactional
        public Author addAuthor(Author author) {
            jdbcTemplate.update(INSERT_AUTHOR_SQL,
                    author.getFirstName(),
                    author.getLastNname(),
                    author.getStreet(),
                    author.getCity(),
                    author.getState(),
                    author.getPostalCode(),
                    author.getPhone(),
                    author.getEmail());
            int authId= jdbcTemplate.queryForObject("select last_insert_id()",Integer.class);
            author.setAuthorId(authId);
            return author;

        }

    @Override
        public void updateAuthor(Author author) {

            jdbcTemplate.update(UPDATE_AUTHOR_SQL,
                    author.getFirstName(),
                    author.getLastNname(),
                    author.getStreet(),
                    author.getCity(),
                    author.getState(),
                    author.getPostalCode(),
                    author.getPhone(),
                    author.getEmail(),
                    author.getAuthorId());
        }

    @Override
    public void deleteAuthor(int id) {
        jdbcTemplate.update(DELETE_AUTHOR_SQL, id);
    }

    @Override
    public List<Author> getAllAuthorsFirstName(String firstname) {
            return null;
    }

    private Author mapRowtoAuthor(ResultSet rs, int rowNum) throws SQLException {

            Author auth= new Author();
            //auth.setId(rs.getInt("id"));
            auth.setAuthorId(rs.getInt("author_id"));
            auth.setFirstName(rs.getString("first_name"));
            auth.setLastNname(rs.getString("last_name"));
            auth.setStreet(rs.getString("street"));
            auth.setCity(rs.getString("city"));
            auth.setState(rs.getString("state"));
            auth.setPostalCode(rs.getString("postal_code"));
            auth.setPhone(rs.getString("phone"));
            auth.setEmail(rs.getString("email"));

            return auth;
        }
}
