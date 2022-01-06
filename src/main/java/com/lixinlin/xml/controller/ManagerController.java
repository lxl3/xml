package com.lixinlin.xml.controller;

import com.lixinlin.xml.bean.Book;

import com.lixinlin.xml.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ManagerController {
    @Autowired
    private BookService bookService;
    @RequestMapping(value = "/manager.html",method = RequestMethod.GET)
    public String manager(Model model){
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books",books);
        return "manager";
    }
    @RequestMapping(value = "/Bookdelete/{id}")
    public String delete(@PathVariable("id") String id){
        System.out.println("删除成功");
        boolean res = bookService.deleteBookById(id);
        return "redirect:/manager.html";
    }
    @GetMapping(value = "/Book/{id}")
    public String getBookById(@PathVariable("id") String id,Model model){
        Book book = bookService.getBookById(id);
//        System.out.println(book);
        model.addAttribute("book",book);
        return "Book_update";
    }

    @RequestMapping(value = "/Bookupdate")
    public String update(Book book){
        bookService.update(book);
        return "redirect:/manager.html";
    }
    @RequestMapping(value = "/Book",method = RequestMethod.POST)
    public String add(Book book,Model model){
        try{
            bookService.addBook(book);
        }catch (Exception e){
            model.addAttribute("msg","输入信息有误");
            e.printStackTrace();
            return "redirect:/Book_add.html";
        }
        return "redirect:/manager.html";
    }
    @RequestMapping("Book_add.html")
    public String Book(){
        return "Book_add";
    }
    @RequestMapping("Book_update.html")
    public String updateBook(){
        return "Book_update";
    }
    @RequestMapping(value = "/Book/search")
    public String search(String id, Model model){
//        System.out.println(id);
        Book book = bookService.getBookById(id);
        if(book==null){
            model.addAttribute("msg","查询结果为空");
            return "redirect:/manager.html";
        }
        else{
            model.addAttribute("book",book);
        }
        return "research.html";

    }
    @RequestMapping("/research.html")
    public String research(Model model){
        Book getbook = (Book) model.getAttribute("getbook");
        System.out.println(getbook);
        return "research";
    }

}
