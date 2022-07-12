package com.test.interviewer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TechnologyTest {
    static String existingTechnologyName = "First Technology";
    static String existingTechnologySlug = "FTSlug";
    static String existingTechnologyDescription = "Example of Technology";

    @BeforeEach
    public void setUp() throws Exception {
        Technology.data = new ArrayList<>();

        // We insert a user for testing delete and save
        Technology.data.add(new Technology(
                existingTechnologyName,
                existingTechnologySlug,
                existingTechnologyDescription
        ));
    }

    @Test
    public void add() {
        Technology tech = new Technology(
                "Any Technology",
                "ATSlug",
                "Any Technology"
        );

        tech.add();

        int expectedId = Technology.data.size();
        assertEquals(
                expectedId,
                tech.id,
                "Technology ID should be the new List's size"
        );
    }


    @Test
    public void save() {
        int originalListSize = Technology.data.size();
        String expectedName = "New Technology Name";
        Technology existingTech = Technology.data.get(0);
        System.out.println(Technology.data.size());
        existingTech.save(expectedName, "", "");

        int newListSize = Technology.data.size();
        System.out.println(Technology.data.size());
        int lastTechIndex = newListSize - 1;
        Technology latestTech = Technology.data.get(lastTechIndex);

        assertEquals(
                originalListSize,
                newListSize,
                "List size should be the same"
        );
        assertEquals(
                expectedName,
                latestTech.name,
                "Name should have been updated"
        );
        assertEquals(
                existingTech.slug,
                latestTech.slug,
                "Slug should have not been updated"
        );
        assertEquals(
                existingTech.description,
                latestTech.description,
                "Description should have not been updated"
        );
    }

    @Test
    public void getByName() {
        Technology result = Technology.getByName(existingTechnologyName);

        assertNotNull(result, "Technology should be found");
        assertEquals(
                existingTechnologySlug,
                result.slug,
                "Unexpected Technology slug"
        );
        assertEquals(
                existingTechnologyDescription,
                result.description,
                "Unexpected Technology description"
        );
    }

    @Test
    public void getByNonExistingName() {
        String nonExistingName = "Non Tech";

        Technology result = Technology.getByName(nonExistingName);

        assertNull(result, "Technology should not be found");
    }

    @Test
    public void getById() {
        Technology result = Technology.getById(1);

        assertNotNull(result, "Technology should be found");
    }

    @Test
    public void delete() throws Exception {
        Technology result = Technology.getByName(existingTechnologyName);
        int expectedListSize = Technology.data.size() - 1;
        result.delete();
        int actualListSize = Technology.data.size();
        assertEquals(
                expectedListSize,
                actualListSize,
                "Interviewer should be deleted"
        );
    }
}