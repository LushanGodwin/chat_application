package lk.ijse.chat_box.bo.custom.impl;

import lk.ijse.chat_box.bo.custom.UserBO;
import lk.ijse.chat_box.dao.custom.UserDAO;
import lk.ijse.chat_box.dao.custom.impl.UserDAOImpl;
import lk.ijse.chat_box.dto.UserDTO;
import lk.ijse.chat_box.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean addUser(UserDTO user) throws SQLException {
        return userDAO.add(new User(user.getUser_name(),user.getPassword()));
    }

    @Override
    public boolean isExistUser(String user_name) throws SQLException {
        return userDAO.isExist(user_name);
    }

    @Override
    public boolean isValidUser(UserDTO user) throws SQLException {
        return userDAO.isValid(new User(user.getUser_name(),user.getPassword()));
    }

    @Override
    public List<UserDTO> getAllUsers() throws SQLException {
        List<User> allUsers = userDAO.getAll();
        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user : allUsers){

            userDTOS.add(new UserDTO(user.getUser_name(),user.getPassword()));

        }
        return userDTOS;

    }
}
