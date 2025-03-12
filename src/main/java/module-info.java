module org.example.electonicdevicesjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens org.example.electonicdevicesjavafx to javafx.fxml;
    exports org.example.electonicdevicesjavafx;
    exports org.example.electonicdevicesjavafx.model;
    opens org.example.electonicdevicesjavafx.model to javafx.fxml;
    exports org.example.electonicdevicesjavafx.service;
    opens org.example.electonicdevicesjavafx.service to javafx.fxml;
    exports org.example.electonicdevicesjavafx.controller;
    opens org.example.electonicdevicesjavafx.controller to javafx.fxml;
}