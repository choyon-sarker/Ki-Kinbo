# Ki Kinbo! - An Online Supershop Management System
Welcome to our _**Ki Kinbo! - An Online Supershop Management System**_. This project is a comprehensive Android application for managing a supershop, including features like product management, customer order placement, inventory tracking, and more.

### Team Name: **LessThanSix**
49th Batch, Department of Computer Science & Engineering, Jahangirnagar University, Bangladesh
### Team Members: 
- NUZHAT NAIRY AFRIN (NN)- 355
- LAMIA BINTA LATIF (LB)- 366
- YUMNA TASNEEM (YT)- 369
- CHOYON SARKER (CS)- 392
- RAIAN RASHID (RR)- 403
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
  // Add other required dependencies here 
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
## Watch on Our Youtube Channel
[KDoc documentation tool integration by helping dokka tutorial  on Youtube](https://youtu.be/lcpnnJtDdAU?si=Zzrk5WZtCTo8pbpB)
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
     





































