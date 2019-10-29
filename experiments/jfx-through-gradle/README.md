# JavaFX through Gradle
A simple Hello World-style JavaFX application which loads JavaFX through Gradle and launches the application through a helper class. The helper class ("Launcher") seems to be necessary because the Main class can not extend anything that isn't a module, and jar files apparently do not count as modules.

There are other solutions out there as well it seems, but this was the simplest and cleanest one to implement in my opinion.
