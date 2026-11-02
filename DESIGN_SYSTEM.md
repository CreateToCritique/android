# InToto Wellness - Comprehensive Design System Specification (v3.0.0 SSOT)

This document is the **Single Source of Truth (SSOT) Master Specification** for the InToto Wellness design system, UI component library, token taxonomy, multi-theme family engine, unified navigation engine, and cross-platform Capacitor Android implementation standards.

---

## 1. Single Source of Truth Architecture

To ensure the design system is a live, executable engineering system rather than static documentation, the system is maintained across 4 coordinated assets:

| Asset File | Type & Location | Purpose & Function |
| :--- | :--- | :--- |
| **`design-tokens.css`** | CSS Variable Engine (`www/css/design-tokens.css`) | Executable single source of truth stylesheet defining all CSS custom properties for 16 theme states (8 World Palette Families x 2 Modes). |
| **`contentEngine.js`** | Centralized Content Provider (`www/js/modules/contentEngine.js`) | Single Source of Truth text registry (`InTotoContent`) and Native Web Components (`<intoto-category-badge>`, `<intoto-section-header>`, `<intoto-setting-card>`). |
| **`navEngine.js`** | Unified Navigation Controller (`www/js/modules/navEngine.js`) | Single Source of Truth navigation stack engine (`InTotoNav`) managing subviews, tab routing, and hardware back button priority. |
| **`DESIGN_SYSTEM.md`** | Specification Blueprint (`www/android/DESIGN_SYSTEM.md`) | Comprehensive engineering documentation, token tables, component anatomy, and copy/paste code snippets. |
| **`design-system.html`** | Living Showcase (`www/design-system.html`) | Live, interactive component library showcase allowing real-time QA testing across all 16 theme states in 1 tap. |

---

## 2. Core Philosophy & Design Principles

InToto combines **Biophilic Health Aesthetics** with two foundational layout principles:

### A. Frameless Content Resting
- **Principle**: Text, metadata, list items, and section rows rest directly on the page background surface without heavy card containers (`bg-[#010805]` or `bg-[#061c13]`).
- **Dividers**: Clean bottom borders (`border-b border-[#12402c]/30`) and generous vertical spacing are used to separate sections instead of boxed panels.
- **Reference Standard**: The **Settings Page Hub** (`settingsViews.js`) serves as the core reference model for this clean, frameless, row-based layout structure.

### B. Selective Skeuomorphism
- **Principle**: 3D bevels, inner glows, and tactile drop-shadows are reserved **strictly for interactive controls** (buttons, form inputs, toggle switches, segmented chips).
- **Function**: Gives users immediate physical affordances and tactile feedback without cluttering content readability.

---

## 3. World Palette Families Inventory (v3.0.0 Multi-Theme Engine)

InToto v3.0.0 features **8 World Palette Families**, each providing both a **Dark Mode (OLED)** and **Light Mode (Daylight)** variant (16 theme states total):

| Family Key | Family Name | Dark Mode Concept | Light Mode Concept | Fit & Target Demographic |
| :--- | :--- | :--- | :--- | :--- |
| **`emerald`** | **InToto Emerald** | Deep Forest (`#02100a`), Gold Accent (`#d4af37`), Teal (`#2dd4bf`) | Soft Light Sage (`#f4f8f5`), White Cards, Deep Teal (`#0d9488`) | Signature biophilic health identity. |
| **`corporate`** | **Corporate Executive** | Midnight Sapphire (`#0b132b`), Indigo (`#1c2541`), Cobalt (`#38bdf8`) | Ice Slate (`#f1f5f9`), White Cards, Royal Navy (`#1d4ed8`) | Professional blue productivity & clinical feel. |
| **`amethyst`** | **Amethyst Quartz** | Deep Violet (`#0d0b18`), Dark Indigo (`#16122c`), Lavender (`#a855f7`) | Pastel Lavender (`#fcf7ff`), White Cards, Orchid (`#9333ea`) | Serene, calm, feminine quartz & mindfulness. |
| **`sakura`** | **Sakura Blossom** | Deep Plum (`#1a0913`), Dark Rose (`#2b1220`), Sakura Pink (`#f472b6`) | Blush Pink (`#fff5f7`), White Cards, Rich Berry Rose (`#db2777`) | Soft, elegant, feminine warm rose floral. |
| **`amber`** | **Champagne & Amber** | Chocolate Obsidian (`#140d07`), Amber (`#24170d`), Gold (`#f59e0b`) | Golden Cream (`#fffbeb`), White Cards, Amber Gold (`#d97706`) | Warm, luxurious, radiant champagne & amber. |
| **`terracotta`**| **Warm Terracotta** | Burnt Clay (`#1a0c08`), Dark Ochre (`#2a1610`), Terracotta (`#e07a5f`) | Limestone (`#fdf8f5`), White Cards, Clay Terracotta (`#c85a32`) | Grounding, organic Mediterranean clay & warm earth. |
| **`nordic`** | **Polar OLED & Snow** | Pitch Black AMOLED (`#000000`), Charcoal (`#111111`), Cyan (`#00f2fe`) | Polar White (`#ffffff`), Mint Cards (`#f0fdf4`), Blue (`#0284c7`) | Maximum battery savings (Dark) and high contrast (Light). |
| **`cyberpunk`** | **Neon Forest** | Obsidian Green (`#01140e`), Emerald (`#04291b`), Neon Lime (`#10b981`) | Mint Mist (`#ecfdf5`), White Cards, Electric Teal (`#059669`) | High-energy, bioluminescent cyber sports. |

---

## 4. Typography Scale & Spatial Rhythm

| Level | Size (px / rem) | Font Weight | Letter Spacing | Target Elements |
| :--- | :--- | :--- | :--- | :--- |
| **Micro Badge** | `9px` (`0.5625rem`) | `700` (Bold) / `800` | `tracking-widest` | Category chips, pill tags, time slots. |
| **Helper Subtext** | `10px` (`0.625rem`) | `400` / `600` | `tracking-wider` | Explanatory labels, last sync times. |
| **Action Label** | `12px` (`0.75rem`) | `700` (Bold) | `tracking-wide` | Button text, list items, input text. |
| **Section Header** | `14px` (`0.875rem`) | `900` (Black) | `tracking-widest` | Subview titles, modal headers. |
| **Primary Metric** | `24px+` (`1.5rem+`) | `900` (Black) | `tracking-widest` | Vitality scores, timers, daily counts. |

---

## 5. Micro-Animations, Navigation Stack & Mobile Ergonomics

### A. Micro-Animations & CSS Utilities
- **`.micro-press`**: Snappy scale-down (`0.97`) on touch/active state for all buttons, chips, checklist rows, and action items.
- **`.subview-fade`**: Fade-in + subtle translateY lift (`fadeInScale`) for settings sub-views, modal dialogs, and tab transitions.
- **`.gold-glow-pulse`**: Soft infinite gold drop-shadow pulse for completion milestones and active streak badges.

### B. Unified Navigation Stack & Hardware Back Button Rules (`InTotoNav`)
- **Subpage Back Navigation**: Tapping the top-left subpage Back button (`InTotoNav.popSettingsView()`) pops the top subpage and reveals the previous view in the stack (`hub` or parent subview) **within the Settings tab**. It never redirects to the Log tab.
- **Hub Back Navigation**: Tapping Back while **already on the Settings Hub** (`hub`) returns to the Daily Log tab (`goToHomeLogPage()`).
- **Hardware & Gesture Back Button**: Android Capacitor back button events delegate to `InTotoNav.handleHardwareBack()`:
  1. Dismiss active modals (`#dataPageModal`, `#customModal`, `#biometricLockModal`, etc.)
  2. Pop Settings subpage stack if in Settings
  3. Switch tab to Daily Log if on Settings Hub
  4. Minimize app on double-tap back from Daily Log

### C. Mobile & Touch Accessibility Standards
- **Touch Targets**: Minimum touch height of `36px` (`touch-action: manipulation`). Compact rating chips enforce `28px` height inside `.segmented-container`.
- **Safe Area Insets**: Safe area padding via `env(safe-area-inset-top)` and `env(safe-area-inset-bottom)` for Android/Capacitor builds.
- **Haptics Physics**: `triggerHaptic('light')` on taps/selections; `triggerHaptic('heavy')` on completions/saves.

---

## 6. Plus (+) & Minus (-) Action vs Stepper Taxonomy

### A. Plus (+) Interactive Rules
- **Unified Add Action (`skeuo-button` Standard)**: Section Header / Ad-Hoc Quick Add (`skeuo-button px-3.5 py-1.5 rounded-xl text-xs font-bold text-goldAccent flex items-center space-x-1.5`). Never pair the `+` icon with text.
- **Surface-Resting Embossed Stepper Plus**: Numeric Steppers / Value Increments resting directly on the surface (`text-goldAccent font-black text-sm drop-shadow-[0_1px_2px_rgba(0,0,0,0.9)]`).

### B. Minus (-) Interactive Rules
- **Surface-Resting Embossed Stepper Minus**: Symmetrical pair for numeric input decrements resting directly on the surface without a heavy button box.
- **Row Deletion & Removal**: Semantic danger/rose-colored icon button (`text-rose-400 hover:text-rose-300 p-1`) using `fa-solid fa-trash` or `fa-solid fa-xmark`.

---

## 7. 5-Pillar Health Badge & Icon Taxonomy

### A. Category Color Tokens
| Pillar / Category | Key | Dark Theme Token & Style | Light Theme Token & Style |
| :--- | :--- | :--- | :--- |
| **Prescriptions & Meds** | `rx` | `bg-goldAccent/20 text-goldAccent border-goldAccent/40` (`#2DD4BF`) | `bg-teal-100 text-goldAccent border-teal-300` (`#0D9488`) |
| **Movement & Exercise** | `ex` | `bg-emerald-950 text-emerald-400 border-emerald-800` (`#34D399`) | `bg-emerald-100 text-emerald-400 border-emerald-300` (`#059669`) |
| **Acts of Love & Care** | `love` | `bg-rose-950 text-rose-400 border-rose-800` (`#F87171`) | `bg-rose-100 text-rose-400 border-rose-300` (`#E11D48`) |
| **Mental Wellness** | `mental` | `bg-indigo-950 text-indigo-400 border-indigo-800` (`#818CF8`) | `bg-indigo-100 text-indigo-400 border-indigo-300` (`#4F46E5`) |
| **Nutrition & Meals** | `nut` | `bg-amber-950 text-amber-400 border-amber-800` (`#FBBF24`) | `bg-amber-100 text-amber-400 border-amber-300` (`#D97706`) |
| **Distractions & Screen** | `distraction` | `bg-slate-900 text-saffron border-slate-700` (`#818CF8`) | `bg-slate-100 text-saffron border-slate-300` (`#4F46E5`) |

---

## 8. Copywriting & Tone Blueprint (Smart 10-Year-Old Standard)

To maintain a consistent, conversational, and accessible tone across all application views:

1. **Heading Length Constraint**: Headings and menu titles must be **1 or 2 words maximum**, contain **no numbers**, and contain **no ampersands (`&`)**.
2. **Subtext Constraint**: Descriptions must be a single, active, conversational sentence (10-12 words maximum).
3. **Everyday Plain Language**: Replaces complex clinical jargon with plain terms (e.g. *"Body Stats"* instead of *"Physical Biometrics & Body Comp"*, *"Sleep Routine"* instead of *"Circadian Chronotype"*).
4. **Single Source of Truth Content Engine**: All category titles, Settings Hub card titles, descriptions, and badges must be consumed directly from `InTotoContent` in [`js/modules/contentEngine.js`](file:///C:/Users/mellwynjoseph/Desktop/intoto-app/www/js/modules/contentEngine.js) or native Web Components (`<intoto-category-badge>`, `<intoto-section-header>`, `<intoto-setting-card>`).

---

## 9. Flawless Visual Startup Architecture & Two-Stage Hydration Pipeline

To eliminate white canvas flashes, Flash of Unstyled Content (FOUC), and initial layout shifts on cold-boot:

1. **Critical Canvas Shield**: Sets `#02100a` background and `#f1f5f9` text synchronously in `<head>` before body parsing.
2. **Pre-Rendered Skeleton Shimmers**: `.skeleton-shimmer` CSS animation classes display biophilic dark emerald placeholders for daily checklist cards during DOM parsing.
3. **Two-Stage Asynchronous Hydration (`initAppFlow`)**:
   - **Stage 1 (Immediate / <30ms)**: Hydrates date, weight/sleep inputs, medication checkboxes, and routine schedules $\rightarrow$ Triggers smooth 250ms opacity fade-in of `#mainViewport`.
   - **Stage 2 (Deferred / >100ms via `requestIdleCallback`)**: Renders Chart.js graphs, notification schedules, and Google Drive cloud status without blocking main-thread UI frames.

---

## 10. Living Showcase Access & Verification

The living showcase is accessible by opening [`www/design-system.html`](file:///C:/Users/mellwynjoseph/Desktop/intoto-app/www/design-system.html) in any browser or mobile WebViews.
