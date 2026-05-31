![Version](https://img.shields.io/badge/version-0.2.0--alpha-orange)  
![Java](https://img.shields.io/badge/java-25.0.3-blue?logo=openjdk)  
![Gradle](https://img.shields.io/badge/gradle-8.14-02303A?logo=gradle)  

# What is LFTR

LFTR (Local File Transfer Routine) is a one-man project intended to be a quick, lightweight and cross-platform solution to share files within local networks. This repository hosts an experimental version of the project, featuring both `client`
and `server` implementations and a CLI for the client.

# Prerequisites

- SDK: Java Temurin JDK 25 (https://adoptium.net/temurin/releases?version=25)
- Build: Gradle 8+

# Build instructions

1. Clone this repository:

```
git clone https://github.com/vladlautaru/local-file-transfer-routine.git
```

2. Change directory to project root:

```
cd local-file-transfer-routine
```

3. Build using:

```
./gradlew build
```

This should create a new `jar` directory, with two JAR files inside: `client.jar` and `server.jar`.

4. Run either JAR by using:

```
cd jar
java -jar client.jar
```
or
```
java -jar jar/client.jar
```
