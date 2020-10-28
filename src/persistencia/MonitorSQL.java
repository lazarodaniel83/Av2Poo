package persistencia;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import logica.MonitorEuclidiana;
import logica.MonitorManhattan;
import logica.dto.MonitorDTO;
import logica.Sensores;
import logica.MonitorMovel;
import logica.MonitorConfig;
import exception.MonitorMovelException;

public class MonitorSQL implements MonitorDAO{
	private static final int MONITOR_MANHATTAN = 0;
	private static final int MONITOR_EUCLIDIANA = 1;
	
	public static final String URI = "jdbc:mysql://localhost";	
	public static final String USER = "daniel";
	public static final String PWD = "";
	public static final String DRIVE = "org.mysql.jdbc.JDBCDriver";
	
	private static String SAVE = "INSERT INTO MONITORMOVEL(ABSCISSA,ORDENADA, MONITORCARBONO, CAMERAVIDEO, TEMPERATURA, MONITORMETANO, TIPOUNIDADE ) \r\n" +
			 "VALUES(?, ?, ?, ?, ?, ?, ?)";
	private final String CREATE = "CREATE TABLE MONITORMOVEL " 
			  + "(id VARCHAR(255)," 
			  + " abscissa DOUBLE, " 
			  + " ordenada DOUBLE, "
			  + " configuracao VARCHAR(255), "
			  + " tipoDaUnidade INT, "
			  + " PRIMARY KEY (id))";
	private static String RECOVERY_BY_ID = "SELECT ID,ABSCISSA,ORDENADA, MONITORCARBONO, CAMERAVIDEO, TEMPERATURA, MONITORMETANO, TIPOUNIDADE \r\n" + 
			  " FROM MONITORMOVEL \r\n" +
			  " WHERE ID = ? \r\n";
	private static String RECOVERY_ALL = "SELECT ID, ABSCISSA,ORDENADA, MONITORCARBONO, CAMERAVIDEO, TEMPERATURA, MONITORMETANO,, TIPOUNIDADE \r\n" + 
            " FROM UNIDADEMOVEL \r\n";	

    private static String UPDATE = "UPDATE MONITORMOVEL SET ABSCISSA = ?, ORDENADA = ?, MONITORCARBONO = ?, CAMERAVIDEO = ?, TEMPERATURA = ?, MONITORMETANO = ?, TIPOUNIDADE = ? WHERE ID = ?";

    private static String DELETE = "DELETE FROM MONITORMOVEL WHERE ID = ?";
    
    public MonitorSQL() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        DriverManager.registerDriver((Driver) Class.forName(MonitorSQL.DRIVE).newInstance());
    }    
   
    private Connection getConnection() throws SQLException {
           Connection conn = DriverManager.getConnection(MonitorSQL.URI, MonitorSQL.USER, MonitorSQL.PWD);
           return conn;
    }
    public void salvar(MonitorMovel monitor) throws Exception {
		if(monitor instanceof MonitorManhattan) {
		  this.saveManhattan((MonitorManhattan)monitor);	
		}else if(monitor instanceof MonitorEuclidiana) {
		  this.saveEuclidiana((MonitorEuclidiana)monitor);	
		}
	}
    
    private void saveEuclidiana(MonitorEuclidiana Monitor) throws SQLException {
		PreparedStatement ps = this.getConnection().prepareStatement(MonitorSQL.SAVE);
		//ps.setInt(1, Monitor.getId());
		ps.setDouble(1, Monitor.getOrdenada());
		ps.setDouble(2, Monitor.getAbscissa());
		ps.setString(3, Monitor.getConfig().getSensores().toString());
		ps.setInt(4, MonitorSQL.MONITOR_EUCLIDIANA);
		ps.executeUpdate();
	}
    
    private void saveManhattan(MonitorManhattan Monitor) throws SQLException {
		PreparedStatement ps = this.getConnection().prepareStatement(MonitorSQL.SAVE);
		//ps.setInt(1, Monitor.getId());
		ps.setDouble(1, Monitor.getOrdenada());
		ps.setDouble(2, Monitor.getAbscissa());
		ps.setString(3, Monitor.getConfig().getSensores().toString());
		ps.setInt(4, MonitorSQL.MONITOR_MANHATTAN);
		ps.executeUpdate();
	}
    
    public MonitorConfig listaSensor(ResultSet rs) throws SQLException{
    	String conf = rs.getString("Configurar");
    	conf = conf.replace("[", "");
    	conf = conf.replace("]", "");
    	
    	ArrayList<String> list = new ArrayList<String>(Arrays.asList(conf.split(",")));
    	MonitorConfig config = new MonitorConfig();
    	
    	for(int i = 0;i<list.size();i++){
    		String sensor = list.get(i).trim();
    		config.adicionarMonitor(Sensores.valueOf(sensor));
    	}
    	return config;
    }
	
    @Override
    public MonitorMovel busca(String id) throws Exception{
		
		Statement stmt;
		MonitorMovel monitor = null;
		
		try {
			PreparedStatement ps = this.getConnection().prepareStatement(MonitorSQL.RECOVERY_BY_ID);
			ps.setString(1,id);
			
			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {
				int tipo = rSet.getInt("tipoDaUnidade");
				MonitorConfig config =  listaSensor(rSet);
				
				if (tipo == 0) {
					monitor = new MonitorEuclidiana(rSet.getString("id"), 
							rSet.getFloat("abscissa"), 
							rSet.getFloat("ordenada"), 
							config);
				
				} 
				else if (tipo == 1) {
					monitor = new MonitorManhattan(rSet.getString("id"), 
													rSet.getDouble("abscissa"), 
													rSet.getDouble("ordenada"), 
													config);
				}			
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return monitor;
	}
    
    public List<MonitorMovel>  bcsTdsMonitores() throws Exception{
    	
		Statement stm;
		ArrayList<MonitorMovel> monitor = new ArrayList<MonitorMovel>();
		MonitorMovel outroMonitor = null;
		
		try {
			stm = this.getConnection().createStatement();
			ResultSet rSet = stm.executeQuery(MonitorSQL.RECOVERY_ALL);
			
			while(rSet.next()){
				int tipo = rSet.getInt("tipoDaUnidade");
				MonitorConfig config = listaSensor(rSet);
				if(tipo == 0){
					outroMonitor = new MonitorEuclidiana(rSet.getString("id"),rSet.getDouble("abscissa"),rSet.getDouble("ordenada"), config);
				}
				else if(tipo == 1){
					outroMonitor = new MonitorManhattan(rSet.getString("id"),
							 rSet.getDouble("abscissa"),
							 rSet.getDouble("ordenada"),
							 config);
				}
				monitor.add(outroMonitor);
				
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
			
		}
		return monitor;
	}
    
    public List<MonitorDTO> buscarTdUi() throws Exception{
    	List<MonitorDTO> monitores = new ArrayList<>();
    	MonitorDTO monitor = null;
    	PreparedStatement ps = this.getConnection().prepareStatement(MonitorSQL.RECOVERY_ALL);
    	ResultSet rSet = ps.executeQuery();
    	
    	while(rSet.next()){
    		int tipo = rSet.getInt("TipoDoMonitor");
    		if(tipo == MonitorSQL.MONITOR_EUCLIDIANA)
    			monitor = new MonitorDTO(rSet.getString("ID"),
    									 rSet.getDouble("ABSCISSA"),rSet.getDouble("ORDENADA"),
    									 rSet.getBoolean("MONITORCARBONO"),rSet.getBoolean("MONITORMETANO"),
    									 rSet.getBoolean("CAMERAVIDEO"),rSet.getInt("TIPOMONITOR"));
    		else if(tipo == MonitorSQL.MONITOR_MANHATTAN)
    			monitor = new MonitorDTO(rSet.getString("ID"),
										 rSet.getDouble("ABSCISSA"),rSet.getDouble("ORDENADA"),
										 rSet.getBoolean("MONITORCARBONO"),rSet.getBoolean("MONITORMETANO"),
										 rSet.getBoolean("CAMERAVIDEO"),rSet.getInt("TIPOMONITOR"));
    		monitores.add(monitor);
    	}
    	return monitores;
    }
    
    
    
	public void atualizar(MonitorMovel monitor) throws Exception{
		
		PreparedStatement stm;
		
		try{
			stm = this.getConnection().prepareStatement(MonitorSQL.UPDATE);
			stm.setDouble(1,monitor.getAbscissa());
			stm.setDouble(2,monitor.getOrdenada());
			stm.setString(3,monitor.getConfig().getSensores().toString());
			stm.setString(4,monitor.getId());
			stm.execute();
			stm.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
		} 
			
	} 
	
	public void delete(String idMonitor){
	  
		PreparedStatement stm;
		int status = 0;
		
		try{
		
			PreparedStatement ps = this.getConnection().prepareStatement(MonitorSQL.DELETE);
			ps.setString(1,idMonitor);
			status = ps.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
		
		
		
	}
	
	public void salveOuAtualiza(MonitorMovel monitor) throws Exception{
		
		try{
			this.busca(monitor.getId());
			this.atualizar(monitor);
		}catch(MonitorMovelException e){
			this.salvar(monitor);
		}
	}                                                                             
   

 }




