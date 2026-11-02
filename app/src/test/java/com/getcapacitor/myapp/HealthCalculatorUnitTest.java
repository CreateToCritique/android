package com.getcapacitor.myapp;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Local unit tests for InToto Health Calculator formulas.
 */
public class HealthCalculatorUnitTest {

    @Test
    public void testBmiCalculation() {
        // BMI = weight_kg / (height_m * height_m)
        double weightKg = 70.0;
        double heightM = 1.72;

        double bmi = weightKg / (heightM * heightM);
        assertEquals(23.66, bmi, 0.01);
        
        // Category classification
        String category = (bmi < 18.5) ? "Underweight" : (bmi < 25.0) ? "Normal" : "Overweight";
        assertEquals("Normal", category);
    }

    @Test
    public void testBmrCalculation() {
        // Male Mifflin-St Jeor Equation: (10 * weight_kg) + (6.25 * height_cm) - (5 * age) + 5
        double weightKg = 70.0;
        double heightCm = 172.0;
        int age = 32;

        double maleBmr = (10 * weightKg) + (6.25 * heightCm) - (5 * age) + 5;
        assertEquals(1620.0, maleBmr, 0.01);
    }

    @Test
    public void testFemaleBmrCalculation() {
        // Female Mifflin-St Jeor Equation: (10 * weight_kg) + (6.25 * height_cm) - (5 * age) - 161
        double weightKg = 60.0;
        double heightCm = 165.0;
        int age = 28;

        double femaleBmr = (10 * weightKg) + (6.25 * heightCm) - (5 * age) - 161;
        assertEquals(1330.25, femaleBmr, 0.01);
    }

    @Test
    public void testTdeeCalculation() {
        double bmr = 1620.0;
        double activityMultiplier = 1.55; // Moderate activity

        double tdee = bmr * activityMultiplier;
        assertEquals(2511.0, tdee, 0.01);
    }

    @Test
    public void testTdeeActivityMultipliers() {
        double bmr = 1500.0;
        
        double sedentaryTdee = bmr * 1.2;
        assertEquals(1800.0, sedentaryTdee, 0.01);

        double activeTdee = bmr * 1.725;
        assertEquals(2587.5, activeTdee, 0.01);
    }

    @Test
    public void testHealthScoreRatingBounds() {
        double medsRatio = 1.0;
        double sleepScore = 0.85;
        double moodScore = 0.8;

        int healthScore = (int) Math.round((medsRatio * 40) + (sleepScore * 30) + (moodScore * 30));
        assertTrue("Health score must be between 0 and 100", healthScore >= 0 && healthScore <= 100);
        assertEquals(90, healthScore);
    }

    @Test
    public void testMenstrualPhaseDayCalculation() {
        int cycleDay = 14; // Day 14 of 28-day cycle
        String phase = (cycleDay >= 12 && cycleDay <= 16) ? "Ovulatory Window" : "Follicular Phase";
        assertEquals("Ovulatory Window", phase);
    }
}
