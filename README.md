[![Build Status](https://travis-ci.org/Trikcter/ERP-Project.svg?branch=master)](https://travis-ci.org/Trikcter/ERP-Project)
# ERP-Project
It is simple ERP system for small business written by Kotlin. I used Spring Boot and Docker. That system is my exam final task at my course in University
## Implementation Information
- **Programming Language**: Kotlin
- **Framework**: Spring Boot
- **Build System**: Gradle
- **CI**: Travis CI
- **Testing**: JUnit5
## Usage
You can use this application in to two mode
### Before you start
- Install Docker and Docker Compose
- Make sure to build the project: `./gradlew build`
### Production mode
Copy and past next command `docker-compose up -d`
### Development mode
Copy and past next command `docker-compose -f docker-compose.yml -f docker-compose.dev.yml up -d` 
#### Important endpoints
- Port 8082 - Web interface for Redis
- Port 5050 - PGAdmin
