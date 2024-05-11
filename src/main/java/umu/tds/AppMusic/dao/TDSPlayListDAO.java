package umu.tds.AppMusic.dao;

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

public final class TDSPlayListDAO implements PlayListDao {

	private static final String PLAYLIST = "PlayList";
	private static final String NOMBRE = "nombre";
	private static final String CANCIONES = "cancion";
	private ServicioPersistencia servPersistencia;

	public TDSPlayListDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	@Override
	public PlayList getPlayListByName(String nombre) {
		return servPersistencia.recuperarEntidades(PLAYLIST).stream()
				.filter(eCancion -> servPersistencia.recuperarPropiedadEntidad(eCancion, NOMBRE).equals(nombre))
				.findFirst().map(this::entidadToPlayList).orElse(null);
	}

	@Override
	public List<PlayList> getAllPlayLists() {
		return servPersistencia.recuperarEntidades(PLAYLIST).stream().map(this::entidadToPlayList)
				.collect(Collectors.toList());
	}

	@Override
	public void updatePlayList(PlayList playList) {
		Entidad ePlaylist = servPersistencia.recuperarEntidad(playList.getId());
		if (ePlaylist == null) {
			// Manejo del caso cuando la entidad no se encuentra.
			// Podrías lanzar una excepción o manejarlo de otra manera dependiendo de tu
			// caso de uso.
			throw new IllegalStateException("PlayList not found with ID: " + playList.getId());
		}

		for (Propiedad prop : ePlaylist.getPropiedades()) {
			if (prop.getNombre().equals(NOMBRE)) {
				prop.setValor(playList.getNombre());
			} else if (prop.getNombre().equals(CANCIONES)) {
				prop.setValor(obtenerIdCanciones(playList.getCanciones()));
			}
			servPersistencia.modificarPropiedad(prop);
		}
	}

	@Override
	public void deletePlayList(PlayList playlist) {
		Entidad ePlaylist = servPersistencia.recuperarEntidad(playlist.getId());
		if (ePlaylist == null)
			return;
		servPersistencia.borrarEntidad(ePlaylist);
	}

	private Entidad playListToEntidad(PlayList playList) {
		Entidad ePlayList = new Entidad();
		ePlayList.setNombre(PLAYLIST);
		ePlayList.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad(NOMBRE, playList.getNombre()),
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

	public void agregarPlaylist(PlayList playlist) {
		Entidad ePlaylist = playListToEntidad(playlist);
		ePlaylist = servPersistencia.registrarEntidad(ePlaylist);
		playlist.setId(ePlaylist.getId());
	}

	public List<PlayList> obtenerTodasLasPlayList() {
		return servPersistencia.recuperarEntidades(PLAYLIST).stream().map(this::entidadToPlayList)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	private String obtenerIdCanciones(List<Cancion> canciones) {
		return canciones.stream().map(song -> Integer.toString(song.getId())).collect(Collectors.joining(" "));
	}

	public PlayList obtenerPlaylistPorId(int id) {

		Entidad entidad = servPersistencia.recuperarEntidad(id);
		String nombre = servPersistencia.recuperarPropiedadEntidad(entidad, NOMBRE);
		String cancionesId = servPersistencia.recuperarPropiedadEntidad(entidad, CANCIONES);
		LinkedList<Cancion> canciones = new LinkedList<>();

		if (cancionesId != null) {
			for (String c : cancionesId.trim().split(" ")) {
				try {
					if (!c.equals("")) {
						Cancion cancionBd = FactoriaDao.getInstancia().getCancionDAO()
								.obtenerCancionPorId(Integer.parseInt(c));
						if (cancionBd != null)
							canciones.add(cancionBd);
					}
				} catch (DAOException e) {
					e.printStackTrace();
				}
			}
		}
		PlayList playlist = new PlayList(nombre, canciones);
		playlist.setId(id);

		return playlist;
	}

	public PlayList obtenerPlaylistPorNombre(String nombre) {
		return servPersistencia.recuperarEntidades(PLAYLIST).stream()
				.filter(ePlaylist -> servPersistencia.recuperarPropiedadEntidad(ePlaylist, NOMBRE).equals(nombre))
				.map(this::entidadToPlayList).findFirst().orElse(null);
	}

}
