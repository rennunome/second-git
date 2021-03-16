/**package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserIdNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.mybatis.mapper.UserMapper;

/***
 * ログインイン時に認証ユーザーを「employeeテーブル」から情報を取得するクラス
 */
/**@Service
public class LoginUserDetailsService implements UserDetailsService {
    @Autowired
    UserExample userExample;

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUserId(int id) throws UsernameNotFoundException {
        //入力された名前をキーにemployeeテーブルのレコードを1件取得
        User user = userMapper.selectById(id);

        //該当レコードが取得できなかった場合はエラーにする
        if  (user   ==  null)   {
            throw new UserIdNotFoundException("ユーザーIDかパスワードが違います。");
        }
        return new LoginUserDetails(user);
    }
}*/