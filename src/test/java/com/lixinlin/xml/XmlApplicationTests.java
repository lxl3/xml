package com.lixinlin.xml;

import com.lixinlin.xml.bean.Book;
import com.lixinlin.xml.bean.User;
import com.lixinlin.xml.mapper.Bookmapper;
import com.lixinlin.xml.mapper.Usermapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class XmlApplicationTests {
    @Autowired
    private Usermapper usermapper;
    private Bookmapper bookmapper;
    @Test
    public void getAllBooks(){
        List<Book> books = bookmapper.getallBooks();
        System.out.println(books);
    }
    @Test
    void contextLoads() {
    }
    @Test
    public void testGetallUser(){
        List<User> users = usermapper.getUsers();
        System.out.println(users);
    }
    @Test
    public void test(){
        String path="D:\\XML\\xml\\src\\main\\resources\\static\\images\\Sunny.jpg";

    }
//    @Test
//    public void testDelete(){
//        boolean b = usermapper.deleteUser("张三");
//        System.out.println(b);
//
//    }

}
