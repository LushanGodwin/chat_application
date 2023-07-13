package lk.ijse.chat_box.dao.custom.impl;

import lk.ijse.chat_box.dao.SQLUtil;
import lk.ijse.chat_box.dao.custom.UserDAO;
import lk.ijse.chat_box.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean add(User user) throws SQLException {
        String sql = "INSERT INTO user(name,password) VALUES(?,?);";
       return SQLUtil.execute(sql,user.getUser_name(),user.getPassword());
    }

    @Override
    public boolean isExist(String user_name) throws SQLException {
        String sql = "SELECT name FROM user WHERE name = ? ;";
        ResultSet resultSet = SQLUtil.execute(sql, user_name);
        return resultSet.next();
    }

    @Override
    public boolean isValid(User user) throws SQLException {
        String sql = "SELECT * FROM user WHERE name = ? ;";
        ResultSet resultSet = SQLUtil.execute(sql,user.getUser_name());
        if (resultSet.next()){
            return resultSet.getString(2).equals(user.getPassword());
        }
        return false;
    }

    @Override
    public List<User> getAll() throws SQLException {
        String sql = "SELECT * FROM user;";
        ResultSet resultSet = SQLUtil.execute(sql);
        List<User> userList = new ArrayList<>();

        while (resultSet.next()){
            userList.add(new User(resultSet.getString(1),resultSet.getString(2)));
        }
        return userList;
    }
}
