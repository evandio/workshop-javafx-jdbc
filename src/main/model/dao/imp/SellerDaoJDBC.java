/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.model.dao.imp;

import main.db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.db.DB;
import main.model.dao.DaoFactory;
import main.model.dao.SellerDao;
import main.model.entities.Department;
import main.model.entities.Seller;

/**
 *
 * @author evand
 */
public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void updatet(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "select *, department.name as DepName "
                + "from "
                + "seller INNER JOIN department ON seller.departmentid = department.id "
                + "where "
                + "seller.id = ?";

        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Department dep = instantiateDepartment(rs);

                Seller obj = instantiateSeller(rs, dep);

                return obj;
            }

            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    //Esse ei fiz sozinho verificar nas aulas
    @Override
    public List<Seller> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "select "
                + "*, department.name as depName "
                + "from "
                + " seller INNER JOIN department ON seller.departmentid = department.id "
                + "ORDER BY department.name ";

        try {
            st = conn.prepareStatement(sql);

            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {
                Department dep = map.get(rs.getInt("departmentid"));
                if (dep == null) {
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("departmentid"), dep);
                }
                Seller obj = instantiateSeller(rs, dep);
                list.add(obj);
            }

            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("departmentid"));
        dep.setName(rs.getString("depname"));

        return dep;
    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {

        Seller obj = new Seller();
        obj.setId(rs.getInt("id"));
        obj.setName(rs.getString("name"));
        obj.setEmail(rs.getString("email"));
        obj.setBirthDate(rs.getDate("birthdate"));
        obj.setBaseSalary(rs.getDouble("basesalary"));
        obj.setDepartment(dep);

        return obj;
    }

    @Override
    public List<Seller> findByDepartment(Department departmentt) {
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "select "
                + "*, department.name as depName "
                + "from "
                + " seller INNER JOIN department ON seller.departmentid = department.id "
                + "WHERE "
                + " seller.departmentid = ? "
                + "ORDER BY department.name ";

        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, departmentt.getId());
            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {
                Department dep = map.get(rs.getInt("departmentid"));
                if (dep == null) {
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("departmentid"), dep);
                }

                Seller obj = instantiateSeller(rs, dep);
                list.add(obj);
            }

            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
    
}
