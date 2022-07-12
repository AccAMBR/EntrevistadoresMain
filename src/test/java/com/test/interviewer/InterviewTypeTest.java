package com.test.interviewer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class InterviewTypeTest {
    static String existingInterviewTypeName = "First Interview Type";
    static String existingInterviewTypeSlug = "FITSlug";
    static String existingInterviewTypeDescription = "Example of Interview Type";

    @BeforeEach
    public void setUp() throws Exception {
        InterviewType.data = new ArrayList<>();

        // We insert a user for testing delete and save
        InterviewType.data.add(new InterviewType(
                existingInterviewTypeName,
                existingInterviewTypeSlug,
                existingInterviewTypeDescription
        ));
    }

    @Test
    public void add() {
        InterviewType type = new InterviewType(
                "Any Interview Type",
                "AITSlug",
                "Any type of Interview Type"
        );

        type.add();

        int expectedId = InterviewType.data.size();
        assertEquals(
                expectedId,
                type.id,
                "InterviewType ID should be the new List's size"
        );
    }


    @Test
    public void save() {
        int originalListSize = InterviewType.data.size();
        String expectedName = "New Interview Type Name";
        InterviewType existingType = InterviewType.data.get(0);
        System.out.println(InterviewType.data.size());
        existingType.save(expectedName, "", "");

        int newListSize = InterviewType.data.size();
        System.out.println(InterviewType.data.size());
        int lastTypeIndex = newListSize - 1;
        InterviewType latestType = InterviewType.data.get(lastTypeIndex);

        assertEquals(
                originalListSize,
                newListSize,
                "List size should be the same"
        );
        assertEquals(
                expectedName,
                latestType.name,
                "Name should have been updated"
        );
        assertEquals(
                existingType.slug,
                latestType.slug,
                "Slug should have not been updated"
        );
        assertEquals(
                existingType.description,
                latestType.description,
                "Description should have not been updated"
        );
    }

    @Test
    public void getByName() {
        InterviewType result = InterviewType.getByName(existingInterviewTypeName);

        assertNotNull(result, "InterviewType should be found");
        assertEquals(
                existingInterviewTypeSlug,
                result.slug,
                "Unexpected Interview Type slug"
        );
        assertEquals(
                existingInterviewTypeDescription,
                result.description,
                "Unexpected Interview Type description"
        );
    }

    @Test
    public void getByNonExistingName() {
        String nonExistingName = "Non Type";

        InterviewType result = InterviewType.getByName(nonExistingName);

        assertNull(result, "Interview Type should not be found");
    }

    @Test
    public void getById() {
        InterviewType result = InterviewType.getById(1);

        assertNotNull(result, "InterviewType should be found");
    }

    @Test
    public void delete() throws Exception {
        InterviewType result = InterviewType.getByName(existingInterviewTypeName);
        int expectedListSize = InterviewType.data.size() - 1;
        result.delete();
        int actualListSize = InterviewType.data.size();
        assertEquals(
                expectedListSize,
                actualListSize,
                "InterviewType should be deleted"
        );
    }
}