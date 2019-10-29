plugins {
  java
  // Gradle plugin for handling jfx.
  id("application")
  id("org.openjfx.javafxplugin") version "0.0.8"
}

group = "xyz.kazuthecat.dogedice"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  // No dependencies yet. Except for JavaFX.
}

configure<JavaPluginConvention> {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

javafx {
  version = "11"
  modules("javafx.controls", "javafx.fxml")
}

