package pe.isil.DAO;

import pe.isil.model.User;
import pe.isil.util.DataBaseUtil;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//"INSERT INTO tbl_users (login, password, fullName, isActive, expirationDate) values (?, ?, ?, ?, ?)";
public class UserDAO implements BaseDAO<User, Integer> {
    @Override
    public void create(User user) {
        try(Connection co = DataBaseUtil.getConnection()){
            final String sql = "INSERT INTO tbl_users (login, password, fullName, isActive, expirationDate) values (?, ?, ?, ?, ?)";
            try(PreparedStatement ps = co.prepareStatement(sql)){
                ps.setString(1, user.getLogin());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getFullName());
                ps.setBoolean(4, user.getIsActive());
                ps.setDate(5, Date.valueOf(user.getExpirationDate()));
                ps.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try(Connection co = DataBaseUtil.getConnection()){
            final String sql = "UPDATE tbl_users SET login = ?, password = ?, fullName = ?, isActive = ?, expirationDate = ? WHERE id = ?)";
            try(PreparedStatement ps = co.prepareStatement(sql)){
                ps.setString(1, user.getLogin());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getFullName());
                ps.setBoolean(4, user.getIsActive());
                ps.setDate(5, Date.valueOf(user.getExpirationDate()));
                ps.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        try(Connection co = DataBaseUtil.getConnection()){
            final String sql = "DELETE FROM tbl_users WHERE id = ?)";
            try(PreparedStatement ps = co.prepareStatement(sql)){
                ps.setInt(1, user.getId());
                ps.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection co = DataBaseUtil.getConnection()){
            final String sql = "SELECT * FROM tbl_users";
            try(Statement st = co.createStatement();){
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()){
                    int id = rs.getInt("id");
                    String login = rs.getString("login");
                    String password = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    Boolean isActive = rs.getBoolean("isActive");
                    LocalDate expirationDate = rs.getDate("expirationDate").toLocalDate();
                    User user = new User(id, login, password, fullname, isActive, expirationDate);
                    users.add(user);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User getById(Integer id) {
            User user = null;
            try(Connection co = DataBaseUtil.getConnection()){
                final String sql = "SELECT * FROM tbl_users WHERE id = ?";
                try(PreparedStatement ps = co.prepareStatement(sql);){
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()){
                        int idUser = rs.getInt("id");
                        String login = rs.getString("login");
                        String password = rs.getString("password");
                        String fullName = rs.getString("fullName");
                        Boolean isActive = rs.getBoolean("isActive");
                        LocalDate expirationDate = rs.getDate("expirationDate").toLocalDate();
                        user = new User(id, login, password, fullName, isActive, expirationDate);
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        return user;
    }
}