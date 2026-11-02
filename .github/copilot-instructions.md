# InToto Wellness - AI Agent System Instructions & Coding Standards

When assisting with the InToto Wellness codebase (`com.intoto.wellness`), strictly follow these non-negotiable architectural and coding standards:

---

## 1. DUAL-DIRECTORY MIRRORING RULE (CRITICAL)
- **Source Root**: `www/` (`js/`, `css/`, `index.html`, `design-system.html`)
- **Android Asset Root**: `www/android/app/src/main/assets/public/`
- **MANDATORY**: Whenever creating, updating, or deleting any JavaScript, CSS, or HTML file in `www/`, you **MUST update the corresponding file in `www/android/app/src/main/assets/public/` byte-for-byte**. Never allow browser preview and Android APK assets to desync.

---

## 2. DESIGN PHILOSOPHY & THEME ENGINE
- **Frameless Content Resting**: Text, metadata, list items, and section rows rest directly on the page background surface separated by clean bottom dividers (`border-b border-[#12402c]/30`). Avoid heavy boxed card containers around text rows.
- **Selective Skeuomorphism**: 3D bevels, inner glows, and tactile shadows are reserved **strictly for interactive controls** (`.skeuo-button`, `.skeuo-input`, `.skeuo-switch`, `.segmented-chip`).
- **16-Theme Family Engine**: Consume CSS Variables (`var(--color-bg-app)`, `var(--color-bg-card)`, `var(--color-border)`, `var(--color-text-primary)`, `var(--color-accent-gold)`). Theme overrides in `app.css` rely on `[data-theme]` selectors for all 8 World Palette Families in Light/Dark mode.

---

## 3. SINGLE SOURCE OF TRUTH CONTENT & NAVIGATION
- **Content Engine (`InTotoContent`)**: Consume all category titles, Settings Hub card titles, descriptions, and category badges from `InTotoContent` in `js/modules/contentEngine.js` or Native Web Components (`<intoto-category-badge>`, `<intoto-section-header>`, `<intoto-setting-card>`).
- **Navigation Router (`InTotoNav`)**: Route all Settings subviews via `InTotoNav.pushSettingsView(viewId, title)` and `InTotoNav.popSettingsView()`.
- **Copywriting Standard (Smart 10-Year-Old Standard)**:
  - Settings headings: 1-2 words maximum, no numbers, no `&` (*Body Stats*, *Medical ID*, *Vitals*, *App Colors*).
  - Subtext: 1 short active sentence (10-12 words max).
  - Use active verbs (*"Save Log"*, *"Sync Watch"*, *"Export File"*, *"Set PIN"*).

---

## 4. DEFENSIVE PROGRAMMING & SECURITY
- **XSS Prevention**: Always sanitize user inputs before rendering in `innerHTML` using `escapeHtml(str)`.
- **Defensive LocalStorage**: Always parse `localStorage` using `safeJsonParse(key, defaultValue)`.
- **Bounds Validation**: Validate numeric inputs using `validateNumericRange(val, min, max, defaultVal)` and time strings using `validateTimeString(timeStr, defaultTime)`.
- **Haptics**: Call `triggerHaptic('light'|'medium'|'heavy')` on taps, toggles, and form submissions.

---

## 5. TESTING & VERIFICATION REQUIREMENTS
- **JavaScript Unit Suite**: Run `node www/js/tests/jsUnitTestSuite.js` to verify all 15 test groups pass (92 assertions).
- **Android JVM Unit Suite**: Run Gradle task `:app:testDebugUnitTest` to verify Java metabolic calculators pass (8 assertions).
