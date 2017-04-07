name := """coco-backend"""

version := "1.0-SNAPSHOT"

//To consider new dependencies go to cmd and type:
//1. reload; 2. eclipse with-source=true
//More info: https://www.playframework.com/documentation/2.5.x/JavaEbean
lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  evolutions,
  filters,
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc4",
  "com.amazonaws" % "aws-java-sdk" % "1.3.11"
)

// Compile the project before generating Eclipse files, so that generated .scala or .class files for views and routes are present
EclipseKeys.preTasks := Seq(compile in Compile)

// Java project. Don't expect Scala IDE
EclipseKeys.projectFlavor := EclipseProjectFlavor.Java  
         
// Use .class files instead of generated .scala files for views and routes 
EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(EclipseCreateSrc.ManagedClasses, EclipseCreateSrc.ManagedResources) 