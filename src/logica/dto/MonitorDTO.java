package logica.dto;

public class MonitorDTO {
	private double abscissa;
	private double ordenada;
	private String id;
	private boolean sensorCarbono;
	private boolean sensorMetano;
	private boolean cameraVideo;
	private int tpMonitor;
	
	public MonitorDTO(String id,double abscissa,double ordenada,
			      boolean sensorCarbono,boolean sensorMetano,boolean cameraVideo,int tpMonitor){
		
		super();
		this.id = id;
		this.abscissa = abscissa;
		this.ordenada = ordenada;
		this.sensorCarbono = sensorCarbono;
		this.sensorMetano = sensorMetano;
		this.cameraVideo = cameraVideo;
		this.tpMonitor = tpMonitor;
	}

	public double getAbscissa() {
		return abscissa;
	}

	public void setAbscissa(double abscissa) {
		this.abscissa = abscissa;
	}

	public double getOrdenada() {
		return ordenada;
	}

	public void setOrdenada(double ordenada) {
		this.ordenada = ordenada;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isSensorCarbono() {
		return sensorCarbono;
	}

	public void setSensorCarbono(boolean sensorCarbono) {
		this.sensorCarbono = sensorCarbono;
	}

	public boolean isSensorMetano() {
		return sensorMetano;
	}

	public void setSensorMetano(boolean sensorMetano) {
		this.sensorMetano = sensorMetano;
	}

	public boolean isCameraVideo() {
		return cameraVideo;
	}

	public void setCameraVideo(boolean cameraVideo) {
		this.cameraVideo = cameraVideo;
	}

	public int getTpMonitor() {
		return tpMonitor;
	}

	public void setTpMonitor(int tpMonitor) {
		this.tpMonitor = tpMonitor;
	}
	
	
	public String getTipoMonitor(){
		if(this.tpMonitor == 0)
			return "Manhattan";
		else if(this.tpMonitor == 1)
			return "Euclidiana";
		else
			return "ERROR";
	}
}
