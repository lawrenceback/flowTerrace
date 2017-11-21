package com.jxgis.ffireserver.web.bean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YanWei on 2017/6/17.
 *
 * 角色功能表
 *
 * @author 言伟
 *
 */
@Entity
@Table(name = "t_s_role_function")
public class TSRoleFunction extends IdEntity implements java.io.Serializable {

    private TSFunction function;
    private TSRole role;

    List<TSRoleFunction> roleFunctions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "function_id")
    public TSFunction getFunction() {
        return function;
    }

    public void setFunction(TSFunction function) {
        this.function = function;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    public TSRole getRole() {
        return role;
    }

    public void setRole(TSRole role) {
        this.role = role;
    }

    @Transient
    public List<TSRoleFunction> getRoleFunctions() {
        return roleFunctions;
    }

    public void setRoleFunctions(List<TSRoleFunction> roleFunctions) {
        this.roleFunctions = roleFunctions;
    }
}
