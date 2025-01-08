module com.frauas.javaproject.javafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.frauas.javaproject.twelvechipgame to javafx.fxml;
    exports com.frauas.javaproject.twelvechipgame;
    exports com.frauas.javaproject.twelvechipgame.controller;
    opens com.frauas.javaproject.twelvechipgame.controller to javafx.fxml;
}