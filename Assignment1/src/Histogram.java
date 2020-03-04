import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

import java.io.File;
import java.util.Scanner;

public class Histogram extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FlowPane pane = new FlowPane();
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));

        // Open the file
        File sourceFile = new File("Random.txt");
        if (!sourceFile.exists()) {
            System.out.println("Target file is not exist or cannot read.");
            System.exit(0);
        }
        Scanner input = new Scanner(sourceFile);
        while (input.hasNext()){
            String temp = input.nextLine();
            for(int i = 0; i < 26; i++) {
                numberOfLetterType[i] += temp.length() - temp.replaceAll(letterType[i], "").length();
                numberOfLetterType[i] += temp.length() - temp.replaceAll(letterType[i].toLowerCase(), "").length();
            }
        }

        // Create bar chart
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Letters");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Occurrences");
        BarChart letterChart = new BarChart(xAxis, yAxis);
        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("Letter Occurrences In Text");
        for(int i = 0; i < 26; i++) {
            dataSeries.getData().add(new XYChart.Data(letterType[i], numberOfLetterType[i]));
        }
        letterChart.getData().add(dataSeries);
        letterChart.setBarGap(0);
        letterChart.setCategoryGap(2);

        // File name
        hBox.getChildren().add(new javafx.scene.control.Label("File Name: "));
        TextField fileInput = new TextField();
        fileInput.setPrefColumnCount(32);
        hBox.getChildren().add(fileInput);

        // Process Button
        Button bt = new Button("View");
        bt.setOnAction(e -> {
            File myFile = new File(fileInput.getText());
            if (!myFile.exists()) {
                System.out.println("Target file is not exist or cannot read.");
                System.exit(0);
            }
            try {
                Scanner in = new Scanner(myFile);
                for(int i = 0; i < 26; i++) {
                    numberOfLetterType[i] = 0;
                }
                while (in.hasNext()){
                    String temp = in.nextLine();
                    for(int i = 0; i < 26; i++) {
                        numberOfLetterType[i] += temp.length() - temp.replaceAll(letterType[i], "").length();
                        numberOfLetterType[i] += temp.length() - temp.replaceAll(letterType[i].toLowerCase(), "").length();
                    }
                }
            }
            catch (FileNotFoundException a){
                a.printStackTrace();
            }
            for(int i = 0; i < 26; i++) {
                dataSeries.getData().set(i, new XYChart.Data(letterType[i], numberOfLetterType[i]));
            }
        });
        hBox.getChildren().add(bt);

        pane.getChildren().addAll(letterChart, hBox);

        // Main Scene
        Scene scene = new Scene(pane, 500, 455);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Histogram");
        primaryStage.show();
    }

    // Data for the Bar Chart
    private static String[] letterType = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private static int[] numberOfLetterType = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
}
