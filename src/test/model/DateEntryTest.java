package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DateEntryTest {

    private DateEntry testDate;
    private ArrayList<String> comparisonList;

    @BeforeEach
    void runBefore() {
        testDate = new DateEntry("Jay", 1);
        comparisonList = new ArrayList<>();
    }

    @Test
    void testDateEntryConstructor() {
        assertEquals("Jay", testDate.getDateName());
        assertEquals(1, testDate.getDateNumber());
        assertFalse(testDate.isDateSuccessful());
        assertEquals(new ArrayList<>(), testDate.getDateHighLightEvents());
        assertEquals(new ArrayList<>(), testDate.getDateRedFlagEvents());
    }

    @Test
    void isDateSuccessful() {
        testDate.setSuccessfulness(true);
        assertTrue(testDate.isDateSuccessful());
    }

    @Test
     void testAddOneHighlightEvent() {
        testDate.addHighlightEvent("sss");
        comparisonList.add("sss");
        assertEquals(comparisonList, testDate.getDateHighLightEvents());
    }

    @Test
    void testAddMoreHighlightEvent() {
        testDate.addHighlightEvent("sss");
        testDate.addHighlightEvent("hello");
        comparisonList.add("sss");
        comparisonList.add("hello");
        assertEquals(comparisonList, testDate.getDateHighLightEvents());
    }

    @Test
    void testAddOneRedFlagEvent() {
        testDate.addRedFlagEvent("sss");
        comparisonList.add("sss");

        assertEquals(comparisonList, testDate.getDateRedFlagEvents());
    }

    @Test
    void testAddMoreRedFlagEvent() {
        testDate.addRedFlagEvent("sss");
        testDate.addRedFlagEvent("hello");
        comparisonList.add("sss");
        comparisonList.add("hello");

        assertEquals(comparisonList, testDate.getDateRedFlagEvents());
    }

    @Test
    void testRemoveHighlightEventSuccessfully() {
        testDate.addHighlightEvent("sss");
        testDate.addHighlightEvent("hello");
        testDate.addHighlightEvent("yo");
        testDate.removeHighlightEvent(2);

        assertEquals(2,testDate.getDateHighLightEvents().size());
        assertEquals("sss", testDate.getDateHighLightEvents().get(0));
        assertEquals("yo", testDate.getDateHighLightEvents().get(1));
    }

    @Test
    void testCantRemoveHighlightEvent() {
        testDate.addHighlightEvent("sss");
        testDate.addHighlightEvent("hello");
        testDate.removeHighlightEvent(3);

        assertEquals(2,testDate.getDateHighLightEvents().size());
    }
    @Test
    void testRemoveRedFlagEventSuccessfully() {
        testDate.addRedFlagEvent("sss");
        testDate.addRedFlagEvent("hello");
        testDate.addRedFlagEvent("yo");
        testDate.removeRedFlagEvent(2);

        assertEquals(2,testDate.getDateRedFlagEvents().size());
        assertEquals("sss", testDate.getDateRedFlagEvents().get(0));
        assertEquals("yo", testDate.getDateRedFlagEvents().get(1));
    }

    @Test
    void testCantRemoveRedFlagEvent() {
        testDate.addRedFlagEvent("sss");
        testDate.addRedFlagEvent("hello");
        testDate.removeRedFlagEvent(3);

        assertEquals(2,testDate.getDateRedFlagEvents().size());
    }
}