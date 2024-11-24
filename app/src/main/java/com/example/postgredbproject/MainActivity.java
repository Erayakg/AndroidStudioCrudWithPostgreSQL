package com.example.postgredbproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.postgredbproject.services.UserService;
import com.example.postgredbproject.entities.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextSurname, editTextId;
    private TextView textViewOutput;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Görünümleri başlat
        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextId = findViewById(R.id.editTextId);
        textViewOutput = findViewById(R.id.textViewOutput);

        Button buttonCreate = findViewById(R.id.buttonCreate);
        Button buttonRead = findViewById(R.id.buttonRead);
        Button buttonUpdate = findViewById(R.id.buttonUpdate);
        Button buttonDelete = findViewById(R.id.buttonDelete);

        // Servisi başlat
        userService = new UserService();

        // Butonlara tıklama olaylarını ata
        buttonCreate.setOnClickListener(v -> createUser());
        buttonRead.setOnClickListener(v -> readUsers());
        buttonUpdate.setOnClickListener(v -> updateUser());
        buttonDelete.setOnClickListener(v -> deleteUser());
    }

    private void createUser() {
        String name = editTextName.getText().toString().trim();
        String surname = editTextSurname.getText().toString().trim();

        if (!name.isEmpty() && !surname.isEmpty()) {
            userService.addUser(name, surname);
            Toast.makeText(this, "User created successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Name and surname cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }

    private void readUsers() {
        List<User> users = userService.getAllUsers();
        StringBuilder output = new StringBuilder();

        for (User user : users) {
            output.append("ID: ").append(user.getId())
                    .append(", Name: ").append(user.getName())
                    .append(", Surname: ").append(user.getSurName())
                    .append("\n");
        }

        textViewOutput.setText(output.toString());
    }

    private void updateUser() {
        String idText = editTextId.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String surname = editTextSurname.getText().toString().trim();

        if (!idText.isEmpty() && !name.isEmpty() && !surname.isEmpty()) {
            Long id = Long.parseLong(idText);
            userService.updateUser(id, name, surname);
            Toast.makeText(this, "User updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "ID, name, and surname are required", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteUser() {
        String idText = editTextId.getText().toString().trim();

        if (!idText.isEmpty()) {
            Long id = Long.parseLong(idText);
            userService.removeUser(id);
            Toast.makeText(this, "User deleted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "ID is required to delete a user", Toast.LENGTH_SHORT).show();
        }
    }
}
