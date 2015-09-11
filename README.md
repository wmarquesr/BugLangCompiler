# BugLang
A compiler that to do nothing unless pass on the course

## Gradle
Gradle is a build system that can compile, test and package. It can compile from command line or eclipse

### Terminal
To compile via terminal, execute:

```shell
./gradlew build run
```

if gradle is already installed on the computer, change ./gradlew to gradle.

To clean the project, execute:

```shell
./gradlew clean
```

### Eclipse
If eclipse will be used, execute that command below to configure that directory to a eclipse project:

```shell
./gradlew eclipse
```

To clean the project from eclipse files, execute:

```shell
./gradlew cleanEclipse
```

The first time executing gradlew can be slow 'cause there is download the essencial of gradle.
