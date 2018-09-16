# Warp Reader is a project to support fast reading

## warp-reader-ui-vaadin
Warp Reader Ui Vaadin is a implementation of the Warp-Reader-Core view api.

![Alt Text](http://www.warpreader.trundicho.de/WarpReader.gif)


## Demo
See Warp Reader in action here:
http://warpreader.trundicho.de

## Drag and drop Url's or copy and paste the text to read in the text area.
[![IMAGE ALT TEXT HERE](http://img.youtube.com/vi/DlbKjgIBs-k/0.jpg)](http://www.youtube.com/watch?v=DlbKjgIBs-k)

## Developer documentation
The warp-reader-ui-vaadin component is based on vaadin and spring-boot.  
Because the timer widget is a gwt ported component it is necessary to build the module with maven "clean install".  
The default profile in the pom builds a war that is deployable in tomcat.  
There is also a jetty profile if you want to use jetty.  
