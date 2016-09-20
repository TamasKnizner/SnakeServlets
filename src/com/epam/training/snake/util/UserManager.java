package com.epam.training.snake.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.epam.training.snake.entity.User;

public class UserManager {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "admin", "admin@admin.com", "admin"));
    }

    public static void saveUsers() {
        try {
            FileOutputStream out = new FileOutputStream("users.ser");
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(users);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Users saved to users.ser");
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadUsers() {
        try {
            File file = new File("users.ser");
            if (!file.exists()) {
                return;
            }
            FileInputStream in = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(in);
            users = (ArrayList<User>) (ois.readObject());
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Users loaded from users.ser");
            printList();
        }
    }

    private static void printList() {
        users.forEach(user -> System.out.println(user.toString()));
    }

    public static boolean addUser(User newUser) {
        if (users.contains(newUser)) {
            return false;
        }
        users.add(newUser);
        return true;
    }

    public static User getUserByName(String email) {
        User found = null;
        for (User u : users) {
            if (email.equals(u.getEmail())) {
                found = u;
            }
        }
        return found;
    }

    public static boolean validateLogin(String email, String password) {
        User user = getUserByName(email);
        boolean isValid = false;
        if (user != null) {
            isValid = password.equals(user.getPassword());
        }
        return isValid;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static Integer getNewId() {
        return users.size() + 1;
    }

    public static User getUserById(Integer id) {
        User found = null;
        for (User u : users) {
            if (id.equals(u.getId())) {
                found = u;
            }
        }
        return found;
    }
}
