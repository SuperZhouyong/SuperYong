package com.sxzx.GreenDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator
 * on 2016/10/25.
 */
@Entity
public class User {
    @Id(autoincrement = true)
    private Long id ;
    @NotNull
    private String SearchName ;
    public String getSearchName() {
        return this.SearchName;
    }
    public void setSearchName(String SearchName) {
        this.SearchName = SearchName;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1463166143)
    public User(Long id, @NotNull String SearchName) {
        this.id = id;
        this.SearchName = SearchName;
    }
    @Generated(hash = 586692638)
    public User() {
    }
 
   

}
