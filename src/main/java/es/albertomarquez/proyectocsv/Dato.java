package es.albertomarquez.proyectocsv;

public class Dato {
    
    private String pais;
    private String year;
    private String derechosHumanos;  

    public Dato(String pais, String año, String derechosHumanos) {
        this.pais = pais;
        this.year = año;
        this.derechosHumanos = derechosHumanos;
    }
    

    public Dato(){}
    
    public Dato(String marca){  
        this.pais = pais;
    }
    
    
    public String getPais(){
        return pais;
    }
    
    public void setPais(String pais){
        this.pais = pais;
    }
    
    public String getAnno(){
        return year;
    }
    
    public void setAnno(String year){
        this.year = year;
    }
    
    public String getderechosHumanos(){
        return derechosHumanos;
    }
    
    public void setderechosHumanos(String derechosHumanos){
        this.derechosHumanos = derechosHumanos;
    }
    
     @Override
    public String toString(){
        String r = "";
        r += "Pais: " + pais + "\n";
        return r;
    }
    public String AnnoToString(){
       String r = "Años: " + year;
       return r;
    }
    
     public String porcentajeToString(){
       String a = "Porcentaje de los derechos humanos: " + derechosHumanos;
       return a;
    }
}
