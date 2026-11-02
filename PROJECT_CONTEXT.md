# InToto Wellness - Application Context & Architecture Guide

> **Important for AI Agents**: Read this document at the start of any new session to obtain complete, immediate context on the codebase architecture, file layout, data schemas, UI design system, automated test suites, and coding conventions.

---

## 1. Executive Summary & Vision

**InToto** (`com.intoto.wellness`) is a local-first, privacy-focused 360° health and wellness tracker for Android and Web. Unlike siloed fitness apps, InToto combines:
- **Physical Biometrics & Body Composition**
- **Medical ID & Safety Emergency Card**
- **Cardiovascular & Clinical Vitals**
- **Clinical Records & Blood Panel Labs**
- **Menstrual & Reproductive Health Phase Prediction**
- **Sleep Schedule & Circadian Chronotype**
- **Mental Wellbeing & Guided Mindfulness Routines**
- **Acts of Love & Care / Relational Wellness**
- **Tracked Distractions & Screen Time**
- **Meal & Grocery Planning**
- **Google Gemini AI Clinical Companion**
- **HL7 FHIR R4 Standard Export & Wearables Sync (Samsung Health, Apple Watch, Fitbit, Garmin, Oura, Whoop)**

---

## 2. Core Architecture & Tech Stack

| Layer | Technology / Library | Purpose |
| :--- | :--- | :--- |
| **Mobile Runtime** | Capacitor 6 / Android WebView | Android hybrid container (`com.intoto.wellness`). |
| **UI Framework** | Vanilla JS (ES6+) + Tailwind CSS v3 | Fast, zero-overhead declarative web UI with custom skeuomorphic design system. |
| **Design System SSOT** | CSS Variables + Living Showcase | `design-tokens.css` + `design-system.html` + `DESIGN_SYSTEM.md` single source of truth. |
| **Content & Web Components**| `contentEngine.js` Registry | Single Source of Truth text provider (`InTotoContent`) & Native Web Components (`<intoto-category-badge>`, `<intoto-section-header>`, `<intoto-setting-card>`). |
| **Navigation Stack Engine** | `navEngine.js` Controller | Single Source of Truth navigation stack (`InTotoNav`) & hardware back button handling. |
| **Automated Testing Suite** | Node JS Runner + Gradle JVM Tests | 100 assertions across `jsUnitTestSuite.js` (92 assertions) and `HealthCalculatorUnitTest.java` (8 assertions). |
| **Charts & Data** | Chart.js v4 | Interactive weight trends and vitals performance charts. |
| **Date Selection** | Flatpickr v4 | Date range pickers for medication scheduling and daily logging. |
| **Icons** | FontAwesome 6 Free | Rich UI icon set across all 8 health pillars and navigation bars. |
| **AI Companion** | Google Gemini 1.5 Flash API | Free-tier BYOK (Bring Your Own Key) for AI insights, coaching, and calorie estimation. |
| **Cloud Sync** | Google Drive REST API | Encrypted local-first JSON backup/restore into user's private Google Drive. |
| **Interoperability** | HL7 FHIR R4 + Health Connect API | Standardized medical JSON bundle export and wearable tracker integration. |

---

## 3. CRITICAL: Dual-Directory Mirroring Rule

The application codebase uses a dual-directory structure for web development and Capacitor Android build assets:

- **Web Source Root**: `C:/Users/mellwynjoseph/Desktop/intoto-app/www/`
  - JavaScript files: `www/js/`, `www/js/views/`, `www/js/modules/`, `www/js/tests/`
  - HTML & CSS: `www/index.html`, `www/css/app.css`, `www/css/design-tokens.css`, `www/design-system.html`
- **Android App Assets**: `C:/Users/mellwynjoseph/Desktop/intoto-app/www/android/app/src/main/assets/public/`
  - Mirrored JavaScript files: `assets/public/js/`, `assets/public/js/views/`, `assets/public/js/modules/`

> [!CAUTION]
> **AI Agent Requirement**: Whenever modifying JavaScript or CSS files (`config.js`, `app.js`, `settingsViews.js`, `contentEngine.js`, `navEngine.js`, `demoData.js`, `app.css`, etc.), you **MUST update both** `www/` AND `www/android/app/src/main/assets/public/` to prevent desync between browser preview and Android APK builds!

---

## 4. Complete File Directory & Module Map

| File Path | Description & Primary Responsibilities |
| :--- | :--- |
| `index.html` | Application HTML shell, header bar, streak indicator, daily score form, and modal mount points. |
| `design-system.html` | **Living Interactive Component Library Showcase**: Real-time QA testbed for all 16 theme states and Web Components. |
| `css/design-tokens.css` | **Executable SSOT Design Token Engine**: CSS custom properties for 16 theme states across 8 World Palette Families. |
| `css/app.css` | Application stylesheet containing Tailwind overrides and skeuomorphic panel/button/input styles. |
| `android/DESIGN_SYSTEM.md` | **Master Design System Blueprint**: Single source of truth specification, component taxonomy, and tone guidelines. |
| `.github/copilot-instructions.md` | **AI Agent System Instructions**: Non-negotiable coding standards and mirroring directives. |
| `js/modules/contentEngine.js` | **Single Source of Truth Text Registry (`InTotoContent`)**: Native Web Components (`<intoto-category-badge>`, `<intoto-section-header>`, `<intoto-setting-card>`). |
| `js/modules/navEngine.js` | **Unified Navigation Stack Engine (`InTotoNav`)**: Settings routing, subview stack management, and hardware back button handling. |
| `js/tests/jsUnitTestSuite.js` | **Automated JavaScript Unit & Web Component Test Runner**: 92 assertions across 15 test groups. |
| `js/config.js` | Global state initialization (`logs`, `profile`, `prescriptions`, `masterActivities`), metabolic calculators (**BMI**, **BMR**, **TDEE**, **Menstrual Phase**), strict input validators, and **HL7 FHIR R4 Exporter**. |
| `js/app.js` | Main app controller: tab switching, daily score saving (`saveDailyLog`), profile harvesting (`saveProfileSilent`), summary chips (`updateProfileSummaryChips`), lab result handlers, emergency contact handlers, and wearable sync. |
| `js/views/settingsViews.js` | Renders the **Profile & Settings Hub**, menu cards, subview forms, metabolic profile diagnostic display, cycle predictor display, emergency contacts, lab history, and theme studio. |
| `js/views/dataPageViews.js` | Data & Calendar matrix modal, Chart.js weight/vitals graphs, log tables, and import/export tools. |
| `js/views/modalViews.js` | Custom modal dialogs, PIN keypad lock, ad-hoc logging modal, and custom guide builder. |
| `js/modules/ai.js` | Google Gemini 1.5 Flash API connector: `askAiCoach()`, `generateAiInsights()`, `estimateCaloriesAI()`. |
| `js/modules/demoData.js` | **1-Click Comprehensive Demo Suite Generator**: `generate30DayDemoData()` seeds 30 days of logs, 6 months of routines, grocery lists, and the complete 8-Pillar Profile dataset into `localStorage`. |
| `js/modules/meals.js` | Weekly 7-day meal plan grid, recipe catalog, and supermarket grocery list compiler. |
| `js/modules/guided.js` | Step-by-step interactive routine runners (Box Breathing, Surya Namaskar, Desk Reset, Gratitude Anchor, Memory Mini-Game). |
| `js/modules/streak.js` | Daily streak calculation and holistic health score algorithm. |
| `js/modules/progressEngine.js` | **Modular 8-Pillar Completion Progress Engine (`InTotoProgress`)**: Real-time daily progress bar calculation, instant header fill, theme-adaptive gradients, and local notification summary integration. |
| `js/modules/security.js` | Biometric lock settings, 4-digit PIN fallback, theme switching, and Google Drive cloud backup/restore. |
| `js/modules/notifications.js` | Local notification schedule manager for medication and wellness reminders. |

---

## 5. Local Storage Data Schema Map (`localStorage`)

All application state is persisted on-device in `localStorage`:

| Key Name | Data Structure | Purpose |
| :--- | :--- | :--- |
| `intoto_profile` | JSON Object (8 Pillars) | Complete user health profile, biometrics, Medical ID, vitals, cycle data, sleep targets, and wearable settings. |
| `intoto_logs` | JSON Array of Objects | Historical daily score logs (`weight`, `sleepHours`, `sleepQuality`, `stress`, `mood`, `food`, `calories`, `medsTakenDetails`). |
| `intoto_prescriptions` | JSON Array of Objects | Active prescription routines (`name`, `dosage`, `times`, `meal`, `days`, `startDate`, `endDate`). |
| `intoto_sched_ex` / `love` / `mental` | JSON Array of Objects | Active exercise, relational, and mental wellness routine schedules. |
| `intoto_master_activities_v2` | JSON Array of Objects | Master library of activity categories with embedded step-by-step guide routines. |
| `intoto_weekly_meal_plan` | JSON Object | Weekly grid mapping days (`Su` to `S`) and meal slots (`Breakfast`, `Lunch`, `Dinner`, `Snacks`) to recipe IDs. |
| `intoto_grocery_list` | JSON Array of Objects | Compiled supermarket grocery checklist items (`name`, `checked`). |
| `intoto_streak` | JSON Object | `currentStreak`, `longestStreak`, `lastLoggedDate`, `healthScore`. |
| `intoto_gemini_key` | String | User's free-tier Google Gemini API Key from Google AI Studio. |
| `intoto_pin` | String | Encrypted 4-digit security fallback PIN. |

---

## 6. Single Source of Truth Design System (v3.0.0)

InToto uses a biophilic design system guided by **Frameless Content Resting** and **Selective Skeuomorphism**:

- **Core Rules**:
  - **Frameless Content Resting**: Text and section rows rest directly on the page background surface with clean bottom dividers (`border-b border-[#12402c]/30`).
  - **Selective Skeuomorphism**: 3D bevels and tactile controls are reserved strictly for interactive elements (`.skeuo-button`, `.skeuo-input`, `.skeuo-switch`, `.segmented-chip`).
  - **Symmetrical 16-Theme Family Engine**: 8 World Palette Families in Light and Dark Mode in `design-tokens.css`.
- **Design System References**:
  - Master Specification Blueprint: [`www/android/DESIGN_SYSTEM.md`](file:///C:/Users/mellwynjoseph/Desktop/intoto-app/www/android/DESIGN_SYSTEM.md)
  - Centralized Content Provider: [`www/js/modules/contentEngine.js`](file:///C:/Users/mellwynjoseph/Desktop/intoto-app/www/js/modules/contentEngine.js)
  - Executable CSS Engine: [`www/css/design-tokens.css`](file:///C:/Users/mellwynjoseph/Desktop/intoto-app/www/css/design-tokens.css)
  - Living Component Showcase: [`www/design-system.html`](file:///C:/Users/mellwynjoseph/Desktop/intoto-app/www/design-system.html)

---

## 7. Coding Conventions & Safety Guardrails

- **XSS Prevention**: Always sanitize user-provided strings before rendering in `innerHTML` using `escapeHtml(str)`.
- **Defensive JSON Parsing**: Always parse `localStorage` using `safeJsonParse(key, defaultValue)`.
- **Input Validation**: Use `validateNumericRange(val, min, max, defaultVal)` and `validateTimeString(timeStr)` to validate numeric inputs and time formats.
- **Haptic Triggers**: Call `triggerHaptic('light'|'medium'|'heavy')` on interactive button clicks and form submissions.
- **AI Context Ingestion**: When calling `askAiCoach()`, pass `JSON.stringify(profile)` to provide full 8-pillar biometric and clinical context to Gemini AI.

---

## 8. Comprehensive Automated Testing Architecture & Verification

The application maintains a dual-layer automated testing architecture:

### A. JavaScript Application Unit & Component Test Suite
- **Test Suite Location**: [`www/js/tests/jsUnitTestSuite.js`](file:///C:/Users/mellwynjoseph/Desktop/intoto-app/www/js/tests/jsUnitTestSuite.js)
- **Execution Command**: `node www/js/tests/jsUnitTestSuite.js`
- **Total Assertions**: **92 Passed, 0 Failed (100% Pass Rate across 15 Test Groups)**
- **Coverage Summary**:
  1. `InTotoContent` Registry & Web Components (`<intoto-category-badge>`, `<intoto-section-header>`, `<intoto-setting-card>`)
  2. `InTotoNav` Stack Operations & Settings Router (push, pop, duplicate prevention, hub routing)
  3. Multi-Theme Controller & `localStorage` Persistence (all 16 theme states)
  4. Daily Log & Numeric Stepper Math (`adjustWeight`, `adjustSleep` bounds)
  5. Input Validators & Defensive Parsing (`validateNumericRange`, `validateTimeString`)
  6. Meal Planner & Supermarket Grocery List Compiler
  7. HL7 FHIR R4 Clinical Exporter (`exportFHIRHealthRecord`)
  8. Backup JSON Schema Validation
  9. Demo Suite Data Generator & Snapshot Exporter (`generate30DayDemoData`, `clearAllDemoData`)
  10. Streak & Holistic Health Score Algorithm
  11. Guided Routine Runner
  12. Defensive `localStorage` Parsing (`safeJsonParse` corrupted JSON recovery)
  13. Web Component DOM Mounting
  14. CSS Theme Variable Engine & 16-Palette Tokens (`design-tokens.css` validation)
  15. Storage Schema Migration (`formatLocalDate` & legacy key conversion)

### B. Android JVM Java Unit Test Suite
- **Test Suite Location**: [`www/android/app/src/test/java/com/getcapacitor/myapp/HealthCalculatorUnitTest.java`](file:///C:/Users/mellwynjoseph/Desktop/intoto-app/www/android/app/src/test/java/com/getcapacitor/myapp/HealthCalculatorUnitTest.java)
- **Execution Command**: `gradle_build(":app:testDebugUnitTest")`
- **Total Assertions**: **8 Passed, 0 Failed (100% Pass Rate)**
- **Coverage Summary**:
  - `testBmiCalculation()`: Body Mass Index math ($kg/m^2$) & category classification
  - `testBmrCalculation()`: Male Mifflin-St Jeor equation
  - `testFemaleBmrCalculation()`: Female Mifflin-St Jeor equation
  - `testTdeeCalculation()`: Moderate activity TDEE math
  - `testTdeeActivityMultipliers()`: Sedentary ($1.2$) vs Active ($1.725$) bounds
  - `testHealthScoreRatingBounds()`: Holistic health score range validation ($0 - 100$)
  - `testMenstrualPhaseDayCalculation()`: Cycle phase prediction math

---

## 9. AI Agent System Instructions & Coding Standards

All AI Agents assisting with this codebase must strictly observe these rules:
1. **Dual-Directory Mirroring (CRITICAL)**: Always mirror changes between `www/` and `www/android/app/src/main/assets/public/` byte-for-byte.
2. **Single Source of Truth Content (`InTotoContent`)**: Consume titles, descriptions, badges, and cards from `contentEngine.js` or Native Web Components (`<intoto-category-badge>`, `<intoto-section-header>`, `<intoto-setting-card>`).
3. **Unified Navigation (`InTotoNav`)**: Route all Settings subviews via `InTotoNav.pushSettingsView(viewId, title)` and `InTotoNav.popSettingsView()`.
4. **Copywriting Standards (Smart 10-Year-Old Standard)**:
   - Settings headings: 1-2 words maximum, no numbers, no `&`.
   - Subtext: 1 short active sentence (10-12 words max).
   - Active verbs (*"Save Log"*, *"Sync Watch"*, *"Export File"*, *"Set PIN"*).
5. **Defensive Programming**: Sanitize HTML with `escapeHtml()`, parse JSON with `safeJsonParse()`, validate numeric ranges with `validateNumericRange()`, and trigger haptics with `triggerHaptic()`.
6. **Testing Verification**: Always verify changes by running `node www/js/tests/jsUnitTestSuite.js` (92 assertions) and Gradle `:app:testDebugUnitTest` (8 assertions).
