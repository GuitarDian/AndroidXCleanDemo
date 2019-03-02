package com.dian.mycleandemo.data.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Dian on 2018/12/23
 * 登录返回类
 */

@Entity
public class User {

    private int id;
    private String name;
    private String tel;
    @Generated(hash = 507313784)
    public User(int id, String name, String tel) {
        this.id = id;
        this.name = name;
        this.tel = tel;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTel() {
        return this.tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }

}
