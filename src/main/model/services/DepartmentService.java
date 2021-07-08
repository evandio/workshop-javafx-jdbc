/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.model.services;

import java.util.List;
import main.model.dao.DaoFactory;
import main.model.dao.DepartmentDao;
import main.model.entities.Department;

/**
 *
 * @author evand
 */
public class DepartmentService {

    private DepartmentDao dao = DaoFactory.createDepartmenttDao();

    public List<Department> findAll() {
        return  dao.findAll();
    }
    
     public void saveOrUpdatet(Department obj){
         if (obj.getId() == null){
             dao.insert(obj);
         } else {
             dao.updatet(obj);
         }
     }
}
