package umu.tds.AppMusic.modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombre;
    private boolean premium;
    private List<PlayList> playlists;

    // Constructor
    public Usuario(String nombre) {
        this.nombre = nombre;
        this.premium = false; // Asumiendo que por defecto no es premium
        this.playlists = new ArrayList<>();
    }

    // Métodos
    public void realizarPago() {
        // Lógica para realizar el pago y convertirse en usuario premium
        this.premium = true; // Por ejemplo, puedes cambiar el estado a premium después del pago
    }

    public void addPlayList(PlayList playList) {
        if (playList != null) {
            this.playlists.add(playList);
        }
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public List<PlayList> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlayList> playlists) {
        this.playlists = playlists;
    }
}

