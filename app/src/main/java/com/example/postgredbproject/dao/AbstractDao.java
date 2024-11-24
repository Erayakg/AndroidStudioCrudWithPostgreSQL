package com.example.postgredbproject.dao;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractDao {

        private Connection connection;

        public Connection getConnection(){

            try{

                StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

                StrictMode.setThreadPolicy(threadPolicy);

                this.connection= DriverManager.getConnection("jdbc:postgresql://*/AndroidAppdb","*","*");

            }catch (SQLException e){
                Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, null, e);
            }
            return this.connection;
        }

    }


