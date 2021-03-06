package com.example.alireza.taskmanager;

import java.util.ArrayList;
import java.util.Vector;

class ExistedUserException extends Exception {
    public ExistedUserException(String message) {
        super(message);
    }
}

class UnknownUserException extends Exception {
    public UnknownUserException(String message) {
        super(message);
    }
}

public class Server {
    static Vector<User> users = new Vector<>();

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public void Register(String u, String e, String n, String p, int t) {
        users.add(new User(u, e, n, p, t));
    }

    public void Register(String u, String e, String n, String p, String f, int t) {
        users.add(new User(u, e, n, p, f, t));

    }

    public void AddPriority(String u, String n, int i) { getUser(u).priorities.add(new Priority(n, i)); }

    public void AddTask(String u, String s, Priority p) { getUser(u).tasks.add(new Task(s, p)); }

    public int isUser(String n) {
        for (User u:users) {
            if (u.getUsername().equals(n)) {
                return 1;
            }
            if (u.getEmail().equals(n)) {
                return 2;
            }
        }
        return 0;
    }

    public User getUser(String n) {
        for (User u :
                users) {
            if (u.getEmail().equals(n) || u.getUsername().equals(n)) {
                return u;
            }
        }
        return null;
    }

    public boolean login(String ne, String p) {
        if (isUser(ne) > 0) {
            User u = getUser(ne);
            if (p.equals(u.getPassword().toString())) {
                u.loggedIn = true;
                System.out.println("logged in!");
            } else {
                System.out.println("wrong pass!");
            }
            return u.loggedIn;
        } else {
            System.out.println("this user is not registered!");
            return false;
        }
    }
}
