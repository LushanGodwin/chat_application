package lk.ijse.chat_box.bo.custom;

import lk.ijse.chat_box.dto.UserDTO;
import lk.ijse.chat_box.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserBO {
    boolean addUser(UserDTO user) throws SQLException;
    boolean isExistUser(String user_name) throws SQLException;
    boolean isValidUser(UserDTO user) throws SQLException;
    List<UserDTO> getAllUsers() throws SQLException;
}
