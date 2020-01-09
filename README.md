# jFlink
[![Drone (cloud)](https://img.shields.io/drone/build/1995parham/jflink.svg?style=flat-square)](https://cloud.drone.io/1995parham/jflink)

## Introduction
There are many events in a soccer game. events have the following structure in JSON:

```json
{
  "timestamp": "2016-11-01T20:44:39Z",
  "type": "Foul",
  "team": 0
}
```

The jFlink consumes these events from Kafka then categorizes them with their type and creates the following results:

```
1> (Foul,1)
```

## Up and Running
A Flink application project using Java and Gradle.

To package your job for submission to Flink, use: `gradle shadowJar`. Afterwards, you'll find the
jar to use in the `build/libs` folder.

To run and test your application with an embedded instance of Flink use: `gradle run`
