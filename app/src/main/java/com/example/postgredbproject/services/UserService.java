package com.example.postgredbproject.services;



import com.example.postgredbproject.dao.UserDao;
import com.example.postgredbproject.entities.User;

import java.util.List;

public class UserService {

    private final UserDao userDao;

    public UserService() {
        userDao = new UserDao(); // DAO sınıfı örneği
    }

    // Kullanıcı ekleme
    public void addUser(String name, String surname) {
        User user = new User();
        user.setName(name);
        user.setSurName(surname);
        userDao.createUser(name,surname); // DAO'yu çağırarak kullanıcı ekle
    }

    // Tüm kullanıcıları listeleme
    public List<User> getAllUsers() {
        return userDao.readAllUsers();
    }

    // Kullanıcı güncelleme
    public void updateUser(Long id, String name, String surname) {
        userDao.updateUser(id,name,surname);

    }

    // Kullanıcı silme
    public void removeUser(Long id) {
        userDao.deleteUser(id); // ID'ye göre kullanıcı sil
    }
}
