package com.test.interviewer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class InterviewTest {
    static Candidate existingCandidate;
    static Interviewer existingInterviewer;
    static InterviewType existingType;
    static Technology existingTechnology;
    static Discipline existingDiscipline;

    @BeforeEach
    public void setUp() throws Exception {
        Interview.data = new ArrayList<>();

        // We insert a user for testing delete and save
        Interview.data.add(new Interview(
                existingCandidate,
                existingInterviewer,
                existingType,
                existingTechnology,
                existingDiscipline
        ));
    }


    @Test
    public void getById() {
        Interview result = Interview.getById(0);

        assertNotNull(result, "Interview should be found");
    }
}