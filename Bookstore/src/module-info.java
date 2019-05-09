module Bookstore {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.persistence;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.naming;

    opens sample;
    opens viewsControllers;
}