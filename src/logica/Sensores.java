package logica;

public enum Sensores {
	cameraVideo("cftv"),temperatura("temp"),
    sensorCarbono("Co2"), sensorMetano("ch4");

    private String sensores;
    
    Sensores(String sensores){
        this.sensores = sensores;
    }

    public String getSensores(){
        return sensores;
    }
}
