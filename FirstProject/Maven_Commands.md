1. Core Build Lifecycle Commands
These commands are tied to Maven's default build lifecycles. Running a specific phase automatically executes all preceding phases in that lifecycle.
• mvn clean: Deletes the target/ directory, removing all previously compiled files and build artifacts.
• mvn validate: Verifies that the project structure is correct and all necessary information is available.
• mvn compile: Compiles the source code of the project (saves .class files in target/classes).
• mvn test: Runs unit tests using the project's testing framework (e.g., JUnit).
• mvn package: Compiles the code, runs tests, and packages it into its distributable format, such as a JAR or WAR file.
• mvn verify: Runs integration tests and checks to ensure quality criteria are met.
• mvn install: Packages the code and installs the artifact into your local repository (~/.m2/repository) so it can be used as a dependency in other local projects.
• mvn deploy: Copies the final packaged artifact to a remote repository (like Nexus or Artifactory) for sharing with other teams.