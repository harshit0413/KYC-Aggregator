# KYC Aggregator Service

## Overview
The **KYC Aggregator Service** is a Spring Boot-based application designed to verify and manage KYC (Know Your Customer) details using PAN and Aadhaar information. It provides RESTful APIs for verifying PAN and Aadhaar details, ensuring data security through encryption and validation mechanisms.

---

## Features
- **PAN Verification**: Validate and retrieve details of a PAN card.
- **Aadhaar Verification**: Validate and retrieve details of an Aadhaar card.
- **Consent Management**: Ensures user consent is obtained before processing KYC data.
- **Error Handling**: Centralized exception handling with custom error messages.
- **Data Encryption**: Secure sensitive data using AES encryption.
- **Database Integration**: Stores and retrieves KYC details from a MySQL database.

---

## Technologies Used
- **Java 21**
- **Spring Boot 3.4.4**
  - Spring Web
  - Spring Data JPA
  - Spring Validation
- **MySQL** (Production Database)
- **H2 Database** (Test Database)
- **Lombok** (for reducing boilerplate code)
- **Maven** (build tool)

---

## Prerequisites
- **Java 21** or higher
- **Maven 3.8+**
- **MySQL** installed and running
- **Postman** or any API testing tool (optional)

---

## Setup Instructions

### 1. Clone the Repository
```bash
git clone <repository-url>
```

### 2. Configure the Database
Update the database credentials in the `src/main/resources/application.properties` file:
```ini
spring.datasource.url=jdbc:mysql://localhost:3306/ClientOnboardingDb
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>
```

> For testing, the application uses an in-memory H2 database. No additional configuration is required for testing.

### 3. Build the Project
```bash
mvn clean install
```

### 4. Run the Application
```bash
mvn spring-boot:run
```

By default, the application runs on port `8083`. You can change it in the `application.properties`:
```ini
server.port=8083
```

---

## API Endpoints

### Base URL
```
http://localhost:8083/kyc
```

### 1. Verify PAN
**Endpoint**: `POST /kyc/verify-pan`  
**Request Body**:
```json
{
  "pan": "<encrypted-pan>",
  "consentGiven": true
}
```
**Response Codes**:
- **200 OK**: PAN verified successfully.
- **404 NOT FOUND**: PAN not found.
- **400 BAD REQUEST**: Consent not given or invalid PAN format.

### 2. Verify Aadhaar
**Endpoint**: `POST /kyc/verify-aadhaar`  
**Request Body**:
```json
{
  "aadhaar": "<encrypted-aadhaar>",
  "consentGiven": true
}
```
**Response Codes**:
- **200 OK**: Aadhaar verified successfully.
- **404 NOT FOUND**: Aadhaar not found.
- **400 BAD REQUEST**: Consent not given or invalid Aadhaar format.

---

## Error Handling
Global exception handler returns meaningful error responses.

Example:
```json
{
  "statusCode": 101,
  "message": "Consent not given by the user"
}
```

Error codes and messages are defined in `ErrorMapperUtil`.

---

## Security
- **Encryption**: PAN and Aadhaar numbers are encrypted using AES.
- **Validation**: Regex patterns used for validating PAN and Aadhaar formats.

---

## Testing
Unit tests are available for the `KYCService` class.

To run tests:
```bash
mvn test
```

### Test Scenarios
- PAN verification with valid/invalid formats.
- Aadhaar verification with valid/invalid formats.
- Consent missing.
- PAN/Aadhaar not found.

---

## Database Schema

### 1. `PANTable`
| Column Name   | Type        | Constraints       |
|---------------|-------------|-------------------|
| PANNo         | VARCHAR(10) | Primary Key       |
| FullName      | VARCHAR(50) | Not Null          |
| DOB           | DATE        |                   |
| Mobile        | VARCHAR(15) |                   |
| FatherName    | VARCHAR(50) |                   |
| MotherName    | VARCHAR(50) |                   |

### 2. `AadhaarTable`
| Column Name   | Type         | Constraints       |
|---------------|--------------|-------------------|
| AadhaarNo     | VARCHAR(12)  | Primary Key       |
| FullName      | VARCHAR(50)  | Not Null          |
| DOB           | DATE         |                   |
| Gender        | CHAR(1)      |                   |
| AddressLine1  | VARCHAR(255) |                   |
| AddressLine2  | VARCHAR(255) |                   |
| City          | VARCHAR(100) |                   |
| State         | VARCHAR(100) |                   |
| PIN           | VARCHAR(6)   |                   |
| Mobile        | VARCHAR(15)  |                   |
| Email         | VARCHAR(30)  |                   |
| FatherName    | VARCHAR(50)  | Not Null          |
| MotherName    | VARCHAR(50)  | Not Null          |

### 3. `KYCDetails`
| Column Name   | Type         | Constraints       |
|---------------|--------------|-------------------|
| KID           | INT          | Primary Key       |
| KYCDocType    | VARCHAR(30)  | Not Null          |
| KYCDocID      | VARCHAR(255) | Not Null          |
| ConsentGiven  | BOOLEAN      | Not Null          |
| CreatedOn     | DATETIME     |                   |
| CreatedBy     | VARCHAR(50)  |                   |
| ModifiedOn    | DATETIME     |                   |
| ModifiedBy    | VARCHAR(50)  |                   |

---

## License
This project is licensed under the MIT License.

---

## Contact
For any queries or issues, please contact the development team.
```
