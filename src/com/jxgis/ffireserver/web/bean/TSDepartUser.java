package com.jxgis.ffireserver.web.bean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YanWei on 2017/6/17.
 *
 * 部门用户
 *
 * @author 言伟
 */
@Entity
@Table(name = "t_s_depart_user")
public class TSDepartUser extends IdEntity implements java.io.Serializable {

    private TSUser user;
    private TSDepart depart;

    private List<TSDepartUser> departUsers = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public TSUser getUser() {
        return user;
    }

    public void setUser(TSUser user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "depart_id")
    public TSDepart getDepart() {
        return depart;
    }

    public void setDepart(TSDepart depart) {
        this.depart = depart;
    }

    @Transient
    public List<TSDepartUser> getDepartUsers() {
        return departUsers;
    }

    public void setDepartUsers(List<TSDepartUser> departUsers) {
        this.departUsers = departUsers;
    }
}