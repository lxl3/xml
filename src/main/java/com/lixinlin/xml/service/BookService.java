package com.lixinlin.xml.service;


import com.lixinlin.xml.bean.Book;
import com.lixinlin.xml.mapper.Bookmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private Bookmapper bookmapper;
    public Book getBookById(String id){
        Book book = bookmapper.getBookById(id);
        return book;
    }
    public List<Book> getAllBooks(){
        List<Book> books = bookmapper.getallBooks();
        return books;
    }
    public boolean deleteBookById(String id){
        boolean res = bookmapper.deleteById(id);
        return res;
    }
    public boolean addBook(Book book){
        return bookmapper.addBook(book);
    }
    public boolean update(Book book){
        return bookmapper.update(book);
    }
}
