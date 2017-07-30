package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.*;




public class Controller implements Initializable {

    double dailyCaloriesNeeded = 0;
    double caloriesToAchieveGoal = 0;

    double BMR = 0;  // podstawowa przemiana materi
    double TEA = 0;  // kalorie z treningów
    double EPOC = 0; // kalorie spalane po treningu

    int NEAT = 0;  // ekto, mezo , endo typ budowy ciała + 200-900kcal

    double TDEE =0; // wartość końcowa - ile kcal na utrzymanie wagi

    // *1,1 - efekt termiczny porzywienia - kcal na trawienie

    // TDEE = (BMR + TEA + EPOC + NEAT)*1,1

    @FXML
    private Button buttonPro;
    @FXML
    private TextField textWeight;
    @FXML
    private TextField textHeight;
    @FXML
    private TextField textAge;

    @FXML
    private ComboBox<String> genderComboBox;

    @FXML
    private ComboBox<String> dailyActivityComboBox;

    @FXML
    private ComboBox<String> targetComboBox;

    @FXML
    private ComboBox<String> bodyTypeComboBox;

    @FXML
    private Label labelCountedCalories;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        genderComboBox.getItems().addAll("Male", "Female");
//        genderComboBox.setPromptText("Male");

        dailyActivityComboBox.getItems().addAll("1,0 – leżący lub siedzący tryb życia, brak aktywności fizycznej",
                "1,2 – praca siedząca, aktywność fizyczna na niskim poziomie",
                "1,4 – praca niefizyczna, trening 2 razy w tygodniu",
                "1,6 – lekka praca fizyczna, trening 3-4 razy w tygodniu",
                "1,8 – praca fizyczna, trening 5 razy w tygodniu",
                "2,0 – ciężka praca fizyczna, codzienny trening");
//        dailyActivityComboBox.setPromptText("1,0 – leżący lub siedzący tryb życia, brak aktywności fizycznej");

        targetComboBox.getItems().addAll("chcę chudnąć", "chcę utrzymać wagę", "chce budować masę mięśniową");
        // targetComboBox.setPromptText("chcę utrzymać wagę");

        bodyTypeComboBox.getItems().addAll("Ectomorph - chudzielec","Mesomorph - atleta","Endomorph - grubasek");


        buttonPro.setOnMouseClicked(event -> handleButtonProcess(event));

    }


    private void handleButtonProcess(MouseEvent e){


        double weight = Double.parseDouble(textWeight.getText());
        double height = Double.parseDouble(textHeight.getText());
        double age = Double.parseDouble(textAge.getText());


        if(genderComboBox.getValue().contentEquals("Female")){
            BMR = 655 + (9.6 * weight) + (1.85 * height) - (4.7 * age);
        }else if(genderComboBox.getValue().contentEquals("Male")) {
            BMR = 66.5 +(13.7 * weight) + (5 * height) - (6.8 * age) ;
        }

        System.out.println("1 etap " + BMR);


        if(dailyActivityComboBox.getValue().contentEquals("1,0 – leżący lub siedzący tryb życia, brak aktywności " +
                "fizycznej")){
            BMR = BMR *1;
        }else if (dailyActivityComboBox.getValue().contentEquals("1,2 – praca siedząca, aktywność fizyczna na niskim poziomie")){
            BMR = BMR *1.2;
        }else if (dailyActivityComboBox.getValue().contentEquals("1,4 – praca niefizyczna, trening 2 razy w tygodniu")){
            BMR = BMR *1.4;
        }else if (dailyActivityComboBox.getValue().contentEquals("1,6 – lekka praca fizyczna, trening 3-4 razy w tygodniu")){
            BMR = BMR *1.6;
        }else if (dailyActivityComboBox.getValue().contentEquals("1,8 – praca fizyczna, trening 5 razy w tygodniu")){
            BMR = BMR *1.8;
        }else if (dailyActivityComboBox.getValue().contentEquals("2,0 – ciężka praca fizyczna, codzienny trening")){
            BMR = BMR *2.0;
        }

        System.out.println("2 etap " + BMR);





        // warunki dla Ektomorfika

        if (bodyTypeComboBox.getValue().contentEquals("Ectomorph - chudzielec") && targetComboBox.getValue().contentEquals("chcę chudnąć") ){
            BMR = BMR *0.9;
        }
        if (bodyTypeComboBox.getValue().contentEquals("Ectomorph - chudzielec") && targetComboBox.getValue().contentEquals("chcę utrzymać wagę") ){
            // zostaje jak jest
        }
        if (bodyTypeComboBox.getValue().contentEquals("Ectomorph - chudzielec") && targetComboBox.getValue().contentEquals("chce budować masę mięśniową") ){
            BMR = BMR *1.2;
        }



        // warunki dla Mezomorfika

        if (bodyTypeComboBox.getValue().contentEquals("Mesomorph - atleta") && targetComboBox.getValue().contentEquals("chcę chudnąć") ){
            BMR = BMR *0.85;
        }
        if (bodyTypeComboBox.getValue().contentEquals("Mesomorph - atleta") && targetComboBox.getValue().contentEquals("chcę utrzymać wagę") ){
            // zostaje jak jest
        }
        if (bodyTypeComboBox.getValue().contentEquals("Mesomorph - atleta") && targetComboBox.getValue().contentEquals("chce budować masę mięśniową") ){
            BMR = BMR *1.15;
        }


        // warunki dla Endomorfika

        if (bodyTypeComboBox.getValue().contentEquals("Endomorph - grubasek") && targetComboBox.getValue().contentEquals("chcę chudnąć") ){
            BMR = BMR *0.8;
        }
        if (bodyTypeComboBox.getValue().contentEquals("Endomorph - grubasek") && targetComboBox.getValue().contentEquals("chcę utrzymać wagę") ){
            // zostaje jak jest
        }
        if (bodyTypeComboBox.getValue().contentEquals("Endomorph - grubasek") && targetComboBox.getValue().contentEquals("chce budować masę mięśniową") ){
            BMR = BMR *1.1;
        }

        System.out.println("3 etap " + BMR);





        int iBMR = (int) BMR;

        labelCountedCalories.setText(iBMR + " kcal");





//        double myDb = 12.3;
//        int myInt = (int) myDb;



//        else if(bodyTypeComboBox.getValue().contentEquals("Mesomorph - atleta")){
//            NEAT = 450;
//        }else if(bodyTypeComboBox.getValue().contentEquals("Endomorph - grubasek")){
//            NEAT = 300;
//        }else {
//            System.out.println("Wybierz typ budowy ciała");
//        }



    }





}
