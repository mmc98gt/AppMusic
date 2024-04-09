package umu.tds.AppMusic.dao;


/**
 * Factoria abstracta DAO.
 */

public abstract class FactoriaDao {
	
	public static final String DAO_TDS = "umu.tds.AppMusic.dao.TDSFactoriaDAO";

	private static FactoriaDao unicaInstancia = null;
	
	/** 
	 * Crea un tipo de factoria DAO.
	 * Solo existe el tipo TDSFactoriaDAO
	 */
	@SuppressWarnings("deprecation")
	public static FactoriaDao getInstancia(String tipo) throws DAOException{
	    if (unicaInstancia == null) {
	        try { 
	            unicaInstancia = (FactoriaDao) Class.forName(tipo).newInstance();
	        } catch (ClassNotFoundException e) {
	            throw new DAOException("La clase no fue encontrada: " + e.getMessage());
	        } catch (InstantiationException e) {
	            throw new DAOException("Error al instanciar: " + e.getMessage());
	        } catch (IllegalAccessException e) {
	            throw new DAOException("Acceso ilegal: " + e.getMessage());
	        } catch (Exception e) {
	            throw new DAOException("Error general: " + e.getMessage());
	        }
	    } 
	    return unicaInstancia;
	}
	

	public static FactoriaDao getInstancia() throws DAOException{
		return getInstancia(FactoriaDao.DAO_TDS);
	}

	protected FactoriaDao (){}
	
	// Metodos factoria para obtener adaptadores
	
	public abstract TDSUsuarioDAO getUsuarioDAO();
	public abstract TDSCancionDao getCancionDAO();
	public abstract TDSPlayListDAO getPlayListDAO();
	public abstract TDSCancionXMLDao getCancionXMLDao();
}