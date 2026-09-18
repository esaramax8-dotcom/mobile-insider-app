# Mobile Insider Android

Native Android/Jetpack Compose app for Mobile Insider. It does not load the website UI in a WebView. The app uses a separate native UI and connects to the existing Mobile Insider Netlify functions for AI and news.

## Build
Open the repository with Android/Gradle tooling or use the included GitHub Actions workflow. The project uses AGP 8.7.3, Kotlin 2.0.21, compileSdk 35 and Java 17.

## AI
The Android app calls:
`https://mobileinsider.netlify.app/.netlify/functions/ai-chat`

No Gemini secret is stored in the APK. Keep provider secrets in the Netlify environment used by the existing function.

## Important
The current website's public Supabase publishable key is not copied into this native app. Authentication/data screens are intentionally structured for the same backend, while privileged operations remain server-side.
