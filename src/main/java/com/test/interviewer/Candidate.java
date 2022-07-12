package com.test.interviewer;

import java.io.*;
import java.util.ArrayList;

public class Candidate implements Serializable {
    static ArrayList<Candidate> data;

    int id;
    String name;
    String lastName;
    String email;
    Boolean isActive;

    public Candidate(
            String name,
            String lastName,
            String email,
            Boolean isActive
    ) {
        this.id = data.size() + 1;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.isActive = isActive;
    }

    public Candidate add() {
        data.add(this);
        Candidate.saveDataToFile();
        return this;
    }

    public void delete() throws Exception{
        Candidate candidate = Candidate.getByEmail(this.email);

        if (candidate != null) {
            data.remove(this);
            Candidate.saveDataToFile();
        }
        else
            throw new Exception("Candidate not found");
    }

    public void save(
            String name,
            String lastName,
            String email,
            Boolean isActive
    ) {
        try {
            this.delete();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        if (!name.equals(""))
            this.name = name;

        if (!lastName.equals(""))
            this.lastName = lastName;

        if (!email.equals(""))
            this.email = email;

        this.isActive = isActive;

        data.add(this);
    }

    public static Candidate getByEmail(String email) {
        for (Candidate candidate: data) {
            if (candidate.email.equals(email))
                return candidate;
        }

        return null;
    }

    public static Candidate getById(int id) {
        for (Candidate candidate: data) {
            if (candidate.id == id)
                return candidate;
        }

        return null;
    }

    @Override
    public String toString() {
        return "\nID: " + this.id +
                "\nName: " + this.name +
                "\nLast Name: " + this.lastName +
                "\nEmail: " + this.email +
                "\nIs Active: " + this.isActive + "\n";
    }

    public static void saveDataToFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./candidates");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

            outputStream.writeObject(Candidate.data);

            outputStream.close();
            fileOutputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void loadDataFromFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("./candidates");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

            ArrayList<Candidate> fileData = (ArrayList<Candidate>) inputStream.readObject();

            Candidate.data.clear();
            Candidate.data.addAll(fileData);

            inputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            if (!e.getMessage().contains("No such file or directory"))
                e.printStackTrace();
        }
    }
}