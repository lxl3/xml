package com.lixinlin.xml.mapper;

import com.lixinlin.xml.bean.Book;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Repository
public class Bookmapper {
    private static final String filename = "D:\\XML\\xml\\src\\main\\java\\com\\lixinlin\\xml\\mapper\\Books.xml";
    //获取所有图书
    public static List<Book> getallBooks(){
        InputStream inputStream = null;
        Document doc=null;
        SAXReader reader=null;
        List<Book> books=new ArrayList<>();
        try {
            inputStream = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        reader = new SAXReader();
        try {
             doc = reader.read(inputStream);
            Element root = doc.getRootElement();
            List<Element> elements = root.elements();
            for(Element element : elements){
                String id = element.attributeValue("id");
                String category = element.attributeValue("category");
                String isbn = element.elementText("ISBN");
                String title = element.elementText("title");
                String author = element.elementText("author");
                String press = element.elementText("press");
                Double price = Double.valueOf(element.elementText("price"));
                Book book = new Book(id, category, isbn, title,author,press, price);
                books.add(book);
            }
            return books;
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return null;

    }
    //根据id删除图书
    public boolean deleteById(String id){
        InputStream inputStream = null;
        Document doc=null;
        SAXReader reader=null;
        List<Book> books=new ArrayList<>();
        try {
            inputStream = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        reader = new SAXReader();
        try {
            doc = reader.read(inputStream);
            Element root = doc.getRootElement();
            List<Element> elements = root.elements();
            for(Element element : elements) {
                if (id != null && id.equals(element.attributeValue("id"))) {
                    element.detach();
                }
            }
            FileWriter writer = new FileWriter(filename);
            doc.write(writer);
            writer.flush();
            writer.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    //添加图书
    public boolean addBook(Book book){
        SAXReader reader = new SAXReader();
        FileInputStream is = null;
        FileOutputStream os = null;
        try {
            is = new FileInputStream(filename);
            Document doc = reader.read(is);
            Element root = doc.getRootElement();
            Element element = root.addElement("book");
            element.addAttribute("id",book.getId());
            element.addAttribute("category",book.getCategory());
            element.addElement("ISBN").addText(book.getISBN());
            element.addElement("title").addText(book.getTitle());
            element.addElement("author").addText(book.getAuthor());
            element.addElement("press").addText(book.getPress());
            element.addElement("price").addText(String.valueOf(book.getPrice()));
            XMLWriter writer = new XMLWriter();
            os = new FileOutputStream(filename);
            writer.setOutputStream(os);
            writer.write(doc);
            writer.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    //修改图书
    public boolean update(Book book){
        InputStream in = null;
        SAXReader reader = new SAXReader();
        Document doc = null;
        try {
            in = new FileInputStream(filename);
            doc = reader.read(in);
            Element root = doc.getRootElement();
            for (Iterator it = root.elementIterator(); it.hasNext(); ) {
                Element element = (Element) it.next();
                if (book.getId().equals(element.attributeValue("id"))) {
                    element.attribute("id").setValue(book.getId());
                    element.attribute("category").setValue(book.getCategory());
                    element.element("ISBN").setText(book.getISBN());
                    element.element("title").setText(book.getTitle());
                    element.element("author").setText(book.getAuthor());
                    element.element("press").setText(book.getPress());
                    element.element("price").setText(String.valueOf(book.getPrice()));
                    break;
                }
            }
            FileWriter writer = new FileWriter(filename);
            doc.write(writer);
            writer.flush();
            writer.close();
            return true;
        } catch (Exception e) {
            System.out.println("error");
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                System.out.println("error");
            }
            return false;
        }
    }
    //根据id获取图书
    public Book getBookById(String id) {
        InputStream inputStream = null;
        Document doc=null;
        SAXReader reader=null;
        try {
            inputStream = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        reader = new SAXReader();
        try {
            doc = reader.read(inputStream);
            Element root = doc.getRootElement();
            List<Element> elements = root.elements();
            for(Element element : elements){
                //判断这本书是否存在
                if(id!=null&&id.equals(element.attributeValue("id"))){
                    String id1 = element.attributeValue("id");
                    String category = element.attributeValue("category");
                    String isbn = element.elementText("ISBN");
                    String title = element.elementText("title");
                    String author = element.elementText("author");
                    String press = element.elementText("press");
                    Double price = Double.valueOf(element.elementText("price"));
                    Book book = new Book(id1, category, isbn, title,author,press, price);
                    return book;
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return null;
    }
}
