package umu.tds.AppMusic.dao;

/**
 * Factoria abstracta DAO.
 */

/**
 * Factoria abstracta para obtener adaptadores DAO.
 */
public abstract class FactoriaDao {

    public static final String DAO_TDS = "umu.tds.AppMusic.dao.TDSFactoriaDAO";

    private static FactoriaDao unicaInstancia = null;

    /**
     * Crea un tipo de factoria DAO. Solo existe el tipo TDSFactoriaDAO.
     *
     * @param tipo el tipo de factoria DAO.
     * @return la instancia única de FactoriaDao.
     * @throws DAOException si ocurre un error durante la creación de la instancia.
     */
    @SuppressWarnings("deprecation")
    public static FactoriaDao getInstancia(String tipo) throws DAOException {
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

    /**
     * Obtiene la instancia única de FactoriaDao con el tipo por defecto.
     *
     * @return la instancia única de FactoriaDao.
     * @throws DAOException si ocurre un error durante la creación de la instancia.
     */
    public static FactoriaDao getInstancia() throws DAOException {
        return getInstancia(FactoriaDao.DAO_TDS);
    }

    protected FactoriaDao() {
    }

    // Métodos factoría para obtener adaptadores DAO

    /**
     * Obtiene el adaptador UsuarioDao.
     *
     * @return el adaptador UsuarioDao.
     */
    public abstract UsuarioDao getUsuarioDAO();

    /**
     * Obtiene el adaptador CancionDao.
     *
     * @return el adaptador CancionDao.
     */
    public abstract CancionDao getCancionDAO();

    /**
     * Obtiene el adaptador PlayListDao.
     *
     * @return el adaptador PlayListDao.
     */
    public abstract PlayListDao getPlayListDAO();

    /**
     * Obtiene el adaptador TDSCancionXMLDao.
     *
     * @return el adaptador TDSCancionXMLDao.
     */
    public abstract TDSCancionXMLDao getCancionXMLDao();
}