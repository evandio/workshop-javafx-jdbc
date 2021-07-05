/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.model.services;

import java.util.ArrayList;
import java.util.List;
import main.model.dao.DaoFactory;
import main.model.dao.DepartmentDao;
import main.model.entities.Department;

/**
 *
 * @author evand
 */
public class DepartamentService {

    private DepartmentDao dao = DaoFactory.createDepartmenttDao();

    public List<Department> findAll() {
        return  dao.findAll();
    }
}
