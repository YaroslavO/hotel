package com.yaroslav.hotel.dao;

import com.yaroslav.hotel.entity.Role;

/**
 * Created by employee on 6/23/15.
 */
public interface RoleDao {
    Role getRoleByName(String name);
}
