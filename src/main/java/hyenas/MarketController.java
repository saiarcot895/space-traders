
package hyenas;

import hyenas.Models.Good;
import hyenas.Models.Planet;
import hyenas.Models.Player;
import hyenas.Models.Ware;
import hyenas.UI.MarketInfoPane;
import hyenas.UI.MarketTableColumn;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MarketController implements Initializable {
    private int[] wares;
    private int[] tempWare;
    private TableView table = new TableView();

    @FXML
    private BorderPane borderPane;
    
    @FXML
    private VBox infoPane;
    
    @FXML
    private Label titleLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Player player = Player.getInstance();
        Planet planet = player.getTradingPlanet();
        
        Font titleFont = Font.loadFont(HyenasLoader.class.getResource("/hyenas/fonts/BlenderPro-Book.otf").toExternalForm(), 40);
        titleLabel.setFont(titleFont);
        titleLabel.setStyle("-fx-text-fill: rgba(0,231,255, .9); -fx-effect: dropshadow( gaussian, rgba(0,0,0,1), 0,0,2,2);");
        
        table.setPrefHeight(680.0);
        table.setPrefWidth(1160.0);
        table.setEditable(false);
        TableColumn wareCol = new MarketTableColumn("Ware");
        TableColumn availableCol = new MarketTableColumn("Available");
        TableColumn priceCol = new MarketTableColumn("Price");
        TableColumn contitionsCol = new MarketTableColumn("Conditions");
        TableColumn cargoCol = new MarketTableColumn("Cargo");
        
        wareCol.setCellValueFactory(
            new PropertyValueFactory<Ware, String>("name")
        );
        
        ObservableList<Ware> data = FXCollections.observableArrayList(
            new Ware(Good.Firearms), new Ware(Good.Food)
        );
        
        table.setItems(data);
        table.getColumns().addAll(wareCol, availableCol, priceCol, contitionsCol, cargoCol);

        borderPane.setCenter(table);
        
        
        VBox rightBox = new VBox();
        MarketInfoPane infoPane = new MarketInfoPane();
        Pane emptyPane = new Pane();
        emptyPane.setPrefWidth(300.0);
        emptyPane.setPrefHeight(300.0);
        rightBox.getChildren().addAll(infoPane, emptyPane);
        borderPane.setRight(rightBox);
        
        
        borderPane.setPadding(new Insets(40));
        borderPane.setMargin(table, new Insets(50));
        borderPane.setMargin(rightBox, new Insets(50, 0, 0, 0));

        /*
        wares = planet.getItems();
        tempWare = new int[10];
        fuelCost = 140-planet.getTechLevel()*10;
        fuelCount = player.getShip().getFuel();
        tPlanet.setText(planet.getPlanetName());
        tTechLevel.setText(planet.techLevelString());
        Random rand = new Random();
        waterPrice = 30 + 3*(planet.getTechLevel()-0) + planet.getWareEvents()[0]*10 + (rand.nextInt(3)-1)*rand.nextInt(5);
        fursPrice = 250 + 10*(planet.getTechLevel()-0) + planet.getWareEvents()[1]*10 + (rand.nextInt(3)-1)*rand.nextInt(11);
        foodPrice = 100 + 5*(planet.getTechLevel()-1) + planet.getWareEvents()[2]*10 + (rand.nextInt(3)-1)*rand.nextInt(6);
        orePrice = 350 + 20*(planet.getTechLevel()-2) + planet.getWareEvents()[3]*10 + (rand.nextInt(3)-1)*rand.nextInt(11);
        gamesPrice = 250 + -10*(planet.getTechLevel()-3) + planet.getWareEvents()[4]*10 + (rand.nextInt(3)-1)*rand.nextInt(6);
        firearmsPrice = 1250 + -75*(planet.getTechLevel()-3) + planet.getWareEvents()[5]*10 + (rand.nextInt(3)-1)*rand.nextInt(101);
        medicinePrice = 650 + -20*(planet.getTechLevel()-4) + planet.getWareEvents()[6]*10 + (rand.nextInt(3)-1)*rand.nextInt(11);
        machinesPrice = 900 + -30*(planet.getTechLevel()-4) + planet.getWareEvents()[7]*10 + (rand.nextInt(3)-1)*rand.nextInt(6);
        narcoticsPrice = 3500 + -125*(planet.getTechLevel()-5) + planet.getWareEvents()[8]*10 + (rand.nextInt(3)-1)*rand.nextInt(151);
        robotsPrice = 5000 + -150*(planet.getTechLevel()-6) + planet.getWareEvents()[9]*10 + (rand.nextInt(3)-1)*rand.nextInt(101);

        //tPlanet.setText(planet.getName());
        tFree.setText("" + freeCargo);

        pWater.setText("" + waterPrice);
        pFurs.setText("" + fursPrice);
        pFood.setText("" + foodPrice);
        pOre.setText("" + orePrice);
        pGames.setText("" + gamesPrice);
        pFirearms.setText("" + firearmsPrice);
        pMedicine.setText("" + medicinePrice);
        pMachines.setText("" + machinesPrice);
        pNarcotics.setText("" + narcoticsPrice);
        pRobots.setText("" + robotsPrice);
        fuelPrice.setText("" + fuelCost);
        fuelLeft.setText(String.format("%.0f", fuelCount));
        currentCredits.setText("" + creditCount);

        if(planet.getWareEvent(0) == 1)    {
            eWater.setText("Drought");
        }
        if(planet.getWareEvent(1) == 1) {
            eFurs.setText("Cold");
        }
        if(planet.getWareEvent(2) == 1) {
            eFood.setText("Crop Failure");
        }
        if(planet.getWareEvent(3) == 1) {
            eOre.setText("War");
            eFirearms.setText("War");
        }
        if(planet.getWareEvent(4) == 1) {
            eGames.setText("Boredom");
            eNarcotics.setText("Boredom");
        }
        if(planet.getWareEvent(6) == 1) {
            eMedicine.setText("Plague");
        }
        if(planet.getWareEvent(7) == 1) {
            eMachines.setText("Lack of Workers");
            eRobots.setText("Lack of Workers");
        }
        
        for (Ware good : player.getShip().getCargo()) {
            switch (good.getName()) {
                case "Water":
                    aWater.setText(""
                            + (Integer.parseInt(aWater.getText()) + 1));
                    break;
                case "Furs":
                    aFurs.setText(""
                            + (Integer.parseInt(aFurs.getText()) + 1));
                    break;
                case "Food":
                    aFood.setText(""
                            + (Integer.parseInt(aFood.getText()) + 1));
                    break;
                case "Ore":
                    aOre.setText(""
                            + (Integer.parseInt(aOre.getText()) + 1));
                    break;
                case "Games":
                    aGames.setText(""
                            + (Integer.parseInt(aGames.getText()) + 1));
                    break;
                case "Firearms":
                    aFirearms.setText(""
                            + (Integer.parseInt(aFirearms.getText()) + 1));
                    break;
                case "Medicine":
                    aMedicine.setText(""
                            + (Integer.parseInt(aMedicine.getText()) + 1));
                    break;
                case "Machines":
                    aMachines.setText(""
                            + (Integer.parseInt(aMachines.getText()) + 1));
                    break;
                case "Narcotics":
                    aNarcotics.setText(""
                            + (Integer.parseInt(aNarcotics.getText()) + 1));
                    break;
                case "Robots":
                    aRobots.setText(""
                            + (Integer.parseInt(aRobots.getText()) + 1));
                    break;
            }
        }

        aWater.setText("" + wares[0]);
        aFurs.setText("" + wares[1]);
        aFood.setText("" + wares[2]);
        aOre.setText("" + wares[3]);
        aGames.setText("" + wares[4]);
        aFirearms.setText("" + wares[5]);
        aMedicine.setText("" + wares[6]);
        aMachines.setText("" + wares[7]);
        aNarcotics.setText("" + wares[8]);
        aRobots.setText("" + wares[9]);*/
    }

    public void buyItem(ActionEvent e) {
        /*
        if (freeCargo <= 0) {
            return;
        }

        if (e.getSource() == bWater) {
            if (creditCount >= waterPrice && wares[0] > 0) {
                freeCargo--;
                tFree.setText("" + freeCargo);
                creditCount -= waterPrice;
                wares[0]--;
            }
        } else if (e.getSource() == bFurs) {
            if (creditCount >= fursPrice && wares[1] > 0) {
                freeCargo--;
                tFree.setText("" + freeCargo);
                creditCount -= fursPrice;
                wares[1]--;
            }
        } else if (e.getSource() == bFood) {
            if (creditCount >= foodPrice && wares[2] > 0) {
                freeCargo--;
                tFree.setText("" + freeCargo);
                creditCount -= foodPrice;
                wares[2]--;
            }
        } else if (e.getSource() == bOre) {
            if (creditCount >= orePrice && wares[3] > 0) {
                freeCargo--;
                tFree.setText("" + freeCargo);
                creditCount -= orePrice;
                wares[3]--;
            }
        } else if (e.getSource() == bGames) {
            if (creditCount >= gamesPrice && wares[4] > 0) {
                freeCargo--;
                tFree.setText("" + freeCargo);
                creditCount -= gamesPrice;
                wares[4]--;
            }
        } else if (e.getSource() == bFirearms) {
            if (creditCount >= firearmsPrice && wares[5] > 0) {
                freeCargo--;
                tFree.setText("" + freeCargo);
                creditCount -= firearmsPrice;
                wares[5]--;
            }
        } else if (e.getSource() == bMachines) {
            if (creditCount >= machinesPrice && wares[6] > 0) {
                freeCargo--;
                creditCount -= machinesPrice;
                wares[6]--;
            }
        } else if (e.getSource() == bMedicine) {
            if (creditCount >= medicinePrice && wares[7] > 0) {
                freeCargo--;
                tFree.setText("" + freeCargo);
                creditCount -= medicinePrice;
                wares[7]--;
            }
        } else if (e.getSource() == bNarcotics) {
            if (creditCount >= narcoticsPrice && wares[8] > 0) {
                freeCargo--;
                tFree.setText("" + freeCargo);
                creditCount -= narcoticsPrice;
                wares[8]--;
            }
        } else if (e.getSource() == bRobots) {
            if (creditCount >= robotsPrice && wares[9] > 0) {
                freeCargo--;
                tFree.setText("" + freeCargo);
                creditCount -= robotsPrice;
                wares[9]--;
            }
        }
        currentCredits.setText("" + creditCount);*/
    }

    public void sellItem(ActionEvent e) {
        /*
        if (e.getSource() == sWater) {
            if (player.getShip().getCargo().contains(Good.Water)) {
                freeCargo++;
                tFree.setText("" + freeCargo);
                wares[0]++;
                creditCount += waterPrice;
            }
        } else if (e.getSource() == sFurs) {
            if (player.getShip().getCargo().contains(Good.Furs)) {
                freeCargo++;
                tFree.setText("" + freeCargo);
                wares[1]++;
                creditCount += fursPrice;
            }
        } else if (e.getSource() == sFood) {
            if (player.getShip().getCargo().contains(Good.Food)) {
                freeCargo++;
                tFree.setText("" + freeCargo);
                wares[2]++;
                creditCount += waterPrice;
            }
        } else if (e.getSource() == sOre) {
            if (player.getShip().getCargo().contains(Good.Ore)) {
                freeCargo++;
                tFree.setText("" + freeCargo);
                wares[3]++;
                creditCount += orePrice;
            }
        } else if (e.getSource() == sGames) {
            if (player.getShip().getCargo().contains(Good.Games)) {
                freeCargo++;
                tFree.setText("" + freeCargo);
                wares[4]++;
                creditCount += gamesPrice;
            }
        } else if (e.getSource() == sFirearms) {
            if (player.getShip().getCargo().contains(Good.Firearms)) {
                freeCargo++;
                tFree.setText("" + freeCargo);
                wares[5]++;
                creditCount += firearmsPrice;
            }
        } else if (e.getSource() == sMachines) {
            if (player.getShip().getCargo().contains(Good.Machines)) {
                freeCargo++;
                tFree.setText("" + freeCargo);
                wares[6]++;
                creditCount += machinesPrice;
            }
        } else if (e.getSource() == sMedicine) {
            if (player.getShip().getCargo().contains(Good.Medicine)) {
                freeCargo++;
                tFree.setText("" + freeCargo);
                wares[7]++;
                creditCount += medicinePrice;
            }
        } else if (e.getSource() == sNarcotics) {
            if (player.getShip().getCargo().contains(Good.Narcotics)) {
                freeCargo++;
                tFree.setText("" + freeCargo);
                wares[8]++;
                creditCount += narcoticsPrice;
            }
        } else if (e.getSource() == sRobots) {
            if (player.getShip().getCargo().contains(Good.Robots)) {
                freeCargo++;
                tFree.setText("" + freeCargo);
                wares[9]++;
                creditCount += robotsPrice;
            }
        }
        currentCredits.setText("" + creditCount);*/
    }
    
    public void resetTrade(ActionEvent e) {
        //TODO
        System.out.println("TODO");
    }
    

    public void confirmTrade(ActionEvent e) {
        /*
        planet.changeWares(wares);
        player.setCredits(creditCount);
        player.getShip().setFuel(fuelCount);
        // Add the Cargo
        for (int i = 0; i < -wares[0]; i++) {
            player.getShip().addCargo(new Ware(Good.Water));
        }
        for (int i = 0; i < -wares[1]; i++) {
            player.getShip().addCargo(new Ware(Good.Furs));
        }
        for (int i = 0; i < -wares[2]; i++) {
            player.getShip().addCargo(new Ware(Good.Food));
        }
        for (int i = 0; i < -wares[3]; i++) {
            player.getShip().addCargo(new Ware(Good.Ore));
        }
        for (int i = 0; i < -wares[4]; i++) {
            player.getShip().addCargo(new Ware(Good.Games));
        }
        for (int i = 0; i < -wares[5]; i++) {
            player.getShip().addCargo(new Ware(Good.Firearms));
        }
        for (int i = 0; i < -wares[6]; i++) {
            player.getShip().addCargo(new Ware(Good.Machines));
        }
        for (int i = 0; i < -wares[7]; i++) {
            player.getShip().addCargo(new Ware(Good.Medicine));
        }
        for (int i = 0; i < -wares[8]; i++) {
            player.getShip().addCargo(new Ware(Good.Narcotics));
        }
        for (int i = 0; i < -wares[9]; i++) {
            player.getShip().addCargo(new Ware(Good.Robots));
        }

        // Remove the Cargo
        for (int i = 0; i < -wares[0]; i++) {
            player.getShip().removeCargo(new Ware(Good.Water));
        }
        for (int i = 0; i < -wares[1]; i++) {
            player.getShip().removeCargo(new Ware(Good.Furs));
        }
        for (int i = 0; i < -wares[2]; i++) {
            player.getShip().removeCargo(new Ware(Good.Food));
        }
        for (int i = 0; i < -wares[3]; i++) {
            player.getShip().removeCargo(new Ware(Good.Ore));
        }
        for (int i = 0; i < -wares[4]; i++) {
            player.getShip().removeCargo(new Ware(Good.Games));
        }
        for (int i = 0; i < -wares[5]; i++) {
            player.getShip().removeCargo(new Ware(Good.Firearms));
        }
        for (int i = 0; i < -wares[6]; i++) {
            player.getShip().removeCargo(new Ware(Good.Machines));
        }
        for (int i = 0; i < -wares[7]; i++) {
            player.getShip().removeCargo(new Ware(Good.Medicine));
        }
        for (int i = 0; i < -wares[8]; i++) {
            player.getShip().removeCargo(new Ware(Good.Narcotics));
        }
        for (int i = 0; i < -wares[9]; i++) {
            player.getShip().removeCargo(new Ware(Good.Robots));
        }*/
    }

    public void addFuel(ActionEvent e) {
        // if (fuelCount == player.getShip().getMaxFuel() || creditCount < fuelCost) {

        //     //TODO display message saying that they have hit limit on fuel
        // }
        // fuelCount++;
        // creditCount -= fuelCost;
        // fuelLeft.setText(String.format("%.0f", fuelCount));
        // currentCredits.setText("" + creditCount);
    }

    public void subtractFuel(ActionEvent e) {
        // if (fuelCount == 0) {
        //     return;
        //     //TODO display message saying that they have hit limit on fuel
        // }
        // fuelCount--;
        // creditCount += fuelCost;
        // fuelLeft.setText(String.format("%.0f", fuelCount));
        // currentCredits.setText("" + creditCount);
    }

    public void goBack(ActionEvent e) {
        HyenasLoader.getInstance().goToSystemScreen();
    }

}
