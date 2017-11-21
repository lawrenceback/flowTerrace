package com.jxgis.ffireserver.web.bean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YanWei on 2017/6/17.
 *
 * 角色用户表
 *
 * @author 言伟
 */
@Entity
@Table(name = "t_s_role_user")
public class TSRoleUser extends IdEntity implements java.io.Serializable {

    private TSUser user;
    private TSRole role;

    List<TSRoleUser> roleUsers = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public TSUser getUser() {
        return user;
    }

    public void setUser(TSUser user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    public TSRole getRole() {
        return role;
    }

    public void setRole(TSRole role) {
        this.role = role;
    }

    @Transient
    public List<TSRoleUser> getRoleUsers() {
        return roleUsers;
    }

    public void setRoleUsers(List<TSRoleUser> roleUsers) {
        this.roleUsers = roleUsers;
    }
}
