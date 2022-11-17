package com.example.kurs;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.io.IOException;
import java.sql.SQLException;

public class ProductCard{



    private final String nameProduct;
    private final int id;
    private final String priceProduct;
    private final String typeProduct;

    private final String idCard;

    public ProductCard(String nameProduct,String priceProduct,String typeProduct,int id,String  idCard){
        this.nameProduct = nameProduct;
        this.typeProduct = typeProduct;
        this.priceProduct = priceProduct;
        this.id = id;
        this.idCard = idCard;
    }


    public GridPane createCardProduct(double x, double y){
        GridPane gridPane = new GridPane();
        gridPane.setId(String.valueOf(idCard));
        Label name = new Label(nameProduct);
        Label price = new Label(priceProduct);
        Label type = new Label(typeProduct);
        gridPane.add(name,0,0);
        gridPane.add(price,0,1);
        gridPane.add(type,0,2);

        int countcolumn = 1;
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth((1.0f/((float) countcolumn))*100.0f);
        for (int i = 1; i <= countcolumn; i++) {
            gridPane.getColumnConstraints().add(column);
        }
        int countRows = 3;
        RowConstraints line = new RowConstraints();
        line.setPercentHeight((1.0f/((float) countRows))*100.0f);
        for (int i = 1; i <= countRows; i++) {
            gridPane.getRowConstraints().add(line);
        }
        gridPane.setGridLinesVisible(true);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        Light.Distant light = new Light.Distant();
        light.setAzimuth(-135.0);

        Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(5.0);

        gridPane.setEffect(lighting);
        gridPane.setLayoutX(x);
        gridPane.setLayoutY(y);

        gridPane.setOnMouseClicked(mouseEvent -> {
            try {
                toProductTable();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return gridPane;
    }


    public void toProductTable() throws IOException, SQLException {
        ProductController.id = id;
        Parent root = FXMLLoader.load(ProductCard.class.getResource("productTable-view.fxml"));
        HelloApplication.stage.setScene(new Scene(root));
        HelloApplication.stage.show();
    }

    public int getId() {
        return id;
    }

    public String getIdCard() {
        return idCard;
    }
}