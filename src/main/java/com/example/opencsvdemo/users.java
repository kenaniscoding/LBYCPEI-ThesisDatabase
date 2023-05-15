package com.example.opencsvdemo;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class users {
    public ArrayList<User> accounts;

    public users(){accounts = new ArrayList<>();}

    public void loadUsers(String filename){
        try {

            BufferedReader fileReader = new BufferedReader(new FileReader(filename));
            Scanner text = new Scanner(fileReader);
            String[] userInfo;

            while (text.hasNext()) {
                String line = text.nextLine();
                System.out.println(line);
                System.out.println("hi");
                userInfo = line.split(",");
                String Admin = userInfo[2];
                User acc = new User(userInfo[0], userInfo[1], Admin);
                accounts.add(acc);

            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not Exist");
        }
    }

    public User getUser(int x){
        return accounts.get(x);
    }
    public int getSize(){
        return accounts.size();
    }
}
