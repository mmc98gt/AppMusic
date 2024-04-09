package umu.tds.AppMusic.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import umu.tds.AppMusic.dao.DAOException;
import umu.tds.AppMusic.dao.FactoriaDao;
import umu.tds.AppMusic.modelo.Cancion;
import umu.tds.AppMusic.modelo.PlayList;
import umu.tds.AppMusic.modelo.Usuario;

public enum RepositorioPlayList {
	INSTANCE;

    private FactoriaDao factoria;
    private Map<Integer, PlayList> playlist;
    
    private RepositorioPlayList() {
        playlist = new HashMap<>();
        
        try {
        	factoria = FactoriaDao.getInstancia();
            List<PlayList> listaPlaylist = factoria.getPlayListDAO().obtenerTodasLasPlayList();
            listaPlaylist.forEach(lista -> {
                playlist.put(lista.getId(), lista);
            });
        } catch (DAOException eDAO) {
            eDAO.printStackTrace();
        }
    }
    
    
}
