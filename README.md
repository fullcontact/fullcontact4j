#FullContact4j

A Java client for the [FullContact API](http://www.fullcontact.com/docs)


##Maven
To fetch FullContact4j from Maven, you'll need to connect to FullContact's public and fetch the artifact like this:
```xml
<repositories>
  <repository>
    <id>fullcontact</id>
    <url>http://fullcontact.artifactoryonline.com/fullcontact/repo</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>com.fullcontact</groupId>
    <artifactId>fullcontact4j</artifactId>
    <version>${version}</version>
  </dependency>
</dependencies>
```

##Gradle
```groovy
repositories {
    maven {
        url "http://fullcontact.artifactoryonline.com/fullcontact/repo"
    }
}

dependencies {
    compile group: "com.fullcontact:fullcontact4j:${version}"
}
```


##Usage:

* [Person API](/fullcontact/fullcontact4j/tree/refactoring/docs/person/)
* [Person Enhanced Data API](/fullcontact/fullcontact4j/tree/refactoring/docs/enhancedData/)
* [Name API](/fullcontact/fullcontact4j/tree/refactoring/docs/name/)
* [Location API](/fullcontact/fullcontact4j/tree/refactoring/docs/location/)
* [CardShark API](/fullcontact/fullcontact4j/tree/refactoring/docs/cardShark/)
* [Batch API](/fullcontact/fullcontact4j/tree/refactoring/docs/batch/)
* [Email API](/fullcontact/fullcontact4j/tree/refactoring/docs/email/)
* [Icon API](/fullcontact/fullcontact4j/tree/refactoring/docs/icon/)

##Dependencies
* Google GSON (http://code.google.com/p/google-gson/)
