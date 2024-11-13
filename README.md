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
## Features
## Prerequisites
## Installation Instructions for _**Ki Kinbo!**_ Project
To get started with Ki Kinbo! follow the steps below to clone the repository, set up the project in Android Studio, and install all necessary dependencies.
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






































