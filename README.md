Beauty Academy Enrollment System

Overview
A comprehensive Android application for beauty academy student enrollment that features Firebase Authentication and a modular course selection system. The app allows students to sign up, log in, and enroll in various beauty and cosmetics courses while maintaining credit limits.

Features
- User Authentication
  - Email and password-based signup
  - Secure login system using Firebase Authentication
  - User session management

- Course Enrollment
  - Interactive course selection interface
  - Credit-based enrollment system (6-24 credits)
  - Real-time credit calculation
  - Course selection validation

- Available Courses
  - Advanced Makeup Artistry (6 credits)
  - Professional Product Knowledge (6 credits)
  - Color Theory in Makeup (6 credits)
  - Dermatology for Beauty Professionals (6 credits)
  - Holistic Skincare (6 credits)
  - Scalp Micropigmentation (6 credits)

Technical Specifications
- Minimum SDK: 26
- Target SDK: 34
- Compile SDK: 34
- Language: Java
- Backend: Firebase

Dependencies
```gradle
dependencies {
    implementation 'androidx.appcompat:appcompat:1.7.0'
    implementation 'com.google.android.material:material:1.12.0'
    implementation 'androidx.activity:activity:1.9.3'
    implementation 'androidx.constraintlayout:constraintlayout:2.2.0'
    implementation 'com.google.firebase:firebase-auth:23.1.0'
    implementation 'androidx.recyclerview:recyclerview:1.2.1'
    implementation 'com.google.firebase:firebase-firestore:25.1.1'
    implementation 'com.github.TutorialsAndroid:GButton:v1.0.19'
    implementation 'com.google.android.gms:play-services-auth:20.4.0'
}
```

Setup Instructions
1. Clone the repository:
```bash
git clone https://github.com/yourusername/beauty-academy-enrollment.git
```

2. Open the project in Android Studio

3. Set up Firebase:
   - Create a new Firebase project
   - Add your Android app to Firebase project
   - Download `google-services.json` and place it in the app directory
   - Enable Email/Password authentication in Firebase Console

4. Build and run the application

System Requirements
- Android Studio Electric Eel or newer
- JDK 8 or higher
- Android SDK Build Tools 34.0.0 or higher
- Android device/emulator running Android 8.0 (API 26) or higher

Application Flow
1. User signs up/logs in using email and password
2. Views welcome screen
3. Navigates to course selection menu
4. Selects courses (minimum 6 credits, maximum 24 credits)
5. Reviews selection in enrollment summary
6. Confirms enrollment

Acknowledgments
- Firebase Authentication
- Android Material Design Components
- RecyclerView for course listing
- CardView for UI elements
