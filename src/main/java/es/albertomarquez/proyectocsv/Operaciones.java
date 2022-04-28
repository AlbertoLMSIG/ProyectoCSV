package es.albertomarquez.proyectocsv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Operaciones {
    int maximo = Integer.MIN_VALUE;
    String anno = "";
    String pA = "";
    boolean yearB = true;
    int sumaParo;
    int cont = 0;
    float media;
    
    String seleccionPais = "";
    String seleccionAnno = "";
    
    public void calculoDato(Pane proot){
    
        Label enun = new Label("DERECHOS HUMANDOS");
        enun.setStyle("-fx-font: 40 Impact;");
        enun.setLayoutX(150);
        enun.setLayoutY(20);
        proot.getChildren().add(enun);
        
        ArrayList<String> lista = new ArrayList();
        ArrayList<String> lista2 = new ArrayList();

        String nombreFichero = "derechosHumanos.csv";
       
        // Crear un ComboBox con el contenido de la lista 
        //PAIS
        ComboBox<String> comboBox = new ComboBox(FXCollections.observableList(lista));
        comboBox.setLayoutY(100);
        comboBox.setLayoutX(40);
        comboBox.setPromptText("Seleciona un pais");
        proot.getChildren().add(comboBox);
        
        Label seleccionado = new Label();
        seleccionado.setLayoutX(55);
        seleccionado.setLayoutY(130);
        proot.getChildren().add(seleccionado);
        
        // Cuando el usuario seleccione algo del ComboBox, se mostrará en el Label el pais selecionado       
        comboBox.setOnAction((t) -> {
            seleccionado.setText("Pais: "+comboBox.getValue());
            seleccionPais = comboBox.getValue();
        });
        
        //AÑO
        ComboBox<String> comboBox2 = new ComboBox(FXCollections.observableList(lista2));
        comboBox2.setLayoutY(100);
        comboBox2.setLayoutX(290);
        comboBox2.setPromptText("Seleciona un año");
        proot.getChildren().add(comboBox2);

       // Añadir un label en el que se mostrará el elemento seleccionado
        Label seleccionado2 = new Label();
        seleccionado2.setLayoutX(300);
        seleccionado2.setLayoutY(130);
        proot.getChildren().add(seleccionado2);
        
        // Cuando el usuario seleccione algo del ComboBox, se mostrará en el Label del año
        comboBox2.setOnAction((t) -> {
            seleccionado2.setText("Año: "+comboBox2.getValue());
            seleccionAnno = comboBox2.getValue();
        });
        
        
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

                if(!pA.equals(dato.getPais()) && !dato.getPais().equals("Entity")){

                    lista.add(pA);
                    pA = dato.getPais();

                }

                for(int i=0;i<lista2.size();i++){
                    if(dato.getAnno().equals(lista2.get(i))){
                        yearB = false;
                    }
                }

                if(yearB == true && !dato.getAnno().equals("Year")){

                    lista2.add(dato.getAnno());
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
        
        //BOTON
        Button buscar = new Button();
        buscar.setLayoutX(470);
        buscar.setLayoutY(100);
        buscar.setText("Buscar");
        proot.getChildren().add(buscar);
        
         //PORCENTAJE DERECHOS HUMANOS
        Label porcDerechosH = new Label();
        porcDerechosH.setLayoutX(130);
        porcDerechosH.setLayoutY(200);
        proot.getChildren().add(porcDerechosH);
        
        buscar.setOnAction(t -> {
           String nombreFichero2 = "derechosHumanos.csv";

            BufferedReader br2 = null;
            try {           
                br2 = new BufferedReader(new FileReader(nombreFichero2));
                String texto2 = br2.readLine();
                while(texto2 != null) {                
                    String[] valores = texto2.split(",");

                    //METODO PARA ORDENAR LOS AÑOS DE MAYOR A MENOS
                    Comparator<String> comparador = Collections.reverseOrder();
                    Collections.sort(lista2, comparador);
                    //----------------------------------------------//
                    Dato dato = new Dato();
                    dato.setPais(valores[0]);
                    dato.setAnno(valores[2]);
                    dato.setderechosHumanos(valores[3]);
                    texto2 = br2.readLine();
                    
                    if(seleccionPais.equals(dato.getPais()) && seleccionAnno.equals(dato.getAnno())){
                        porcDerechosH.setText("El porcentaje de los derechos humanos obtenido es de "+dato.getderechosHumanos());
                        //System.out.println("el porcentaje obtenido es "+dato.getderechosHumanos());
                    }
                    
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
                    if(br2 != null) {                
                        br2.close();            
                    }        
                }        
                catch (Exception ex) {            
                    System.out.println("Error al cerrar el fichero");            
                    ex.printStackTrace();       
                }   
            } 
        });
    }
}

