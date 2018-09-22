# Warp Reader

Warp Reader is a project to support fast reading. This repository has the Warp-Reader-Core and multiple ui implementations.

![Alt Text](http://www.warpreader.trundicho.de/WarpReader.gif)

## Demo

See Warp Reader in action here:
http://warpreader.trundicho.de

Drag and drop Url's or copy and paste the text to read in the text area.
[![IMAGE ALT TEXT HERE](http://img.youtube.com/vi/DlbKjgIBs-k/0.jpg)](http://www.youtube.com/watch?v=DlbKjgIBs-k)

## Developer documentation warp-reader-ui-vaadin
The warp-reader-ui-vaadin component is based on vaadin and spring-boot.  
Because the timer widget is a gwt ported component it is necessary to build the module with vaadin-maven-plugin 
already configured in the pom.xml.  
The default profile in the pom builds a war that is deployable in tomcat.  
There is also a jetty profile if you want to use jetty.  

## Developer documentation warp-reader-ui-gwt
The warp-reader-ui-gwt component is based on gwt.
The default profile in the pom builds a war that is deployable in jetty.

## Getting Started
What things you need to install the software and how to install them

### Prerequisites

- Java 8 JDK
- A JAVA IDE
- Vaadin 8 or
- GWT

### Installing and running warp-reader-ui-vaadin

1. Clone this repository
2. In warp-reader pom.xml run `mvn clean install`
3. Start WarpReaderApplication class from your IDE. Spring-Boot Tomcat will be started.
4. Open browser at localhost:8080 and use WarpReader

### Installing and running warp-reader-ui-gwt

1. Clone this repository
2. In warp-reader pom.xml run `mvn clean install -Pwarp-reader-gwt`
3. Start gwt application class from your IDE with maven plugins gwt:devmode
4. Open browser at http://127.0.0.1:8888/ and use WarpReader

### Deployment

Just run the upper maven commands to generate war files for tomcat or jetty.  
The default profile in the vaadin-ui pom.xml builds a tomcat ready war to be run from within your IDE.  
There is also a jetty profile `vaadin-jetty` in the vaadin-ui pom.xml that builds a jetty ready war in the target folder.

## Built With

* [Spring-Boot](http://spring.io/projects/spring-boot) - Spring-Boot
* [Vaadin](https://vaadin.com/docs/v8/framework/tutorial.html) - The vaadin-8 web framework used
* [GWT](http://www.gwtproject.org/) - The GWT 2.8 framework used
* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Angelo Romito** - *Initial work* - [Trundicho](https://github.com/Trundicho)

## License

This project is licensed under Apache License Version 2.0 - see the [LICENSE](LICENSE) file for details
