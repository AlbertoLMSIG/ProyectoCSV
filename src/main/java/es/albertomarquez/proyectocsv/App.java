package es.albertomarquez.proyectocsv;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class App extends Application {
    
ImageView imgfondo1;
ImageView imgfondo2;
Timeline fondoScroll;
int imagenFondo1X = 0;
int imagenFondo2X = 640;
Pane proot = new Pane();


    @Override
    public void start(Stage stage) {
       var scene = new Scene(proot, 640, 480);
       stage.setScene(scene);
       stage.show();
       
       scrool();
       
       Operaciones opera = new Operaciones();
       
       opera.calculoDato(proot);
    }
        
    public void scrool(){           
            imgfondo1 = new ImageView();
            imgfondo2 = new ImageView();
            proot.getChildren().add(imgfondo1);
            proot.getChildren().add(imgfondo2);
            imgfondo1.setLayoutX(imagenFondo1X);
            imgfondo2.setLayoutX(imagenFondo2X); 
            Image img = new Image(getClass().getResourceAsStream("/image/fondo.png"));
            imgfondo1 = new ImageView(img);
            imgfondo2 = new ImageView(img);
            proot.getChildren().add(imgfondo2);
            proot.getChildren().add(imgfondo1);
            fondoScroll = new Timeline(                             
                new KeyFrame(Duration.seconds(0.007), (ActionEvent ae) -> {                                         
                    imagenFondo1X = imagenFondo1X -1;
                    imgfondo1.setLayoutX(imagenFondo1X);
                    imagenFondo2X = imagenFondo2X -1;
                    imgfondo2.setLayoutX(imagenFondo2X);
                    if (imagenFondo1X == -700) {  
                        imagenFondo1X = 643;
                      } else if (imagenFondo2X == -700) {
                          imagenFondo2X = 643;
        }
        })
        );
        fondoScroll.setCycleCount(Timeline.INDEFINITE);
        fondoScroll.play();
        }
        
        public static void main(String[] args) {
            launch();
        }

}