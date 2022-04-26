package es.albertomarquez.proyectocsv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class App extends Application {
    
int maximo = Integer.MIN_VALUE;
Pane root;
String anno = "";
String paisActual = "";
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
       
       calculoDato();
    }
    
 
        public void calculoDato(){
        
        Label enun = new Label("DERECHOS HUMANDOS");
        enun.setStyle("-fx-font: 40 Impact;");
        enun.setLayoutX(150);
        enun.setLayoutY(20);
        proot.getChildren().add(enun);
       
        String nombreFichero = "derechosHumanos.csv";
        ArrayList<String> lista = new ArrayList();
        ArrayList<String> lista2 = new ArrayList();
        
        
        BufferedReader br = null;
        try { 
            br = new BufferedReader(new FileReader(nombreFichero));
            String texto = br.readLine();
            while(texto != null) {
                String[] valores = texto.split(",");
                
                //METODO PARA ORDENAR LOS AÑOS DE MAYOR A MENOS
                Comparator<String> comparador = Collections.reverseOrder();
                Collections.sort(lista2, comparador);
                //----------------------------------------------//
                Dato dato = new Dato();
                dato.setPais(valores[0]);
                dato.setAnno(valores[2]);
                dato.setderechosHumanos(valores[3]);
                
                System.out.println("Pais: "+dato.getPais());
                System.out.println("Año: "+dato.getAnno());
                System.out.println("Porcentaje de derechos: "+dato.getderechosHumanos());
                System.out.println("#####################################"); 
                
                if(!paisActual.equals(dato.getPais()) && !dato.getPais().equals("Entity")){
                    
                    lista.add(paisActual);
                    paisActual = dato.getPais();
                   
                }
                
                if(!anno.equals(dato.getAnno()) && !dato.getAnno().equals("Year")){
     
                    lista2.add(anno);
                    anno = dato.getAnno();
                }
                
                
                texto = br.readLine();       
            }    
        }   
        catch (FileNotFoundException ex) { 
            System.out.println("Error: Fichero no encontrado");
            ex.printStackTrace();   
        } 
        catch(Exception ex) {        
            System.out.println("Error de lectura del fichero");        
            ex.printStackTrace();    
        }
        finally {        
            try {            
                if(br != null) {                
                    br.close();            
                }        
            }        
            catch (Exception ex) {            
                System.out.println("Error al cerrar el fichero");            
                ex.printStackTrace();       
            }   
        }
        // Crear un ComboBox con el contenido de la lista
        ComboBox<String> comboBox = new ComboBox(FXCollections.observableList(lista));
        comboBox.setLayoutY(100);
        comboBox.setLayoutX(40);
        comboBox.setPromptText("Seleciona un pais");
        proot.getChildren().add(comboBox);
        
        ComboBox<String> comboBox2 = new ComboBox(FXCollections.observableList(lista2));
        comboBox2.setLayoutY(100);
        comboBox2.setLayoutX(290);
        comboBox2.setPromptText("Seleciona un año");
        proot.getChildren().add(comboBox2);
    
        // Añadir un label en el que se mostrará el elemento seleccionado
        Label seleccionado = new Label();
        seleccionado.setLayoutX(55);
        seleccionado.setLayoutY(130);
        proot.getChildren().add(seleccionado);
    
        // Cuando el usuario seleccione algo del ComboBox, se mostrará en el Label        
        comboBox.setOnAction((t) -> {
            seleccionado.setText(comboBox.getValue());
        });
        
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