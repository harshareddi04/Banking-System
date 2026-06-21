# Banking System

A full-stack Banking System web application developed using Java, JSP, Servlets, JDBC, MySQL, HTML, CSS, and Apache Tomcat. The application simulates basic banking operations such as account registration, login, deposit, withdrawal, balance enquiry, transaction history, and mini statement generation.

## Features

### User Management
- User Registration
- Secure User Login
- Session Management
- Logout Functionality

### Banking Operations
- Deposit Money
- Withdraw Money
- Balance Enquiry
- Transaction History
- Mini Statement (Last 5 Transactions)

### Security & Validation
- Password Validation
- 4-Digit Transaction PIN Verification
- Account Number Validation (12 Digits)
- Contact Validation (Email/Phone)
- Session-Based Authentication

### Database Operations
- Store User Information
- Store Transaction Records
- Real-Time Balance Updates
- Transaction Tracking

## Technologies Used

### Backend
- Java
- JSP (Java Server Pages)
- Servlets
- JDBC

### Frontend
- HTML5
- CSS3
- JavaScript

### Database
- MySQL

### Server
- Apache Tomcat

### Development Tools
- Eclipse IDE
- MySQL Workbench
- Git
- GitHub

## Project Structure

```
BankingSystem
│
├── src/main/java
│   ├── com.bank.connection
│   │   └── DBConnection.java
│   │
│   ├── com.bank.model
│   │   ├── User.java
│   │   └── Transaction.java
│   │
│   ├── com.bank.servlet
│   │   ├── RegisterServlet.java
│   │   ├── LoginServlet.java
│   │   ├── DashboardServlet.java
│   │   ├── DepositServlet.java
│   │   ├── WithdrawServlet.java
│   │   ├── BalanceServlet.java
│   │   ├── TransactionHistoryServlet.java
│   │   ├── MiniStatementServlet.java
│   │   └── LogoutServlet.java
│   │
│   └── com.bank.util
│       └── ValidationUtil.java
│
└── src/main/webapp
    ├── register.jsp
    ├── login.jsp
    ├── dashboard.jsp
    ├── deposit.jsp
    ├── withdraw.jsp
    ├── balance.jsp
    ├── transactionHistory.jsp
    ├── miniStatement.jsp
    ├── message.jsp
    └── css
        └── style.css
```

## Database Schema

### Users Table

```sql
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(100),
    contact VARCHAR(100) UNIQUE,
    account_number VARCHAR(12) UNIQUE,
    atm_card_number VARCHAR(20) UNIQUE,
    password VARCHAR(255),
    pin VARCHAR(10),
    balance DOUBLE DEFAULT 0
);
```

### Transactions Table

```sql
CREATE TABLE transactions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    account_number VARCHAR(12),
    transaction_type VARCHAR(20),
    amount DOUBLE,
    balance_after_transaction DOUBLE,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## How to Run the Project

### Prerequisites

- Java JDK 17 or later
- Apache Tomcat 10/11
- MySQL Server
- Eclipse IDE
- MySQL Workbench

### Setup

1. Clone the repository

```bash
git clone https://github.com/harshareddi04/Banking-System.git
```

2. Create database

```sql
CREATE DATABASE bankdb;
```

3. Execute table creation scripts.

4. Configure database connection in:

```java
DBConnection.java
```

```java
private static final String URL = "jdbc:mysql://localhost:3306/bankdb";
private static final String USERNAME = "root";
private static final String PASSWORD = "your_password";
```

5. Add MySQL Connector JAR.

6. Deploy project on Apache Tomcat.

7. Access application:

```
http://localhost:9090/BankingSystem
```

## Future Enhancements

- Spring Boot Migration
- Spring Security Integration
- BCrypt Password Encryption
- REST API Development
- Online Deployment
- Admin Dashboard
- Email Notifications
- JWT Authentication

## Project Highlights

- Developed using Java Enterprise Technologies
- Implemented JDBC for database connectivity
- Applied MVC-based architecture
- Implemented secure authentication and validation
- Maintained transaction history with real-time updates
- Designed responsive user interface using HTML and CSS

## Author

**Harshavardhan Reddy Settipalli**

📧 harshavardhanreddysettipalli4@gmail.com

🔗 GitHub: https://github.com/harshareddi04

---

### Banking System – Full Stack Java Web Application
A secure banking application that allows users to register, log in, deposit money, withdraw money, check balances, view transaction history, and generate mini statements using Java, JSP, Servlets, JDBC, MySQL, and Apache Tomcat.
