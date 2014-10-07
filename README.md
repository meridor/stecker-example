# Plugin Engine Example
This is a simple example showing how to use [plugin engine](https://github.com/meridor/plugin-engine).
## What it does
This is a simple console application taking one or more user names and greeting each user. The project contains two plugins which greet differently and **main** module which actually does the job.

## How to run
You need to have:
* JDK 1.8.+
* Maven 3.2+
To compile the example type:
```java
$ mvn clean package
```
To run it go to **target** directory and type the following command:
```bash
$ java -jar main-1.0-SNAPSHOT.jar <your-name>
```