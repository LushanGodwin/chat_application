package lk.ijse.chat_box.dao.custom;

import lk.ijse.chat_box.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    boolean add(User user) throws SQLException;
    boolean isExist(String user_name) throws SQLException;
    boolean isValid(User user) throws SQLException;
    List<User> getAll() throws SQLException;

}
