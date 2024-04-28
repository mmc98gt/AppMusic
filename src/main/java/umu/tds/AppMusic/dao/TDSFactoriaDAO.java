package umu.tds.AppMusic.dao;

/** 
 * Factoria concreta DAO para el Servidor de Persistencia de la asignatura TDS.
 * 
 */

public final class TDSFactoriaDAO extends FactoriaDao {
	
	public TDSFactoriaDAO() {	}
	
	@Override
	public UsuarioDao getUsuarioDAO() {	
		return new TDSUsuarioDAO(); 
	}

	@Override
	public CancionDao getCancionDAO() {
		return new TDSCancionDao(); 
	}

	@Override
	public TDSCancionXMLDao getCancionXMLDao() {
		return new TDSCancionXMLDao();
	}

	@Override
	public PlayListDao getPlayListDAO() {
		return new TDSPlayListDAO(); 
	}

}
