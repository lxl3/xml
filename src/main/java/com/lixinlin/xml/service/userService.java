package com.lixinlin.xml.service;

import com.lixinlin.xml.bean.User;
import com.lixinlin.xml.mapper.Usermapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class userService {
    @Autowired
    private Usermapper usermapper;
    public void add(User user){
        usermapper.addUser(user);
    }
    public User getUserByName(String name){
        User user = usermapper.getUserByName(name);
        if(user==null){
//            System.out.println("用户名可用");
            return null;
        }
        return user;
    }
    public String upload(MultipartFile file){
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileName = file.getOriginalFilename();
        String filePath = "D:\\XML\\xml\\src\\main\\resources\\static\\images\\";
        File dest = new File(filePath + fileName);
        try {
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            log.info("上传成功");
            return filePath+fileName;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString(), e);
        }
        return "上传失败！";
    }
    public List<User> getUsers(){
        List<User> users = usermapper.getUsers();
        return users;
    }
}
