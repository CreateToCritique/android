package com.getcapacitor.myapp;

import static org.junit.Assert.*;

import android.content.Context;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test for InToto Capacitor Activity & Package Context.
 */
@RunWith(AndroidJUnit4.class)
public class InTotoActivityTest {

    @Test
    public void testAppContextAndPackageName() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertNotNull("App context must not be null", appContext);
        assertTrue("Package name must contain getcapacitor app", appContext.getPackageName().contains("com.getcapacitor"));
    }
}
