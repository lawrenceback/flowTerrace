package com.jxgis.ffireserver.web.bean;

import javax.persistence.*;

/**
 * Created by YanWei on 2017/6/17.
 *
 * 角色-组织机构 实体
 *
 * @author 言伟
 */
@Entity
@Table(name = "t_s_role_depart")
public class TSRoleDepart extends IdEntity implements java.io.Serializable{

    private TSDepart depart;
    private TSRole role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "depart_id")
    public TSDepart getDepart() {
        return depart;
    }

    public void setDepart(TSDepart depart) {
        this.depart = depart;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    public TSRole getRole() {
        return role;
    }

    public void setRole(TSRole role) {
        this.role = role;
    }
}
