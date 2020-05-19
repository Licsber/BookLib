package site.licsber.book.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String userName;
    private String userPwd;
    private String sex;
    private Date date;
    private String mail;
    private String work;
    private String like;
    private String intro;
}
