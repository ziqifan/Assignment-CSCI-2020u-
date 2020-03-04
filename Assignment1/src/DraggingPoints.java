import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DraggingPoints extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();

        // draw circle
        double circleRadius = 200;
        Circle circle = new Circle();
        circle.setRadius(circleRadius);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);
        circle.centerXProperty().bind(pane.widthProperty().divide(2));
        circle.centerYProperty().bind(pane.heightProperty().divide(2));

        // draw first point
        double randAngle1 = Math.toRadians(Math.random() * 361);
        double randX1 = Math.sin(randAngle1) * circleRadius;
        double randY1 = Math.cos(randAngle1) * circleRadius;
        Circle point1 = new Circle();
        point1.setRadius(10);
        point1.setStroke(Color.BLACK);
        point1.setFill(Color.RED);
        point1.centerXProperty().bind(pane.widthProperty().divide(2).add(randX1));
        point1.centerYProperty().bind(pane.heightProperty().divide(2).add(randY1));

        // draw second point
        double randAngle2 = Math.toRadians(Math.random() * 361);
        double randX2 = Math.sin(randAngle2) * circleRadius;
        double randY2 = Math.cos(randAngle2) * circleRadius;
        Circle point2 = new Circle();
        point2.setRadius(10);
        point2.setStroke(Color.BLACK);
        point2.setFill(Color.RED);
        point2.centerXProperty().bind(pane.widthProperty().divide(2).add(randX2));
        point2.centerYProperty().bind(pane.heightProperty().divide(2).add(randY2));

        // draw third point
        double randAngle3 = Math.toRadians(Math.random() * 361);
        double randX3 = Math.sin(randAngle3) * circleRadius;
        double randY3 = Math.cos(randAngle3) * circleRadius;
        Circle point3 = new Circle();
        point3.setRadius(10);
        point3.setStroke(Color.BLACK);
        point3.setFill(Color.RED);
        point3.centerXProperty().bind(pane.widthProperty().divide(2).add(randX3));
        point3.centerYProperty().bind(pane.heightProperty().divide(2).add(randY3));

        // draw first line
        Line line1 = new Line();
        line1.startXProperty().bind(pane.widthProperty().divide(2).add(point1.getCenterX()));
        line1.startYProperty().bind(pane.heightProperty().divide(2).add(point1.getCenterY()));
        line1.endXProperty().bind(pane.widthProperty().divide(2).add(point2.getCenterX()));
        line1.endYProperty().bind(pane.heightProperty().divide(2).add(point2.getCenterY()));

        // draw second line
        Line line2 = new Line();
        line2.startXProperty().bind(pane.widthProperty().divide(2).add(point2.getCenterX()));
        line2.startYProperty().bind(pane.heightProperty().divide(2).add(point2.getCenterY()));
        line2.endXProperty().bind(pane.widthProperty().divide(2).add(point3.getCenterX()));
        line2.endYProperty().bind(pane.heightProperty().divide(2).add(point3.getCenterY()));

        // draw third line
        Line line3 = new Line();
        line3.startXProperty().bind(pane.widthProperty().divide(2).add(point3.getCenterX()));
        line3.startYProperty().bind(pane.heightProperty().divide(2).add(point3.getCenterY()));
        line3.endXProperty().bind(pane.widthProperty().divide(2).add(point1.getCenterX()));
        line3.endYProperty().bind(pane.heightProperty().divide(2).add(point1.getCenterY()));

        // calculate centre point
        double centrePointX = (point1.getCenterX() + point2.getCenterX() + point3.getCenterX()) / 3;
        double centrePointY = (point1.getCenterY() + point2.getCenterY() + point3.getCenterY()) / 3;

        // draw point 1 angle text
        Text t1 = new Text(getAngle(point1.getCenterX(), point1.getCenterY(), point2.getCenterX(), point2.getCenterY(), point3.getCenterX(), point3.getCenterY()));
        t1.xProperty().bind(pane.widthProperty().divide(2).add(point1.getCenterX() + normalizeX(point1.getCenterX(), point1.getCenterY(), centrePointX, centrePointY) * 30));
        t1.yProperty().bind(pane.heightProperty().divide(2).add(point1.getCenterY() + normalizeY(point1.getCenterX(), point1.getCenterY(), centrePointX, centrePointY) * 30));

        // draw point 2 angle text
        Text t2 = new Text(getAngle(point2.getCenterX(), point2.getCenterY(), point3.getCenterX(), point3.getCenterY(), point1.getCenterX(), point1.getCenterY()));
        t2.xProperty().bind(pane.widthProperty().divide(2).add(point2.getCenterX() + normalizeX(point2.getCenterX(), point2.getCenterY(), centrePointX, centrePointY) * 30));
        t2.yProperty().bind(pane.heightProperty().divide(2).add(point2.getCenterY() + normalizeY(point2.getCenterX(), point2.getCenterY(), centrePointX, centrePointY) * 30));

        // draw point 3 angle text
        Text t3 = new Text(getAngle(point3.getCenterX(), point3.getCenterY(), point1.getCenterX(), point1.getCenterY(), point2.getCenterX(), point2.getCenterY()));
        t3.xProperty().bind(pane.widthProperty().divide(2).add(point3.getCenterX() + normalizeX(point3.getCenterX(), point3.getCenterY(), centrePointX, centrePointY) * 30));
        t3.yProperty().bind(pane.heightProperty().divide(2).add(point3.getCenterY() + normalizeY(point3.getCenterX(), point3.getCenterY(), centrePointX, centrePointY) * 30));

        // update when mouse drag point 1
        point1.setOnMouseDragged(e -> {
            // update point 1 position
            double x = e.getX() - pane.getWidth()/2;
            double y = e.getY() - pane.getHeight()/2;
            double angle = Math.atan(y/x);
            double newX = Math.cos(angle) * circleRadius;
            double newY = Math.sin(angle) * circleRadius;
            if(x>= 0)
            {
                point1.centerXProperty().bind(pane.widthProperty().divide(2).add(newX));
                point1.centerYProperty().bind(pane.widthProperty().divide(2).add(newY));
            }else{
                point1.centerXProperty().bind(pane.widthProperty().divide(2).add(-newX));
                point1.centerYProperty().bind(pane.widthProperty().divide(2).add(-newY));
            }

            // update connect lines
            line1.startXProperty().bind(pane.widthProperty().divide(2).add(point1.getCenterX() - pane.getWidth()/2));
            line1.startYProperty().bind(pane.heightProperty().divide(2).add(point1.getCenterY()- pane.getHeight()/2));
            line3.endXProperty().bind(pane.widthProperty().divide(2).add(point1.getCenterX() - pane.getWidth()/2));
            line3.endYProperty().bind(pane.heightProperty().divide(2).add(point1.getCenterY()- pane.getHeight()/2));

            // calculate new centre
            double centrePX = (point1.getCenterX() + point2.getCenterX() + point3.getCenterX()) / 3;
            double centrePY = (point1.getCenterY() + point2.getCenterY() + point3.getCenterY()) / 3;

            // update text
            t1.setText(getAngle(point1.getCenterX(), point1.getCenterY(), point2.getCenterX(), point2.getCenterY(), point3.getCenterX(), point3.getCenterY()));
            t1.xProperty().bind(pane.widthProperty().divide(2).add(point1.getCenterX() + normalizeX(point1.getCenterX(), point1.getCenterY(), centrePX, centrePY) * 30 - pane.getWidth()/2));
            t1.yProperty().bind(pane.heightProperty().divide(2).add(point1.getCenterY() + normalizeY(point1.getCenterX(), point1.getCenterY(), centrePX, centrePY) * 30 - pane.getWidth()/2));
            t2.setText(getAngle(point2.getCenterX(), point2.getCenterY(), point3.getCenterX(), point3.getCenterY(), point1.getCenterX(), point1.getCenterY()));
            t2.xProperty().bind(pane.widthProperty().divide(2).add(point2.getCenterX() + normalizeX(point2.getCenterX(), point2.getCenterY(), centrePX, centrePY) * 30 - pane.getWidth()/2));
            t2.yProperty().bind(pane.heightProperty().divide(2).add(point2.getCenterY() + normalizeY(point2.getCenterX(), point2.getCenterY(), centrePX, centrePY) * 30 - pane.getWidth()/2));
            t3.setText(getAngle(point3.getCenterX(), point3.getCenterY(), point1.getCenterX(), point1.getCenterY(), point2.getCenterX(), point2.getCenterY()));
            t3.xProperty().bind(pane.widthProperty().divide(2).add(point3.getCenterX() + normalizeX(point3.getCenterX(), point3.getCenterY(), centrePX, centrePY) * 30 - pane.getWidth()/2));
            t3.yProperty().bind(pane.heightProperty().divide(2).add(point3.getCenterY() + normalizeY(point3.getCenterX(), point3.getCenterY(), centrePX, centrePY) * 30 - pane.getWidth()/2));
        });

        // update when mouse drag point 2
        point2.setOnMouseDragged(e -> {
            // update point 1 position
            double x = e.getX() - pane.getWidth()/2;
            double y = e.getY() - pane.getHeight()/2;
            double angle = Math.atan(y/x);
            double newX = Math.cos(angle) * circleRadius;
            double newY = Math.sin(angle) * circleRadius;
            if(x>= 0)
            {
                point2.centerXProperty().bind(pane.widthProperty().divide(2).add(newX));
                point2.centerYProperty().bind(pane.widthProperty().divide(2).add(newY));
            }else{
                point2.centerXProperty().bind(pane.widthProperty().divide(2).add(-newX));
                point2.centerYProperty().bind(pane.widthProperty().divide(2).add(-newY));
            }

            // update connect lines
            line1.endXProperty().bind(pane.widthProperty().divide(2).add(point2.getCenterX() - pane.getWidth()/2));
            line1.endYProperty().bind(pane.heightProperty().divide(2).add(point2.getCenterY()- pane.getHeight()/2));
            line2.startXProperty().bind(pane.widthProperty().divide(2).add(point2.getCenterX() - pane.getWidth()/2));
            line2.startYProperty().bind(pane.heightProperty().divide(2).add(point2.getCenterY()- pane.getHeight()/2));

            // calculate new centre
            double centrePX = (point1.getCenterX() + point2.getCenterX() + point3.getCenterX()) / 3;
            double centrePY = (point1.getCenterY() + point2.getCenterY() + point3.getCenterY()) / 3;

            // update text
            t1.setText(getAngle(point1.getCenterX(), point1.getCenterY(), point2.getCenterX(), point2.getCenterY(), point3.getCenterX(), point3.getCenterY()));
            t1.xProperty().bind(pane.widthProperty().divide(2).add(point1.getCenterX() + normalizeX(point1.getCenterX(), point1.getCenterY(), centrePX, centrePY) * 30 - pane.getWidth()/2));
            t1.yProperty().bind(pane.heightProperty().divide(2).add(point1.getCenterY() + normalizeY(point1.getCenterX(), point1.getCenterY(), centrePX, centrePY) * 30 - pane.getWidth()/2));
            t2.setText(getAngle(point2.getCenterX(), point2.getCenterY(), point3.getCenterX(), point3.getCenterY(), point1.getCenterX(), point1.getCenterY()));
            t2.xProperty().bind(pane.widthProperty().divide(2).add(point2.getCenterX() + normalizeX(point2.getCenterX(), point2.getCenterY(), centrePX, centrePY) * 30 - pane.getWidth()/2));
            t2.yProperty().bind(pane.heightProperty().divide(2).add(point2.getCenterY() + normalizeY(point2.getCenterX(), point2.getCenterY(), centrePX, centrePY) * 30 - pane.getWidth()/2));
            t3.setText(getAngle(point3.getCenterX(), point3.getCenterY(), point1.getCenterX(), point1.getCenterY(), point2.getCenterX(), point2.getCenterY()));
            t3.xProperty().bind(pane.widthProperty().divide(2).add(point3.getCenterX() + normalizeX(point3.getCenterX(), point3.getCenterY(), centrePX, centrePY) * 30 - pane.getWidth()/2));
            t3.yProperty().bind(pane.heightProperty().divide(2).add(point3.getCenterY() + normalizeY(point3.getCenterX(), point3.getCenterY(), centrePX, centrePY) * 30 - pane.getWidth()/2));
        });

        // update when mouse drag point 3
        point3.setOnMouseDragged(e -> {
            // update point 1 position
            double x = e.getX() - pane.getWidth()/2;
            double y = e.getY() - pane.getHeight()/2;
            double angle = Math.atan(y/x);
            double newX = Math.cos(angle) * circleRadius;
            double newY = Math.sin(angle) * circleRadius;
            if(x>= 0)
            {
                point3.centerXProperty().bind(pane.widthProperty().divide(2).add(newX));
                point3.centerYProperty().bind(pane.widthProperty().divide(2).add(newY));
            }else{
                point3.centerXProperty().bind(pane.widthProperty().divide(2).add(-newX));
                point3.centerYProperty().bind(pane.widthProperty().divide(2).add(-newY));
            }

            // update connect lines
            line2.endXProperty().bind(pane.widthProperty().divide(2).add(point3.getCenterX() - pane.getWidth()/2));
            line2.endYProperty().bind(pane.heightProperty().divide(2).add(point3.getCenterY()- pane.getHeight()/2));
            line3.startXProperty().bind(pane.widthProperty().divide(2).add(point3.getCenterX() - pane.getWidth()/2));
            line3.startYProperty().bind(pane.heightProperty().divide(2).add(point3.getCenterY()- pane.getHeight()/2));

            // calculate new centre
            double centrePX = (point1.getCenterX() + point2.getCenterX() + point3.getCenterX()) / 3;
            double centrePY = (point1.getCenterY() + point2.getCenterY() + point3.getCenterY()) / 3;

            // update text
            t1.setText(getAngle(point1.getCenterX(), point1.getCenterY(), point2.getCenterX(), point2.getCenterY(), point3.getCenterX(), point3.getCenterY()));
            t1.xProperty().bind(pane.widthProperty().divide(2).add(point1.getCenterX() + normalizeX(point1.getCenterX(), point1.getCenterY(), centrePX, centrePY) * 30 - pane.getWidth()/2));
            t1.yProperty().bind(pane.heightProperty().divide(2).add(point1.getCenterY() + normalizeY(point1.getCenterX(), point1.getCenterY(), centrePX, centrePY) * 30 - pane.getWidth()/2));
            t2.setText(getAngle(point2.getCenterX(), point2.getCenterY(), point3.getCenterX(), point3.getCenterY(), point1.getCenterX(), point1.getCenterY()));
            t2.xProperty().bind(pane.widthProperty().divide(2).add(point2.getCenterX() + normalizeX(point2.getCenterX(), point2.getCenterY(), centrePX, centrePY) * 30 - pane.getWidth()/2));
            t2.yProperty().bind(pane.heightProperty().divide(2).add(point2.getCenterY() + normalizeY(point2.getCenterX(), point2.getCenterY(), centrePX, centrePY) * 30 - pane.getWidth()/2));
            t3.setText(getAngle(point3.getCenterX(), point3.getCenterY(), point1.getCenterX(), point1.getCenterY(), point2.getCenterX(), point2.getCenterY()));
            t3.xProperty().bind(pane.widthProperty().divide(2).add(point3.getCenterX() + normalizeX(point3.getCenterX(), point3.getCenterY(), centrePX, centrePY) * 30 - pane.getWidth()/2));
            t3.yProperty().bind(pane.heightProperty().divide(2).add(point3.getCenterY() + normalizeY(point3.getCenterX(), point3.getCenterY(), centrePX, centrePY) * 30 - pane.getWidth()/2));
        });


        pane.getChildren().addAll(circle, line1, line2, line3, point1, point2, point3, t1, t2, t3);

        // Main Scene
        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dragging Points On Circle");
        primaryStage.show();
    }

    double length(double x1, double y1, double x2, double y2){
        double x = Math.abs(x1 - x2);
        double y = Math.abs(y1 - y2);
        return Math.sqrt(x * x + y * y);
    }

    String getAngle(double p1x, double p1y, double p2x, double p2y, double p3x, double p3y){
        double length1 = length(p1x, p1y, p2x, p2y);
        double length2 = length(p2x, p2y, p3x, p3y);
        double length3 = length(p3x, p3y, p1x, p1y);
        double angle = Math.toDegrees(Math.acos((length2 * length2 - length1 * length1 - length3 * length3) / (-2 * length1 * length3)));
        return Integer.toString((int) angle);
    }

    double normalizeX(double x1, double y1, double x2, double y2){
        double vx = x2 - x1;
        double vy = y2 - y1;
        double vabs = Math.sqrt(vx * vx + vy * vy);
        return vx/vabs;
    }

    double normalizeY(double x1, double y1, double x2, double y2){
        double vx = x2 - x1;
        double vy = y2 - y1;
        double vabs = Math.sqrt(vx * vx + vy * vy);
        return vy/vabs;
    }
}
