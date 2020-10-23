package logica;

public class Ponto {
	
	private double abscica;
    private double ordenada;

    public Ponto(double abscica,double ordenada){
        this.abscica = abscica;
        this.ordenada = ordenada;
    }

    public void setAbscica(double abscica){
        this.abscica = abscica;
    }
    public double getAbscica(){
        return this.abscica;
    }
    public void setOrdenada(double ordenada){
        this.ordenada = ordenada;
    }
    public double getOrdenada(){
    	return this.ordenada;
    }	
}
