package es.albertomarquez.proyectocsv;

public class Dato {
    
    private String pais;
    private int año;
    private float derechosHumanos;  


    public Dato(){}
    
    public Dato(String marca){  
        this.pais = pais.toUpperCase();
    }
    
    
    public String getPais(){
        return pais;
    }
    
    public void setPais(String pais){
        this.pais = pais.toUpperCase();
    }
    
    public int getAño(){
        return año;
    }
    
    public void setAño(int año){
        this.año = año;
    }
    
    public float getderechosHumanos(){
        return derechosHumanos;
    }
    
    public void setderechosHumanos(float derechosHumanos){
        this.derechosHumanos = derechosHumanos;
    }
    
     @Override
    public String toString(){
        String r = "";
        r += "Pais: " + pais + "\n";
        return r;
    }
    public String añoToString(){
       String r = "Años: " + año;
       return r;
    }
    
     public String porcentajeToString(){
       String a = "Porcentaje de los derechos humanos: " + derechosHumanos;
       return a;
    }
}
