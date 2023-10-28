package umu.tds.AppMusic.dao;


/**
 * Factoria abstracta DAO.
 */

public abstract class FactoriaDao {
	
	public static final String DAO_TDS = "umu.tds.dao.TDSFactoriaDAO";

	private static FactoriaDao unicaInstancia = null;
	
	/** 
	 * Crea un tipo de factoria DAO.
	 * Solo existe el tipo TDSFactoriaDAO
	 */
	@SuppressWarnings("deprecation")
	public static FactoriaDao getInstancia(String tipo) throws DAOException{
		if (unicaInstancia == null)
			try { 
				unicaInstancia=(FactoriaDao) Class.forName(tipo).newInstance();
			} catch (Exception e) {	
				throw new DAOException(e.getMessage());
		} 
		return unicaInstancia;
	}
	

	public static FactoriaDao getInstancia() throws DAOException{
		return getInstancia(FactoriaDao.DAO_TDS);
	}

	protected FactoriaDao (){}
	
	// Metodos factoria para obtener adaptadores
	
	public abstract UsuarioDao getUsuarioDao();	
}