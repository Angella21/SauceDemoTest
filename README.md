# SauceDemoTest

![Java](https://img.shields.io/badge/Java-17-brightgreen)
![Maven](https://img.shields.io/badge/Maven-3.9.0-blue)
![Build](https://img.shields.io/badge/Build-passing-brightgreen)
![Allure](https://img.shields.io/badge/Allure-Report-orange)


Automated UI tests for the Sauce Demo web application using **Playwright**, **JUnit 5**, and **Allure Reports**.

---

## 🧪 Project Overview
This project demonstrates automated end-to-end testing for the Sauce Demo site.  
It includes login tests, cart operations, and logout functionality.

---

## ⚙️ Technologies
- Java 17
- Playwright Java
- JUnit 5
- Allure Reports
- Maven

---

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher installed
- Maven installed
- Allure installed (optional, for reports)

### Installation
1. Clone the repository: ```bash - git clone <your-repo-url>
2. Navigate to the project folder: 
   cd SauceDemoTest
3. Install dependencies: 
   mvn clean install
4. Running Tests: 
   mvn clean test
5. Generating Allure Reports:
   allure serve allure-results
