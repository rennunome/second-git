/**package com.example.security;

import org.springframework.security.core.userdetails.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
//equals()とhashCode()を生成するが親クラスのメソッドは呼び出さない
@EqualsAndHashCode(callSuper=false)
public class LoginUserDetails extends User {
    //employeeテーブルから取得したオブジェクトを格納
    private final User user;

    //認証処理
    public LoginUserDetails(User user) {
        //employeeテーブルの名前とパスワードでログイン認証を行う
        super(user.getId(), user.getPassword());
        this.user = user;
    }
}*/