# DonorOnCall_Server

[![Join the chat at https://gitter.im/donorcall01/DonorOnCall_Server](https://badges.gitter.im/donorcall01/DonorOnCall_Server.svg)](https://gitter.im/donorcall01/DonorOnCall_Server?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)  [![Build Status](https://travis-ci.org/donorcall01/DonorOnCall_Server.svg?branch=master)](https://travis-ci.org/donorcall01/DonorOnCall_Server)

The Server application for the Donor on Call Service.



### [Contribution Guide](https://github.com/donorcall01/DonorOnCall_Server/blob/master/CONTRIBUTING.md)

### [License](https://github.com/donorcall01/DonorOnCall_Server/blob/master/LICENSE.md)


### Setup And Installation

Requirements

* maven 3
* mysql-server 5.5
* java 1.7
* scala 2.11.7 // Installation is not Required


#### Sql Setup 

Update the Configuration File in `src/main/resources/application.conf`

Update Mysql with the [MySql Schema](db-schema.md)

### Starting the Server

If your using an IDE run the class com.donoroncall.server.BootStrapServer

The file is located in src/main/scala/com/donoroncall/server/BootStrapServer.scala

```

mvn install

# To Start the api Server From Terminal

mvn exec:java -Dexec.mainClass="com.donoroncall.server.BootStrapServer"

```
