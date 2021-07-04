/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.model.dao;

import main.db.DB;
import main.model.dao.imp.DepartmentJDBC;
import main.model.dao.imp.SellerDaoJDBC;

/**
 *
 * @author evand
 */
public class DaoFactory {

    public static SellerDao createSellerDao() {
        return new SellerDaoJDBC(DB.getConnection());
    }

    public static DepartmentDao createDepartmenttDao() {
        return new DepartmentJDBC();
    }

}
