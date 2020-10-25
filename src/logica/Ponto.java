package logica;

public class Ponto {
	
	private double abscissa;
    private double ordenada;

    public Ponto(double abscissa,double ordenada){
        this.abscissa = abscissa;
        this.ordenada = ordenada;
    }

    public void setAbscica(double abscissa){
        this.abscissa = abscissa;
    }
    public double getAbscissa(){
        return this.abscissa;
    }
    public void setOrdenada(double ordenada){
        this.ordenada = ordenada;
    }
    public double getOrdenada(){
    	return this.ordenada;
    }	
   
	public boolean equals(Ponto outro){	
		return (outro.getAbscissa() == this.getAbscissa() || outro.getOrdenada() == this.getOrdenada())?true:false;
	}
}
