# 12ChipGame

# How to create an executable (windows)

Download jmod files from https://download2.gluonhq.com/openjfx/23.0.1/openjfx-23.0.1_windows-x64_bin-jmods.zip

Create a folder lib/javafx and put the extracted jmod files into it.

To package the program into 12ChipGame-1.0-SNAPSHOT.jar use the command:

    mvn clean package.

In the root directory of the game enter the following command:

    \jpackage.exe --win-console --input ./target --main-jar 12ChipGame-1.0-SNAPSHOT.jar --main-class com.frauas.javaproject.twelvechipgame.Main --name TwelveChipGame--module-path ./lib/javafx --add-modules javafx.controls,javafx.fxml,javafx.base,javafx.graphics,javafx.media --type app-image
