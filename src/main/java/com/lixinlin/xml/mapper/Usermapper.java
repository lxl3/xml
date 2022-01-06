package com.lixinlin.xml.mapper;


import com.lixinlin.xml.bean.User;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class Usermapper {
    private static final String filename = "D:\\XML\\xml\\src\\main\\java\\com\\lixinlin\\xml\\mapper\\Users.xml";
    public void addUser(User User) {
        SAXReader reader = new SAXReader();
        FileInputStream is = null;
        FileOutputStream os = null;
        try {
            is = new FileInputStream(filename);
            Document doc = reader.read(is);
            Element root = doc.getRootElement();
            Element element = root.addElement("User");
            element.addElement("name").addText(User.getName());
            element.addElement("password").addText(String.valueOf(User.getPassword()));
            element.addElement("sex").addText(String.valueOf(User.getSex()));
            String[] hobby = User.getHobby();
            for(String ho:hobby){
                element.addElement("hobby").addText(ho);
            }
            element.addElement("filepath").addText(User.getImgpath());
            XMLWriter writer = new XMLWriter();
            os = new FileOutputStream(filename);
            writer.setOutputStream(os);
            writer.write(doc);
            writer.close();
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

    }
    //列出xml中所有用户信息
    public static List<User> getUsers() {
        InputStream in = null;
        SAXReader reader = new SAXReader();
        Document doc = null;
        List<User> Users = new ArrayList<>();
        try
        {
            in = new FileInputStream(filename);
            doc = reader.read(in);
            Element root = doc.getRootElement();
            List<Element> elements = root.elements();
            Users = new ArrayList<User>();
            for (Element element : elements)
            {
                User User = new User();
                User.setName(element.elementText("name"));
                User.setPassword(String.valueOf(Integer.valueOf(element.elementText("password"))));
                User.setSex(String.valueOf(Boolean.valueOf(element.elementText("sex"))));
//                User.setHobby(element.elementText("hobby"));
                Users.add(User);
            }
        }
        catch (Exception e1)
        {
            System.out.println("error");
        }
        finally
        {
            try
            {
                in.close();
            }
            catch (IOException e)
            {
                System.out.println("error");
            }
        }

        return Users;
    }
    //通过姓名获取用户信息
    public static User getUserByName(String name){
        InputStream in = null;
        SAXReader reader = new SAXReader();
        Document doc = null;
        try {
            in = new FileInputStream(filename);
            doc = reader.read(in);
            Element root = doc.getRootElement();
            List<Element> elements = root.elements();
            for(Element element : elements)
            {
                User user = new User();
                if(name != null && name.equals(element.elementText("name"))){
                    user.setName(name);
                    user.setPassword(element.elementText("password"));
                    user.setSex((element.elementText("sex")).equals("on") ? "男": "女");
                    user.setImgpath((element.elementText("filepath")));
                    System.out.println(user);
                    return user;
                }
            }
            FileWriter writer = new FileWriter(filename);
            doc.write(writer);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
