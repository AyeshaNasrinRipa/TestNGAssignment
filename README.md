# 🧪 DailyFinance Automation Testing using Selenium and TestNG

## 📘 Project Overview

This project automates the functional flow of [DailyFinance](https://dailyfinance.roadtocareer.net/) using **Selenium WebDriver**, **TestNG**, **Java**, and **Page Object Model (POM)** design pattern.  
It performs user registration, email verification via Gmail API, password reset, login validation, item addition, profile update, and admin dashboard verification.  
Admin credentials are securely passed from the **command prompt** during runtime for safety.

---
## 🧩 Technology Stack
- **Language:** Java  
- **Framework:** TestNG  
- **Automation Tool:** Selenium WebDriver  
- **Build Tool:** Gradle  
- **Design Pattern:** Page Object Model (POM)  
- **Reporting:** Allure Report  
- **Data Handling:** CSV (Apache Commons CSV), JSON  
- **API Testing:** RestAssured (Gmail API)

---
### 2. **pages/**
Implements POM-based UI classes for each web page:
- `SignupPage.java`
- `LoginPage.java`
- `UserDashboardPage.java`
- `AddCostPage.java`
- `AdminDashboardPage.java`
- `ResetPasswordPage.java`
- `ResetPasswordFormPage.java`

### 3. **testrunner/**
Contains all TestNG test classes:
- `SignupTestRunner.java`
- `ResetPasswordTestRunner.java`
- `ResetPasswordFormTestRunner.java`
- `UserLoginTestRunner.java`
- `UserProfilePageTestRunner.java`
- `AddCostTestRunner.java`
- `AdminDashboardTestRunner.java`
- `AdminFetchUsersTestRunner.java`
- `RegistrationTestRunnerCSV.java`

### 4. **services/**
- `GmailService.java` – Connects to Gmail API using OAuth token, reads latest emails, extracts snippets and reset links.

### 5. **utils/**
Utility methods for reading/writing JSON, text files, and managing tokens.

---

## 🧠 Test Scenarios (12 Tasks)
Each of the following automated tasks corresponds to a major project requirement.  

### **Task 1:** Register a new user  
- Automates the registration of a new Gmail-based account using Faker-generated data.  
- Verifies that a **“Congratulations on Registering!”** email is received from Gmail API.

### **Task 2:** Reset password (Negative Tests Included)  
- Two **negative test cases** verify:
  - Reset password with **blank email** → validation message displayed.  
  - Reset password with **unregistered email** → “Your email is not registered” message appears.  
- Positive case: Valid registered email triggers a reset link email.

### **Task 3:** Request valid password reset link  
- Sends a password reset request for a registered Gmail account and asserts success message.

### **Task 4:** Retrieve Gmail reset link & set new password  
- Uses Gmail API to extract reset link.  
- Opens the link and sets a new password.  

### **Task 5:** Login using new password  
- Logs in with updated password and verifies successful dashboard access.

### **Task 6:** Add random two items  
- Adds:
  1. One item with **all fields** filled.  
  2. Another with **only mandatory fields**.  
- Asserts that both appear in the user’s item list.

### **Task 7:** Update user Gmail in profile  
- Updates the user email with a **new Gmail** in the profile section.  
- Alerts handled and JSON file updated dynamically.

### **Task 8:** Login validation with updated Gmail  
- Verifies that login with the **new email succeeds** and with the **old email fails.**

### **Task 9:** Admin login with credentials from terminal  
- Admin credentials are provided securely via the **command line**
- Successfully logs in and **navigates to the admin dashboard**.  

### **Task 10: Search Updated Gmail in Admin Dashboard**
- Searches for the recently updated user email.  
- Asserts that the **correct email appears in the user table**.  

### **Task 11: Register 3 More Users Using CSV File**
- Reads user data from **signup.csv** using Apache Commons CSV.  
- Registers all users sequentially using **data-driven testing**.  

### **Task 12: Fetch All Users and Write to Text File**
- Logs in as admin.  
- Fetches all users from the dashboard.  
- Writes the user list to **allUsers.txt**.  

---

### Recording of the full automation process: 
[![Watch Full Automation Process Video](https://img.youtube.com/vi/acTRZ6uhHOo/0.jpg)](https://youtu.be/acTRZ6uhHOo)

---

## 📝 Standard Test Cases
All automated tasks have been converted into **standardized test cases** for documentation, including **negative scenarios**.

**Access the test cases here:**  
[Test Cases Document](https://docs.google.com/spreadsheets/d/1PMxcoI-4_9_7CGZJmfD3Hl-L5KkDl8iqSPg8-QbrLHw/edit?usp=sharing) 

---

## 📊 Allure Report

<img width="1366" height="693" alt="image" src="https://github.com/user-attachments/assets/0e3445f6-5c53-4de9-89eb-69c125d48724" />

<img width="1365" height="696" alt="image" src="https://github.com/user-attachments/assets/09b91231-1dbb-4eff-bfa3-aeaba4206eac" />


