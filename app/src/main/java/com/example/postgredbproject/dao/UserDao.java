package com.example.postgredbproject.dao;


import com.example.postgredbproject.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao extends AbstractDao {

    public void createUser(String name, String surname) {
        String query = "INSERT INTO users (name, surname) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);

            preparedStatement.setString(2, surname);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public List<User> readAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setSurName(resultSet.getString("surname"));
                users.add(user);
            }

        } catch (SQLException e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return users;
    }

    public void updateUser(Long id, String name, String surname) {
        String query = "UPDATE users SET name = ?, surname = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void deleteUser(Long id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}

