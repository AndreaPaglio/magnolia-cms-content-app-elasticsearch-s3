# Installation
For use that app, add dependency in your pom.xml:

    <dependency>
	  <groupId>net.seniorsoftwareengineer</groupId>
	  <artifactId>magnolia-cms-content-app-elasticsearch</artifactId>
	  <version>1.1</version>
	</dependency>

Add in your pom.xml the link to repository
```xml
<distributionManagement>
   <repository>
     <id>github</id>
     <name>GitHub OWNER Apache Maven Packages</name>
     <url>https://maven.pkg.github.com/AndreaPaglio/magnolia-cms-content-app-elasticsearch-s3</url>
   </repository>
</distributionManagement>
```
Build your solution with:

    mvn install

# Supported Magnolia versions
It tested with Magnolia 6.0 and major versions
