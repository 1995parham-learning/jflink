# jFlink
[![Drone (cloud)](https://img.shields.io/drone/build/1995parham/jflink.svg?style=flat-square)](https://cloud.drone.io/1995parham/jflink)

## Up and Running
A Flink application project using Java and Gradle.

To package your job for submission to Flink, use: 'gradle shadowJar'. Afterwards, you'll find the
jar to use in the 'build/libs' folder.

To run and test your application with an embedded instance of Flink use: 'gradle run'
