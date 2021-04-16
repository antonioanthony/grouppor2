package com.company.Summative2AnthonyAntonio.dao;

import com.company.Summative2AnthonyAntonio.dto.Publisher;

import java.util.List;

public interface PublisherDao {
    Publisher getPublisher (int id);
    List<Publisher> getAllPublishers();
    Publisher addPublisher(Publisher publisher);
    void updatePublisher (Publisher publisher);
    void deletePublisher(int id);
    List<Publisher> getAllPublishersFirstName(String Firstname);

}
