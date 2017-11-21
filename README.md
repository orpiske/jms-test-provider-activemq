JMS Test Util: Active MQ provider
============

Introduction
----

This is an Active MQ provider for  JMS Test Util

Build Status
----

Build Status: [![Linux Build Status](https://travis-ci.org/orpiske/jms-test-provider-activemq.svg?branch=master)](https://travis-ci.org/orpiske/jms-test-provider-activemq)


Building
----

This is only required if you want to compile the project. There are Maven dependencies available
for daily usage. check the usage section for details.


To download the project you can use:

```
git clone https://github.com/orpiske/jms-test-provider-activemq.git -b
jms-test-provider-activemq-1.0.0
```

You can use Maven to build the project. No additional configuration or setup
should* be required. To compile and installgi the project, please run:

```
mvn clean install
```

The build system will generate deliverables in zip, tar.bz2, tar.gz format.
Project specific jars will also be generated.

Note: no additional configuration should be required, however if the host
system has any service running on port 61616 (such as ActiveMQ), it may be necessary to
change the TestConfiguration class. It is located
 in the test package. The following line should be modified:

```
public static final String CONNECTOR = "tcp://localhost:61616";
```


Usage
----

**Dependencies**:

To use this project as library on your project you have to add my personal 
[bintray](https://bintray.com/orpiske/libs-release/) repository to the pom.xml
file:

```
<repositories>
    <repository>
        <id>orpiske-repo</id>
        <url>https://dl.bintray.com/orpiske/libs-release</url>
    </repository>
</repositories>
```

Then, the library can be referenced as: 
```
<dependency>
    <groupId>net.orpiske</groupId>
    <artifactId>jms-test-provider-activemq</artifactId>
    <version>1.1.0</version>
</dependency>
```

**Note**: replace version with the latest available version you wish to use.

**API Documentation**:

**API**:

The API documentation (javadoc) is available [here](http://www.orpiske.net/files/javadoc/jms-test-provider-activemq-1.1/apidocs/). 

**Usage**:

Annotate the test class with:

```
@RunWith(JmsTestRunner.class)
@Provider(
        value = ActiveMqProvider.class,
        configuration = ActiveMqConfiguration.class)
```

References
----

* [Main Site](http://orpiske.net/)
* [Apache Active MQ](http://activemq.apache.org/)

