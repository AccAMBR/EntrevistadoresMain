package com.test.interviewer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class CandidateTest {
    static String existingCandidateName = "First";
    static String existingCandidateLastName = "User";
    static String existingCandidateEmail = "first@email.com";

    @BeforeEach
    public void setUp() throws Exception {
        Candidate.data = new ArrayList<>();

        // We insert a user for testing delete and save
        Candidate.data.add(new Candidate(
                existingCandidateName,
                existingCandidateLastName,
                existingCandidateEmail,
                true
        ));
    }

    @Test
    public void add() {
        Candidate candidate = new Candidate(
                "Test",
                "User",
                "any@email.com",
                true
        );

        candidate.add();

        int expectedId = Candidate.data.size();
        assertEquals(
                expectedId,
                candidate.id,
                "Candidate ID should be the new List's size"
        );
    }


    @Test
    public void save() {
        int originalListSize = Candidate.data.size();
        String expectedLastName = "New";
        Candidate existingCandidate = Candidate.data.get(0);
        System.out.println(Candidate.data.size());
        existingCandidate.save("", expectedLastName, "", true);

        int newListSize = Candidate.data.size();
        System.out.println(Candidate.data.size());
        int lastCandidateIndex = newListSize - 1;
        Candidate latestCandidate = Candidate.data.get(lastCandidateIndex);

        assertEquals(
                originalListSize,
                newListSize,
                "List size should be the same"
        );
        assertEquals(
                expectedLastName,
                latestCandidate.lastName,
                "Last Name should have been updated"
        );
        assertEquals(
                existingCandidate.name,
                latestCandidate.name,
                "Name should have not been updated"
        );
    }

    @Test
    public void getByEmail() {
        Candidate result = Candidate.getByEmail(existingCandidateEmail);

        assertNotNull(result, "Candidate should be found");
        assertEquals(
                existingCandidateName,
                result.name,
                "Unexpected Candidate Name"
        );
        assertEquals(
                existingCandidateLastName,
                result.lastName,
                "Unexpected Candidate Last Name"
        );
    }

    @Test
    public void getByNonExistingEmail() {
        String nonExistingEmail = "nonexisting@email.com";

        Candidate result = Candidate.getByEmail(nonExistingEmail);

        assertNull(result, "Candidate should not be found");
    }

    @Test
    public void getById() {
        Candidate result = Candidate.getById(1);

        assertNotNull(result, "Candidate should be found");
    }

    @Test
    public void delete() throws Exception {
        Candidate result = Candidate.getByEmail(existingCandidateEmail);
        int expectedListSize = Candidate.data.size() - 1;
        result.delete();
        int actualListSize = Candidate.data.size();
        assertEquals(
                expectedListSize,
                actualListSize,
                "Candidate should be deleted"
        );
    }
}