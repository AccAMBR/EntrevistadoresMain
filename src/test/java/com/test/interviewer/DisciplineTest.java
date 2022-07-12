package com.test.interviewer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DisciplineTest {
    static String existingDisciplineName = "First Discipline";
    static String existingDisciplineSlug = "FDSlug";
    static String existingDisciplineDescription = "Example of Discipline";

    @BeforeEach
    public void setUp() throws Exception {
        Discipline.data = new ArrayList<>();

        // We insert a user for testing delete and save
        Discipline.data.add(new Discipline(
                existingDisciplineName,
                existingDisciplineSlug,
                existingDisciplineDescription
        ));
    }

    @Test
    public void add() {
        Discipline discipline = new Discipline(
                "Any Discipline",
                "ADSlug",
                "Any Discipline"
        );

        discipline.add();

        int expectedId = Discipline.data.size();
        assertEquals(
                expectedId,
                discipline.id,
                "Discipline ID should be the new List's size"
        );
    }


    @Test
    public void save() {
        int originalListSize = Discipline.data.size();
        String expectedName = "New Discipline Name";
        Discipline existingDiscipline = Discipline.data.get(0);
        System.out.println(Discipline.data.size());
        existingDiscipline.save(expectedName, "", "");

        int newListSize = Discipline.data.size();
        System.out.println(Discipline.data.size());
        int lastDisciplineIndex = newListSize - 1;
        Discipline latestDiscipline = Discipline.data.get(lastDisciplineIndex);

        assertEquals(
                originalListSize,
                newListSize,
                "List size should be the same"
        );
        assertEquals(
                expectedName,
                latestDiscipline.name,
                "Name should have been updated"
        );
        assertEquals(
                existingDiscipline.slug,
                latestDiscipline.slug,
                "Slug should have not been updated"
        );
        assertEquals(
                existingDiscipline.description,
                latestDiscipline.description,
                "Description should have not been updated"
        );
    }

    @Test
    public void getByName() {
        Discipline result = Discipline.getByName(existingDisciplineName);

        assertNotNull(result, "Discipline should be found");
        assertEquals(
                existingDisciplineSlug,
                result.slug,
                "Unexpected Discipline slug"
        );
        assertEquals(
                existingDisciplineDescription,
                result.description,
                "Unexpected Discipline description"
        );
    }

    @Test
    public void getByNonExistingName() {
        String nonExistingName = "Non Discipline";

        Discipline result = Discipline.getByName(nonExistingName);

        assertNull(result, "Discipline should not be found");
    }

    @Test
    public void getById() {
        Discipline result = Discipline.getById(1);

        assertNotNull(result, "Discipline should be found");
    }

    @Test
    public void delete() throws Exception {
        Discipline result = Discipline.getByName(existingDisciplineName);
        int expectedListSize = Discipline.data.size() - 1;
        result.delete();
        int actualListSize = Discipline.data.size();
        assertEquals(
                expectedListSize,
                actualListSize,
                "Discipline should be deleted"
        );
    }
}