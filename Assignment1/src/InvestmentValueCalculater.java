import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InvestmentValueCalculater extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10,200,50,10));
        pane.setHgap(5);
        pane.setVgap(5);

        // Ivestment Amount
        pane.add(new javafx.scene.control.Label("Ivestment Amount "),0,0);
        TextField investmentInput = new TextField();
        investmentInput.setTextFormatter(new TextFormatter<String>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?[.]?([1-9][0-9]*)?")) {
                return change;
            }
            return null;
        }));
        pane.add(investmentInput,1,0);

        // Years
        pane.add(new javafx.scene.control.Label("Years "),0,1);
        TextField yearInput = new TextField();
        yearInput.setTextFormatter(new TextFormatter<String>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?[.]?([1-9][0-9]*)?")) {
                return change;
            }
            return null;
        }));
        pane.add(yearInput,1,1);

        // Annual Interest Rate
        pane.add(new javafx.scene.control.Label("Annual Interest Rate "),0,2);
        TextField insterestRateInput = new TextField();
        insterestRateInput.setTextFormatter(new TextFormatter<String>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?[.]?([1-9][0-9]*)?")) {
                return change;
            }
            return null;
        }));
        pane.add(insterestRateInput,1,2);

        // Future Value
        pane.add(new javafx.scene.control.Label("Future value "),0,3);
        TextField valueInput = new TextField();
        valueInput.setEditable(false);
        pane.add(valueInput,1,3);

        // Register Button
        Button btAdd = new Button("Calculate");

        btAdd.setOnAction(e-> {
            double invest = Float.parseFloat(investmentInput.getText());
            double year = Float.parseFloat(yearInput.getText());
            double interest = Float.parseFloat(insterestRateInput.getText());
            double temp = invest * Math.pow(1 + interest * 0.01 / 12, year * 12);

            valueInput.setText(String.valueOf(temp));
        });

        pane.add(btAdd,1,4);
        GridPane.setHalignment(btAdd, HPos.RIGHT);

        // Main Scene
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calculater");
        primaryStage.show();
    }
}
