import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CalculatorStringApp extends Application {

    private TextField field;
    private Button button;
    private Label labHeading;
    private Label labResult;
    private Label labInput;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Калькулятор");

        final double rem = new Text("").getBoundsInParent().getHeight();

        VBox rootNode = new VBox(0.8 * rem);
        rootNode.setAlignment(Pos.CENTER);
        rootNode.setPadding(new Insets(0.8 * rem));

        Scene myScene = new Scene(rootNode, 500, 200);

        primaryStage.setScene(myScene);

        field = new TextField();
        field.setPromptText("Введите выражение");
        field.setPrefColumnCount(25);

        button = new Button("Рассчитать");

        labHeading = new Label("Введите выражение. Допускаются символы +-*/():");
        labInput = new Label("");
        labResult = new Label("");


        button.setOnAction((ae) -> {
            String result = Calculator.calculator(field.getText());
            if (result == null) {
                labResult.setText("Вы ввели какую-то хрень. Попробуйте ещё раз!");
            } else {
                labResult.setText("Результат: " + result);
            }
            labInput.setText("Вы ввели: " + field.getText());
            field.clear();
        });

        field.setOnAction((ae) -> button.fire());

        rootNode.getChildren().addAll(labHeading, field, button, labInput, labResult);

        primaryStage.show();
    }
}
