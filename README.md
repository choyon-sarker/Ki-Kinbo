# Ki Kinbo! - An Online Supershop Management System
Welcome to our _**Ki Kinbo! - An Online Supershop Management System**_. This project is a comprehensive Android application for managing a supershop, including features like product management, customer order placement, inventory tracking, and more.

### Team Name: **LessThanSix**
49th Batch, Department of Computer Science & Engineering, Jahangirnagar University, Bangladesh
### Team Members: 
- NUZHAT NAIRY AFRIN (NN) - 355
- LAMIA BINTA LATIF (LB) - 366
- YUMNA TASNEEM (YT) - 369
- CHOYON SARKER (CS) - 392
- RAIAN RASHID (RR) - 403
## Project Overview
"Ki Kinbo!" Supershop Management System is an Android-based online supershop app focused on delivering a seamless and user-friendly shopping experience. Built using the MVVM (Model-View-ViewModel) architecture and Firebase, the app also streamlines store management by simplifying inventory and sales tracking. Designed to enhance customer satisfaction and loyalty, "Ki Kinbo!" aims to expand market reach and build strong brand recognition. Customers can browse, compare, and buy products across categories, with fast navigation and secure transactions. 
## Features
- **User-Friendly Interface:** Intuitive design with fast navigation for a smooth shopping experience.
- **Product Browsing & Search:** Browse products across categories with search filters for quick access.
- **Shopping Cart & Checkout:** Secure and simple cart and checkout process, supporting multiple payment options.
- **Order Management:** Customers can track orders; managers oversee processing and fulfillment.
- **Inventory Management:** Efficient stock management with easy inventory updates for managers.
- **Customer Account Management:** Profile setup, order history, and personalized recommendations.
- **Admin Panel:** A backend panel for admins to manage roles, configurations, and system operations.
- **Reports & Analytics:** Sales, customer trends, and inventory insights for strategic planning.
- **Security & Compliance:** Secure login and data protection aligned with regulatory standards.
- **Marketing & Campaign Tracking:** Tools for creating campaigns and tracking engagement.
- **Technical Support:** Post-launch support to resolve issues and maintain platform stability.
- **Firebase Integration:** Includes authentication, real-time database, and media storage through Firebase.
## Usage
**For Customers:**
- **Sign Up and Log In:** Users can create accounts and securely log in through Firebase Authentication to access personalized features.
- **Browse and Search Products:** Explore products by categories or search for specific items.
- **Add to Cart and Checkout:** Add items to the shopping cart and complete purchases through a secure, straightforward checkout process.
- **Order Tracking:** Track the status of orders in real-time after checkout.
- **Account Management:** Update profile details, view order history, and manage saved items.
  
**For Shop Managers:**
- **Inventory Control:** Access the product management section to add, edit, or delete items, monitor stock levels, and manage listings.
- **Sales Tracking:** Review sales data and generate reports to understand trends and track sales performance.
- **Order Fulfillment:** Oversee the entire order lifecycle, from order placement to delivery, ensuring timely processing and shipment. 
## Prerequisites
Before you start, ensure you have the following installed:
- [Android Studio](https://developer.android.com/studio)
- JDK 11 or higher
- An active [Firebase](https://firebase.google.com/) account.


## Installation Instructions for _**Ki Kinbo!**_ Project
Let's get started with **Ki Kinbo!** 🚀

Follow the steps below to clone the repository, set up the project in Android Studio, and install all necessary dependencies.
#### Step 1: Clone the repository
1. **Open Git Bash or Command Prompt:**
     - Launch Git Bash (or an equivalent terminal) on your computer. If you haven't installed Git, download it from [Git](https://git-scm.com/) and follow the setup instructions.
2. **Copy Repository Link:**
     - Go to the GitHub repository page for 'Ki Kinbo!'. You can do this by searching for 'Ki-Kinbo' on GitHub or using the direct link [Ki Kinbo! Github](https://github.com/choyon-sarker/Ki-Kinbo)
     - Copy the repository link by clicking the green **Code** button and selecting HTTPS (or SSH if you have set up SSH keys).
3. **Clone the Repository:**
     - In Git Bash, navigate to the directory where you want to store the project. Then, type the following command to clone the repository:
       
            git clone https://github.com/choyon-sarker/Ki-Kinbo.git
     - This command will create a new folder named Ki-Kinbo in your specified directory, containing all files and folders of the project.

#### Step 2: Open the Project in Android Studio
1. **Launch Android Studio:**
    - Open Android Studio. If you don’t have it installed, download it from [Android Studio](https://developer.android.com/studio) and follow the setup instructions.
2. **Select '_Open an existing project_':**
    - On the Android Studio welcome screen, click on Open an existing project.
3. **Navigate to the Cloned Repository:**
    - Use the file explorer to locate the Ki-Kinbo folder you cloned in Step 1. Select this folder and click OK to open the project in Android Studio.

#### Step 3: Install Project Dependencies
The project uses dependencies for Firebase, Material Design, and AndroidX libraries. Android Studio should automatically download these dependencies from the build.gradle files, but you may need to manually sync them.
1. **Verify dependencies:**
   - Open the build.gradle files in the project:
       - **Project-level** build.gradle: This file manages global settings and dependencies shared across all modules.
       - **App-level** build.gradle: This file manages dependencies specific to the app module.
   - In the app-level build.gradle file, ensure the required dependencies are listed as shown below:
<pre>  dependencies 
{
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.database)
    implementation(libs.androidx.recyclerview)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.androidx.lifecycle.livedata.testing)

    // JUnit 4 for unit tests
    testImplementation("junit:junit:4.13.2")

    // Robolectric for Android unit testing
    testImplementation("org.robolectric:robolectric:4.9.1")
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.testng)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.junit.jupiter)
    // Check for the latest version

    // Android test dependencies
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Dokka
    implementation("org.jetbrains.dokka:dokka-core:1.7.10")
    // JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")

    // Optional: AssertJ for fluent assertions (optional but helpful)
    testImplementation("org.assertj:assertj-core:3.24.2")

    // Android Testing libraries
    // To test LiveData and ViewModel
    testImplementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-livedata-testing:2.6.1")
    testImplementation("io.mockk:mockk:1.12.3") // For mocking in tests (mockk instead of Mockito)

    // Optionally, add JUnit Platform for running tests
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.10.0")

    // For testing LiveData objects and ViewModel
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    // Test for Kotlin coroutines (if your ViewModel uses coroutines)
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.24")
    implementation(kotlin("script-runtime"))
}  </pre>
2. **Sync dependencies:**
   - After verifying dependencies, click **Sync Now** if prompted at the top of the build.gradle file. This will download and install all necessary libraries and packages for the project.
   - If no prompt appears, manually initiate a sync by selecting **File > Sync Project with Gradle Files.**
3. **Check gradle configurations:**
   - Open the app-level build.gradle file and ensure the following SDK configurations are set, especially if Firebase Auth is being used:
      - minSdkVersion 23
      - targetSdkVersion \<latest SDK version\>
   - Confirm compatibility:
      - Ensure that both minSdkVersion and targetSdkVersion are compatible with the Firebase libraries used in the project. Adjust as necessary to avoid compatibility issues with Firebase and other libraries.

## Generate Documentation 
### Watch the tutorial on our Youtube Channel
A comprehensive and detailed tutorial on how to generate coding documentation using KDoc via Dokka. The tutorial includes a step-by-step procedure.
<p align="center">
  <a href="https://www.youtube.com/watch?v=lcpnnJtDdAU&t=2s">
    <img src="https://img.youtube.com/vi/lcpnnJtDdAU/maxresdefault.jpg" alt="Video Thumbnail" />
  </a>
</p>









## Contributing To The Project
Thank you for your interest in contributing to Ki Kinbo!. We’re excited to have you collaborate on improving this project! Below are detailed steps to help you get started with your contributions.
1. **Fork the Repository:**
   - Go to the Ki Kinbo! GitHub repository.
   - Click on the **Fork** button at the top right corner of the page. This will create a copy of the repository under your GitHub account.
2. **Clone Your Forked Repository:**
   - Open Git Bash or your preferred terminal.
   - Clone your forked repository to your local machine by running
     
          git clone https://github.com/choyon-sarker/Ki-Kinbo.git
   - Navigate into the project directory:
     
          cd Ki-Kinbo
3. **Create a new branch:**
   - Create a branch to keep your changes isolated. Use a descriptive name for the branch based on the feature or fix you're working on:
     
          git checkout -b feature-name-or-fix-description
4. **Make your changes:**
   - Open the project in Android Studio and implement your changes.
   - Ensure you follow the existing coding style and structure.
   - If adding a new feature, please document it clearly in the code and update any relevant files. 
5. **Test your changes:**
   - Build the project and run tests to ensure your changes are working as expected.
   - If you are adding a new feature, include unit tests if possible to ensure it functions correctly and doesn’t introduce issues.
6. **Commit your changes:**
   - Before committing, ensure that you have staged only the necessary files:

           git add .
   - Commit your changes with a clear and concise commit message:

          git commit -m "commit message"
7. **Push to your fork:**
   - Push your branch to your forked repository on GitHub:

          git push origin feature-name-or-fix-description
8. **Open a pull request:**
   - Go to your forked repository on GitHub.
   - You should see a prompt to **Compare & pull request.** Click on it
   - Add a title and description for your pull request. Make sure to include:
          - The purpose of the changes (feature, bug fix, documentation update, etc.).
          - Any issues this pull request closes (e.g., Closes #issue-number).
          - Relevant details that would help the reviewers understand your changes.
   - Submit the pull request.
9. **Participate in code review:**
    - Your pull request will be reviewed by project maintainers. They may request changes or ask for clarification.
    - Be open to feedback and make the requested updates if needed.
    - Push any additional changes to the same branch, and they will automatically appear in the pull request.
10. **Celebrate your contribution:**
    - Once your pull request is approved and merged, congratulations! You’ve contributed to Ki Kinbo! 🎉

## Setting Up Firebase
Firebase is essential for authentication, data storage, and media management in this project.
1. **Create Firebase Project:**
     - Go to [Firebase](https://console.firebase.google.com/)
     - Sign in with your Google account. Click on Add Project, enter a project name (e.g., 'Ki Kinbo!'), follow the setup steps, and click **Create Project**.
2. **Add Android App:**
   - In the Firebase console, select your project and navigate to Project Settings. Scroll down to Your Apps and click on Add App > Android. Enter your app's package name (e.g., 'com.devdroid.kikinbo').
   - Register the app and download the **google-services.json** file.
3. **Enable Authentication:**
   - In your Firebase console, go to Authentication > Sign-in Method. Enable the authentication methods you will use (e.g., Email/Password, Google).
4. **Set Up Firestore Database:**
   - In the Firebase console, navigate to Firestore Database > Create Database. Select a mode (either Start in production mode or Start in test mode) and set up your Firestore.
5. **Download and Add Firebase Configuration File:**
   - Place the **google-services.json** file in the app directory of your project. Ensure you sync your project files in Android Studio after adding the file.     

## Running the Project
After setting up Firebase and dependencies, you’re ready to run the project.
1. **Connect an Android** device or start an emulator in Android Studio.
2. Run the project by clicking on the **Run** button in Android Studio or pressing **Shift + F10**.
3. **Test Firebase Integration:**
   - Try signing in or registering to ensure Firebase Authentication is working.
   - Verify data is stored in the Firebase Realtime Database as you interact with the app.


































