package es.albertomarquez.proyectocsv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
    
int maximo = Integer.MIN_VALUE;
Pane root;
String pais = "";
String paisActual = "";


    @Override
    public void start(Stage stage) {
       
        root = new Pane();
        var scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
        
        Label enun = new Label("DERECHOS DE LOS PAISES");
        enun.setStyle("-fx-font: 40 arial;");
        enun.setLayoutX(50);
        root.getChildren().add(enun);
        
        String nombreFichero = "derechosHumanos.csv";
        ArrayList<String> lista = new ArrayList();
        
        BufferedReader br = null;
        try { 
            br = new BufferedReader(new FileReader(nombreFichero));
            String texto = br.readLine();
            while(texto != null) {
                String[] valores = texto.split(",");
                pais = valores[0];
                String año = valores[2];
                String porcentaje = valores[3];
                System.out.println("Pais: "+pais);
                System.out.println("Año: "+año);
                System.out.println("Porcentaje de derechos: "+porcentaje);
                System.out.println("#####################################"); 
                if(!paisActual.equals(pais) && !pais.equals("Entity")){
                    lista.add(paisActual);
                    paisActual = pais;
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
    comboBox.setLayoutX(50);
    root.getChildren().add(comboBox);
    
    // Añadir un label en el que se mostrará el elemento seleccionado
    Label seleccionado = new Label();
    root.getChildren().add(seleccionado);
    
        // Cuando el usuario seleccione algo del ComboBox, se mostrará en el Label
    comboBox.setOnAction((t) -> {
        seleccionado.setText(comboBox.getValue());
    });


    
    }

        public static void main(String[] args) {
            launch();
        }

}