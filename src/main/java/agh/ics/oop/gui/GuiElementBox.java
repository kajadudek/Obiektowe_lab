package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    private Image image;
    private ImageView imageView;
    private int imageSize;
    private Label positionLabel;
    private VBox vbox;

    public GuiElementBox(IMapElement element) {
        try {
            imageSize = 20;
            image = new Image(new FileInputStream(element.toImage()));
            imageView = new ImageView(image);
            imageView.setFitHeight(imageSize);
            imageView.setFitWidth(imageSize);
        }catch (FileNotFoundException exception) {
            System.out.println("image not found");
        }

        positionLabel = new Label(element.toLabel());
        vbox = new VBox(imageView, positionLabel);
        vbox.setAlignment(Pos.CENTER);
    }

    public VBox getVbox(){
        return this.vbox;
    }

}
