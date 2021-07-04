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
import java.sql.Statement;
import java.util.List;
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

    private final Connection conn;

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
            st = conn.prepareCall(sql);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Department dep = new Department();
                dep.setId(rs.getInt("departmentid"));
                dep.setName(rs.getString("depname"));

                Seller obj = new Seller();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setEmail(rs.getString("email"));
                obj.setBirthDate(rs.getDate("birthdate"));
                obj.setBaseSalary(rs.getDouble("basesalary"));
                obj.setDepartment(dep);

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

    @Override
    public List<Seller> findAll(Seller obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
