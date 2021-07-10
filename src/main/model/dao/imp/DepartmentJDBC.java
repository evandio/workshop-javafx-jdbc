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
import java.util.ArrayList;
import java.util.List;
import main.db.DB;
import main.model.dao.DaoFactory;
import main.model.dao.DepartmentDao;
import main.model.entities.Department;

/**
 *
 * @author evand
 */
public class DepartmentJDBC implements DepartmentDao {

    private Connection conn;

    public DepartmentJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;

        String sql = "INSERT INTO department (name) VALUES (?)";
        try {
            st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getName());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public void updatet(Department obj) {
        PreparedStatement st = null;

        String sql = "UPDATE department SET name = ? WHERE id = ?";

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;

        String sql = "DELETE FROM department WHERE id = ?";

        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, id);

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        } finally{
            DB.closeStatement(st);
        }

    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "select * "
                + "from "
                + " department "
                + "where "
                + " id = ?";

        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Department dep = instantiateDepartment(rs);
                return dep;
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
    public List<Department> findAll(Department obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "select * "
                + "from "
                + " department ";

        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            List<Department> list = new ArrayList<>();

            while (rs.next()) {
                list.add(instantiateDepartment(rs));
            }

            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("id"));
        dep.setName(rs.getString("name"));

        return dep;
    }

    public static void main(String args[]) {

        DepartmentDao dao = DaoFactory.createDepartmenttDao();
        Department dep = dao.findById(7);
        dao.deleteById(dep.getId());
    }

}
