// Import the necessary packages
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;

// Create the main class that extends the Application class
public class App extends Application {
    private String fontName = "Helvetica";
    private Font font = Font.font(fontName, FontWeight.NORMAL, 15);
    private Font headerFont = Font.font("JetBrains Mono", FontWeight.BOLD, 17);
    private Font italicFont = Font.font(fontName, FontPosture.ITALIC, 15);
    private Font boldFont = Font.font(fontName, FontWeight.BOLD, 15);

    public static void main(String[] args) {
        launch(args);
    }

    // Override the start method
    @Override
    public void start(Stage mainStage) {
        // Set the size of the main stage and make it fixed
        mainStage.setWidth(650);
        mainStage.setHeight(450);
        mainStage.setResizable(false);

        // Set the title of the main stage
        mainStage.setTitle("BMI Calculator");

        // Using a grid pane as the main pane
        GridPane mainPane = new GridPane();
        mainPane.setHgap(10);
        mainPane.setVgap(10);
        mainPane.setPadding(new Insets(20));
        // Create column constraints
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50); 
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(50);
        // Add the column constraints to the GridPane
        mainPane.getColumnConstraints().addAll(column1, column2);

        // Create input labels
        Label titleLabel = new Label("————————  BMI Calculator  ———————");
        titleLabel.setFont(headerFont);
        Label genderLabel = new Label("Gender");
        Label weightLabel = new Label("Berat Bedan (kg)");
        Label heightLabel = new Label("Tinggi Badan (cm)");
        // Put the labels in the grid pane
        mainPane.add(titleLabel, 0, 0);
        GridPane.setColumnSpan(titleLabel, 2); // Span the title label to 2 columns
        GridPane.setHalignment(titleLabel, HPos.CENTER); // Center the output label horizontally
        mainPane.add(genderLabel, 0, 1);
        mainPane.add(weightLabel, 0, 2);
        mainPane.add(heightLabel, 0, 3);

        // Create input fields
        ChoiceBox<String> genderInput = new ChoiceBox<>();
        genderInput.getItems().addAll("Laki-laki", "Perempuan");
        TextField weightInput = new TextField();
        TextField heightInput = new TextField();
        // Set placeholder for input labels
        weightInput.setPromptText("Bilangan bulat 1-199");
        heightInput.setPromptText("Bilangan bulat 1-199");
        // Put the input fields in the grid pane
        mainPane.add(genderInput, 1, 1);
        mainPane.add(weightInput, 1, 2);
        mainPane.add(heightInput, 1, 3);

        // Create the output label
        Label outputLabel = new Label("————————————  Hasil  ———————————");
        outputLabel.setFont(headerFont);
        Label idealWeightLabel = new Label("Berat Badan Ideal");
        Label bmiLabel = new Label("Index Massa Tubuh (BMI)");
        Label classificationLabel = new Label("Klasifikasi");
        Label idealWeightOutput = new Label("...");
        Label bmiOutput = new Label("...");
        Label classificationOutput = new Label("...");
        // Set the output labels to wrap text
        idealWeightOutput.setWrapText(true);
        bmiOutput.setWrapText(true);
        classificationOutput.setWrapText(true);
        // Put the output labels in the grid pane
        mainPane.add(outputLabel, 0, 4);
        GridPane.setColumnSpan(outputLabel, 2); // Span the output label to 2 columns
        GridPane.setHalignment(outputLabel, HPos.CENTER); // Center the output label horizontally
        GridPane.setMargin(outputLabel, new Insets(20, 0, 0, 0)); // Set the margin for the output label
        mainPane.add(idealWeightLabel, 0, 5);
        mainPane.add(bmiLabel, 0, 6);
        mainPane.add(classificationLabel, 0, 7);
        mainPane.add(idealWeightOutput, 1, 5);
        mainPane.add(bmiOutput, 1, 6);
        mainPane.add(classificationOutput, 1, 7);

        // Create the compute button and exit button
        Button computeButton = new Button("Compute!");
        GridPane.setHalignment(computeButton, HPos.RIGHT); // Align the button to the right
        Button exitButton = new Button("Exit");

        // Event handler for the exit button
        computeButton.setOnAction(e -> {
            // Get the input values
            String gender = genderInput.getValue();
            String weight = weightInput.getText();
            String height = heightInput.getText();

            // Set the output values
            setIdealWeight(idealWeightOutput, height, gender);
            setBMI(bmiOutput, weight, height);
            setBMIClassification(classificationOutput, weight, height);
        });

        // Event handler for the exit button
        exitButton.setOnAction(e -> {
            mainStage.close();
        });

        // Set the font and color for the buttons
        computeButton.setStyle("-fx-background-color: #012a9f; -fx-text-fill: white; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 5, 0, 0, 1);");
        exitButton.setStyle("-fx-background-color: #ff2301; -fx-text-fill: white; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 5, 0, 0, 1);");
        computeButton.setFont(boldFont);
        exitButton.setFont(boldFont);
        // Set on click effect for the buttons
        computeButton.setOnMousePressed(e -> computeButton.setStyle("-fx-background-color: #011b67; -fx-text-fill: white; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 2, 0, 0, 1);"));
        computeButton.setOnMouseReleased(e -> computeButton.setStyle("-fx-background-color: #012a9f; -fx-text-fill: white; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 5, 0, 0, 1);"));
        exitButton.setOnMousePressed(e -> exitButton.setStyle("-fx-background-color: #c41a00; -fx-text-fill: white; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 2, 0, 0, 1);"));
        exitButton.setOnMouseReleased(e -> exitButton.setStyle("-fx-background-color: #ff2301; -fx-text-fill: white; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 5, 0, 0, 1);"));
        // Set the buttons size
        computeButton.setPrefSize(100, 20);
        exitButton.setPrefSize(100, 20);
        // Put the buttons in the grid pane
        mainPane.add(computeButton, 0, 8);
        mainPane.add(exitButton, 1, 8);
        // Make the button go lower
        GridPane.setMargin(computeButton, new Insets(30, 0, 0, 0));
        GridPane.setMargin(exitButton, new Insets(30, 0, 0, 0));

        // Set the font for the rest of the labels
        genderLabel.setFont(font);
        weightLabel.setFont(font);
        heightLabel.setFont(font);
        idealWeightLabel.setFont(font);
        bmiLabel.setFont(font);
        classificationLabel.setFont(font);

        // Set the font for the input fields
        heightInput.setFont(font);
        weightInput.setFont(font);
        genderInput.setStyle("-fx-font: 15px \"" + fontName + "\";");

        // Set the font color for all the labels and output put fields
        titleLabel.setTextFill(Color.web("#00ff86"));
        genderLabel.setTextFill(Color.WHITE);
        outputLabel.setTextFill(Color.web("#00ff86"));
        heightLabel.setTextFill(Color.WHITE);
        weightLabel.setTextFill(Color.WHITE);
        idealWeightLabel.setTextFill(Color.WHITE);
        bmiLabel.setTextFill(Color.WHITE);
        classificationLabel.setTextFill(Color.WHITE);
        idealWeightOutput.setTextFill(Color.WHITE);
        bmiOutput.setTextFill(Color.WHITE);
        classificationOutput.setTextFill(Color.WHITE);

        // Styling the main pane
        mainPane.setStyle("-fx-background-color: #00162e; -fx-border-color: #00ff86; -fx-border-width: 3px;");

        // Create new scene
        Scene mainScene = new Scene(mainPane, 200, 600);
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    // Method to calculate the ideal weight
    private void setIdealWeight(Label label, String height, String gender) {
        // Reset the font
        label.setFont(font);

        // Use try-catch to validate if the height is not a number
        try {
            Double.parseDouble(height);
        } catch (NumberFormatException e) {
            label.setText("Input tinggi badan invalid!");
            label.setFont(italicFont);
            return;
        }
        double heightDouble = Double.parseDouble(height);
        // Validate if the height is negative
        if (heightDouble <= 0 || heightDouble >= 200) {
            label.setText("Tinggi badan harus antara 1-199 cm!");
            label.setFont(italicFont);
            return;
        }
        // Validate if the height is not integer
        if (heightDouble % 1 != 0) {
            label.setText("Tinggi badan harus bilangan bulat!");
            label.setFont(italicFont);
            return;
        }

        // Calculate the ideal weight
        double idealWeight;
        if (gender.equals("Laki-laki")) {
            idealWeight = (heightDouble - 100) - ((heightDouble - 100) * 0.1);
            label.setText(String.format("%.2f kg", idealWeight));
        } else {
            idealWeight = (heightDouble - 100) - ((heightDouble - 100) * 0.15);
            label.setText(String.format("%.2f kg", idealWeight));
        }
    }

    // Method to calculate the BMI
    private void setBMI(Label label, String weight, String height) {
        // Reset the font
        label.setFont(font);

        // Use try-catch to validate if the height or the height is not a number
        try {
            Double.parseDouble(height);
            Double.parseDouble(weight);
        } catch (NumberFormatException e) {
            label.setText("Input tinggi atau berat badan invalid!");
            label.setFont(italicFont);
            return;
        }
        double heightDouble = Double.parseDouble(height);
        double weightDouble = Double.parseDouble(weight);
        // Validate if the height is negative
        if (heightDouble <= 0 || heightDouble >= 200) {
            label.setText("Tinggi badan harus antara 1-199 cm!");
            label.setFont(italicFont);
            return;
        }
        // Validate if the height is not integer
        if (heightDouble % 1 != 0) {
            label.setText("Tinggi badan harus bilangan bulat!");
            label.setFont(italicFont);
            return;
        }

        // Validate if the weight is negative
        if (weightDouble <= 0 || weightDouble >= 200) {
            label.setText("Berat badan harus antara 1-199 kg!");
            label.setFont(italicFont);
            return;
        }
        // Validate if the weight is not integer
        if (weightDouble % 1 != 0) {
            label.setText("Berat badan harus bilangan bulat!");
            label.setFont(italicFont);
            return;
        }

        // Calculate the BMI
        double bmi = (weightDouble * 10000) / (heightDouble * heightDouble);
        label.setText(String.format("%.2f", bmi));
    }

    // Method to get the BMI classification
    private void setBMIClassification(Label label, String weight, String height) {
        // Reset the font
        label.setTextFill(Color.WHITE);
        label.setFont(font);

        // Use try-catch to validate if the height or the weight is not a number
        try {
            Double.parseDouble(height);
            Double.parseDouble(weight);
        } catch (NumberFormatException e) {
            label.setText("Input tinggi atau berat badan invalid!");
            label.setFont(italicFont);
            return;
        }
        double weightDouble = Double.parseDouble(weight);
        double heightDouble = Double.parseDouble(height);
        // Validate if the height is negative
        if (heightDouble <= 0 || heightDouble >= 200) {
            label.setText("Tinggi badan harus antara 1-199 cm!");
            label.setFont(italicFont);
            return;
        }
        // Validate if the height is not integer
        if (heightDouble % 1 != 0) {
            label.setText("Tinggi badan harus bilangan bulat!");
            label.setFont(italicFont);
            return;
        }
        // Validate if the weight is negative
        if (weightDouble <= 0 || weightDouble >= 200) {
            label.setText("Berat badan harus antara 1-199 kg!");
            label.setFont(italicFont);
            return;
        }
        // Validate if the weight is not integer
        if (weightDouble % 1 != 0) {
            label.setText("Berat badan harus bilangan bulat!");
            label.setFont(italicFont);
            return;
        }

        double bmi = (weightDouble * 10000) / (heightDouble * heightDouble);
        if (bmi < 18.5) {
            label.setText("Underweight");
            label.setTextFill(Color.BLUE);
        } else if (bmi < 25) {
            label.setText("Normal");
            label.setTextFill(Color.GREEN);
        } else if (bmi < 30) {
            label.setText("Overweight");
            label.setTextFill(Color.YELLOW);
        } else {
            label.setText("Obese");
            label.setTextFill(Color.RED);
        }
        label.setFont(boldFont);
    }
}