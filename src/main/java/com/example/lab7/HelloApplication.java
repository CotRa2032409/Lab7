package com.example.lab7;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Lab 7");
        stage.setMaximized(true);
        CategoryAxis axeX = new CategoryAxis();
        NumberAxis axeY = new NumberAxis();
        axeX.setLabel("Mois");
        axeY.setLabel("Température (°C)");


        Menu importer = new Menu("Importer");
        Menu exporter = new Menu("Exporter");
        MenuItem test = new MenuItem("Exporter");
        MenuItem lignes = new MenuItem("Lignes");
        MenuItem zones = new MenuItem("Zones");
        MenuItem barres = new MenuItem("Barres");
        importer.getItems().addAll(lignes, zones, barres);
        exporter.getItems().add(test);

        MenuBar menuBar = new MenuBar(importer, exporter);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Veuillez sélectionner un fichier");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Fichiers data", "*.dat"),
                new FileChooser.ExtensionFilter(".png", "*.png"),
                new FileChooser.ExtensionFilter(".jpg", "*.jpg"),
                new FileChooser.ExtensionFilter(".gif", "*.gif"));

        lignes.setOnAction((ae) -> {
            File fichier = fileChooser.showOpenDialog(stage);
            List<String> data = null;
            try {
                data = Files.readAllLines(fichier.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            final LineChart<String, Number> root = new LineChart<>(axeX, axeY);
            root.setTitle("Température pour chaque mois");

            XYChart.Series series = new XYChart.Series();
            series.setName("Température");
            String[] list = data.get(0).split(",");
            String[] list2 = data.get(1).split(",");

            for (int i = 0; i < list.length; i++) {

                series.getData().add(new XYChart.Data(list[i], Double.parseDouble(list2[i])));
            }
            root.getData().addAll(series);
            borderPane.setCenter(root);
        });

        zones.setOnAction((ae) -> {
            File fichier = fileChooser.showOpenDialog(stage);
            List<String> data = null;
            try {
                data = Files.readAllLines(fichier.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            final AreaChart<String, Number> root = new AreaChart<>(axeX, axeY);
            root.setTitle("Température pour chaque mois");

            XYChart.Series series = new XYChart.Series();
            series.setName("Température");
            String[] list = data.get(0).split(",");
            String[] list2 = data.get(1).split(",");

            for (int i = 0; i < list.length; i++) {

                series.getData().add(new XYChart.Data(list[i], Double.parseDouble(list2[i])));
            }
            root.getData().addAll(series);
            borderPane.setCenter(root);
        });

        barres.setOnAction((ae) -> {
            File fichier = fileChooser.showOpenDialog(stage);
            List<String> data = null;
            try {
                data = Files.readAllLines(fichier.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            final BarChart<String, Number> root = new BarChart<>(axeX, axeY);
            root.setTitle("Température pour chaque mois");

            XYChart.Series series = new XYChart.Series();
            series.setName("Température");
            String[] list = data.get(0).split(",");
            String[] list2 = data.get(1).split(",");

            for (int i = 0; i < list.length; i++) {

                series.getData().add(new XYChart.Data(list[i], Double.parseDouble(list2[i])));
            }
            root.getData().addAll(series);
            borderPane.setCenter(root);
        });
        Scene scene = new Scene(borderPane);

        test.setOnAction((ae) -> {
            WritableImage image = borderPane.getCenter().snapshot(null, null);
            File file = fileChooser.showSaveDialog(stage);
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}