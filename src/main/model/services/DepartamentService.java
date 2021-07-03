/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.model.services;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.List;
import main.model.entities.Department;

/**
 *
 * @author evand
 */
public class DepartamentService {

    public List<Department> findAll() {
        //MOCK dados

        List<Department> list = new ArrayList<>();
        list.add(new Department(1, "Books"));
        list.add(new Department(2, "Computes"));
        list.add(new Department(3, "Eletronics"));
        list.add(new Department(4, "Games"));

        return list;
    }

}
