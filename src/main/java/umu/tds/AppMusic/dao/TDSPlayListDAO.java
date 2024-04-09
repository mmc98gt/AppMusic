package umu.tds.AppMusic.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.AppMusic.modelo.Cancion;
import umu.tds.AppMusic.modelo.PlayList;
import umu.tds.AppMusic.modelo.Usuario;
import umu.tds.AppMusic.dao.*;

public final class TDSPlayListDAO implements PlayListDao {

	private static final String PLAYLIST = "PlayList";
	private static final String NOMBRE = "nombre";
	private static final String CANCIONES = "cancion";
	private ServicioPersistencia servPersistencia;

	public TDSPlayListDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	@Override
	public void insertPlayList(PlayList playList) {
		Entidad ePlayList = this.playListToEntidad(playList);
		ePlayList = servPersistencia.registrarEntidad(ePlayList);
		playList.setId(ePlayList.getId());
	}

	@Override
	public PlayList getPlayListByName(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlayList> getAllPlayLists() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updatePlayList(PlayList playList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deletePlayList(PlayList playlist) {
		 Entidad ePlaylist = servPersistencia.recuperarEntidad(playlist.getId());
	        if(ePlaylist == null) return;

	        // Se borra la playlist
	        servPersistencia.borrarEntidad(ePlaylist);
	}

	private Entidad playListToEntidad(PlayList playList) {
		Entidad ePlayList = new Entidad();
		ePlayList.setNombre(PLAYLIST);
		ePlayList.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad(PLAYLIST, playList.getNombre()),
				new Propiedad(CANCIONES, obtenerIdCanciones(playList.getCanciones())))));

		return ePlayList;
	}

	private PlayList entidadToPlayList(Entidad ePlayList) {
		String nombre = servPersistencia.recuperarPropiedadEntidad(ePlayList, NOMBRE);
		String cancionesID = servPersistencia.recuperarPropiedadEntidad(ePlayList, CANCIONES);
		List<Cancion> canciones = new LinkedList<>();

		if (cancionesID != null) {
			for (String c : cancionesID.trim().split(" ")) {
				try {
					if (!c.equals("")) {
						Cancion cancion = FactoriaDao.getInstancia().getCancionDAO()
								.obtenerCancionPorId(Integer.parseInt(c));
						if (cancion != null)
							canciones.add(cancion);
					}
				} catch (DAOException e) {
					e.printStackTrace();
				}
			}
		}

		PlayList playlist = new PlayList(nombre, canciones);
		playlist.setId(ePlayList.getId());

		return playlist;
	}

	public List<PlayList> obtenerTodasLasPlayList() {
		return servPersistencia.recuperarEntidades(PLAYLIST).stream().map(this::entidadToPlayList)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	private String obtenerIdCanciones(List<Cancion> canciones) {
		return canciones.stream().map(song -> Integer.toString(song.getId())).collect(Collectors.joining(" "));
	}

}
