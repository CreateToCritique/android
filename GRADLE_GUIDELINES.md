# Gradle Build Environment & JDK Alignment Rules (CRITICAL)

This document serves as the authoritative guide for Gradle build configuration and JDK alignment in the InToto Wellness Android project.

---

## 1. Preventing Multiple Gradle Daemons & JDK Mismatch Errors

To prevent spawning multiple Gradle daemons and recurring JDK mismatch errors (`Multiple Gradle daemons might be spawned because the Gradle JDK and JAVA_HOME locations are different`), the following configuration rules must **never** be altered:

1. **Gradle JDK Location ([`.idea/gradle.xml`](file:///C:/Users/mellwynjoseph/Desktop/intoto-app/www/android/.idea/gradle.xml))**:
   - Must explicitly point to Android Studio's project JDK: `jbr-21` (`C:/Users/mellwynjoseph/.jdks/jbr-21.0.11`).
2. **Project-wide Gradle Properties ([`gradle.properties`](file:///C:/Users/mellwynjoseph/Desktop/intoto-app/www/android/gradle.properties))**:
   - Must maintain `org.gradle.java.home=C\:/Users/mellwynjoseph/.jdks/jbr-21.0.11`.
3. **Environment & IDE Sync**:
   - Both Android Studio's internal Gradle JVM setting (`.idea/gradle.xml`) and `gradle.properties` **must always be kept in exact alignment** pointing to `C:/Users/mellwynjoseph/.jdks/jbr-21.0.11` to prevent duplicate daemons and background sync warnings.
