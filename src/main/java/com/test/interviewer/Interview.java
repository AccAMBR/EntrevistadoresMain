package com.test.interviewer;

import java.io.*;
import java.util.ArrayList;

public class Interview  implements Serializable {
    static ArrayList<Interview> data;

    int id;
    Candidate candidate;
    Interviewer interviewer;
    InterviewType type;
    Technology technology;
    Discipline discipline;

    public Interview(
            Candidate candidate,
            Interviewer interviewer,
            InterviewType type,
            Technology technology,
            Discipline discipline
    ) {
        this.id = data.size() + 1;
        this.candidate = candidate;
        this.interviewer = interviewer;
        this.type = type;
        this.technology = technology;
        this.discipline = discipline;
    }

    public Interview add() {
        data.add(this);
        Interview.saveDataToFile();
        return this;
    }

    public void delete() throws Exception{
        Interview interview = Interview.getById(this.id);

        if (interview != null) {
            data.remove(this);
            Interview.saveDataToFile();
        }
        else
            throw new Exception("Interview not found");
    }

    public void save(
            Candidate candidate,
            Interviewer interviewer,
            InterviewType type,
            Technology technology,
            Discipline discipline
    ) {
        try {
            this.delete();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        this.candidate = candidate;
        this.interviewer = interviewer;
        this.type = type;
        this.technology = technology;
        this.discipline = discipline;

        data.add(this);
    }

    public static Interview getById(int id) {
        for (Interview interview: data) {
            if (interview.id == id)
                return interview;
        }

        return null;
    }

    @Override
    public String toString() {
        return "\nID: " + this.id +
                "\nCandidate: " + this.candidate +
                "\nInterviewer: " + this.interviewer +
                "\nType: " + this.type +
                "\nTechnology: " + this.technology +
                "\nDiscipline: " + this.discipline + "\n";
    }

    public static void saveDataToFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./interviews");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

            outputStream.writeObject(Interview.data);

            outputStream.close();
            fileOutputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void loadDataFromFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("./interviews");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

            ArrayList<Interview> fileData = (ArrayList<Interview>) inputStream.readObject();

            Interview.data.clear();
            Interview.data.addAll(fileData);

            inputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            if (!e.getMessage().contains("No such file or directory"))
                e.printStackTrace();
        }
    }
}
