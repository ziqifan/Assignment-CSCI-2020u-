import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.Random;

public class ThreeCards extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        // generate 3 random cards and get their urls
        Random randomGenerator = new Random();
        int randomInt1 = randomGenerator.nextInt(54) + 1;
        int randomInt2 = randomGenerator.nextInt(54) + 1;
        int randomInt3 = randomGenerator.nextInt(54) + 1;
        String firstImage = "Cards/"+randomInt1+".png";
        String secondImage = "Cards/"+randomInt2+".png";
        String thirdImage = "Cards/"+randomInt3+".png";

        // create 3 cards' imageView
        Image image1 = new Image(firstImage);
        ImageView imageView1 = new ImageView(image1);
        Image image2 = new Image(secondImage);
        ImageView imageView2 = new ImageView(image2);
        Image image3 = new Image(thirdImage);
        ImageView imageView3 = new ImageView(image3);

        // add all 3 images to pane
        FlowPane pane = new FlowPane();
        pane.setPadding(new Insets(0,0,0,0));
        pane.setHgap(5);
        pane.setVgap(5);
        pane.getChildren().addAll(imageView1,imageView2,imageView3);

        // add pane to the scene and show the scene
        Scene scene = new Scene(pane, 226, 96);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Three Cards");
        primaryStage.show();
    }
}
