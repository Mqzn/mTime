# mTime

Simple time utility written in java that allows
developers to easily make things that require time calculations 
faster without any efforts

I personally have been using it for a year so far
it has no issues, but if you find any issues please
open an issue at the GitHub repository and provide it with the details
of the issue  you find, whether it's a bug or a suggestion
or whatever.

## Installation

If you're using a dependency manager software like maven or gradle
then this project is suitable for you.

### Step 1: Add the repository

###### Maven
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

###### Gradle
```groovy

repositories {
    mavenCentral()
    maven {
        url 'https://jitpack.io'
    }
}
```

### Step 2: Add the dependency

###### Maven
```xml
<dependency>
    <groupId>com.github.Mqzn</groupId>
    <artifactId>mTime</artifactId>
    <version>1.0</version>
</dependency>
```

###### Gradle
```groovy

dependencies {
    implementation 'com.github.mqzn:mTime:1.0'
}
```

### Step 3: Shade the dependency

###### Maven
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-shade-plugin</artifactId>
            <version>3.3.0</version>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                        <goal>shade</goal>
                    </goals>
                </execution>
            </executions>
            <configuration>
                <relocations>
                    <relocation>
                        <pattern>dev.mqzen.time</pattern>
                        <!-- Replace 'com.yourpackage' with the package of your plugin ! -->
                        <shadedPattern>com.yourpackage.time</shadedPattern>
                    </relocation>
                </relocations>
            </configuration>
        </plugin>
    </plugins>
</build>
```

###### Gradle
Dont forget to add the shadow jar plugin in your build.gradle.
It should be above at the top of the build.gradle file.
```groovy 
plugins {
    id 'com.github.johnrengelman.shadow' version '7.1.2'
}
```
Now let's configure the shadow jar plugin
to relocate the mTime dependency into your project.

```groovy
shadowJar {
    // Replace 'com.yourpackage' with the package of your plugin 
    relocate 'dev.mqzen.time', 'com.yourpackage.time'
}
```

## Usage

This is how you can take advantage of using
mTime library

### Parsing a time string
You can parse any string of any form that has text values
that indicate a time unit and a value

**Examples**
- "14d"
- "12d13h14m5s"
- "3654234d, 600hours, 700minutes, 911sec"

**How to parse**
```java
public class ExampleClass {

   public static void main(String[] args) {
	  // use the getters from the parser object
	  // for example: TimeParser#getDays gets the number of days in that time string
     
	  String input = "3654234d, 600hours, 700minutes, 911sec";
	  TimeParser parser = TimeParser.parse(input);
    
	  System.out.println("Days: " + parser.getDays());
	  System.out.println("Hours: " + parser.getHours());
	  System.out.println("Minutes: " + parser.getMinutes());
	  System.out.println("Seconds: " + parser.getSeconds());

   }
	 
}
```
### Time formatting
This library allows you to format any time parsed 
into a fancy looking text components
from [KyoriPowered Adventure Library](https://github.com/KyoriPowered/adventure)

You can format a time parser object or a time period directly 
or even raw values of days , hours, etc etc...
you can also format the time from a `LocalDateTime`

**Examples**
```
    LocalDateTime dateTime = LocalDateTime.now();
    TimeFormatter dateTimeFormatter = TimeFormatter.of(dateTime);
    TextComponent dateTimeFormat = dateTimeFormatter.format(false, NamedTextColor.YELLOW, NamedTextColor.GOLD);
    
    TimeFormatter stringFormatter = TimeFormatter.of("3654234d, 600hours, 700minutes, 911sec");
    TextComponent stringFormat = stringFormatter.format(false, NamedTextColor.YELLOW, NamedTextColor.GOLD );
		
```
### Extra information
This project has been built using JDK8, and
Iam the only author of this project.

Thanks for viewing this project !
