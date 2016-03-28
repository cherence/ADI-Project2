package com.example.cher.cherproject2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void testTypeIsCorrect() {
        Attraction attraction = new Attraction("Food", "Three Broomsticks", "Hogsmeade", "not favorite", 10, 789, 456, 123);

        String expected = "Food";
        String actual = attraction.getmType();

        assertEquals(expected, actual);
    }

    @Test
    public void testNameIsCorrect() {
        Attraction attraction = new Attraction("Food", "Three Broomsticks", "Hogsmeade", "not favorite", 10, 789, 456, 123);

        String expected = "Three Broomsticks";
        String actual = attraction.getmName();

        assertEquals(expected, actual);

    }

    @Test
    public void testGeneralLocationIsCorrect() {
        Attraction attraction = new Attraction("Food", "Three Broomsticks", "Hogsmeade", "not favorite", 10, 789, 456, 123);

        String expected = "Hogsmeade";
        String actual = attraction.getmGeneralLocation();

        assertEquals(expected, actual);

    }

    @Test
    public void testFavoriteStatusIsCorrect() {
        Attraction attraction = new Attraction("Food", "Three Broomsticks", "Hogsmeade", "not favorite", 10, 789, 456, 123);

        String expected = "not favorite";
        String actual = attraction.getFavoriteStatus();

        assertEquals(expected, actual);

    }

    @Test
    public void testInformationIsCorrect() {
        Attraction attraction = new Attraction("Food", "Three Broomsticks", "Hogsmeade", "not favorite", 10, 789, 456, 123);

        int expected = 10;
        int actual = attraction.getmInformation();

        assertEquals(expected, actual);

    }

    @Test
    public void testHeaderImageRIdIsCorrect() {
        Attraction attraction = new Attraction("Food", "Three Broomsticks", "Hogsmeade", "not favorite", 10, 789, 456, 123);

        int expected = 456;
        int actual = attraction.getmHeaderImageRId();

        assertEquals(expected, actual);

    }

    @Test
    public void testMapImageRIdIsCorrect() {
        Attraction attraction = new Attraction("Food", "Three Broomsticks", "Hogsmeade", "not favorite", 10, 789, 456, 123);

        int expected = 123;
        int actual = attraction.getmMapImageRId();

        assertEquals(expected, actual);

    }
}