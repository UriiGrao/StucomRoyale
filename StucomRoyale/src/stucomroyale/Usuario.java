/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stucomroyale;

import java.util.*;

/**
 *
 * @author UriiGrao
 */
public class Usuario implements Comparable<Usuario> {

    private final String nombre;
    private final String pass;
    private int nTrofeos = 0;
    private ArrayList<Carta> mazo = new ArrayList<>();

    /**
     * Constructor por defecto con nombre y contraseña
     *
     * @param nombre
     * @param pass
     */
    public Usuario(String nombre, String pass) {
        this.nombre = nombre;
        this.pass = pass;
    }

    /**
     * constructor con cartas generadas.
     *
     * @param nombre
     * @param password
     * @param grupoCartas
     */
    public Usuario(String nombre, String password, ArrayList<Carta> grupoCartas) {
        this(nombre, password);
        this.mazo = grupoCartas;
    }

    /**
     * funcion para coger nombre de usuario
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * function para coger pass de usuario
     *
     * @return pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * function para coger Trofeos del usuario
     *
     * @return nTrofeos
     */
    public int getnTrofeos() {
        return nTrofeos;
    }

    /**
     * function para cambiar trofeos del usuario.
     *
     * @param nTrofeos
     */
    public void setnTrofeos(int nTrofeos) {
        this.nTrofeos = nTrofeos;
    }

    /**
     * function para coger mazo de usuario
     *
     * @return
     */
    public ArrayList<Carta> getMazo() {
        return mazo;
    }

    /**
     * function para cambiar el mazo de usuario.
     *
     * @param mazo
     */
    public void setMazo(ArrayList<Carta> mazo) {
        this.mazo = mazo;
    }

    /**
     * function para añadir en mazo una carta.
     *
     * @param carta
     */
    public void setCarta(Carta carta) {
        this.mazo.add(carta);
    }

    /**
     * function de comparar los trofeos de los usuarios y devuelve sort.
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Usuario o) {
        // devuelve 0 (Las cadenas son iguales), -1 (la cadena 1 es menor que la cadena 2.) o 1 (la cadena 1 es mayor que la cadena 2.)
        if (nTrofeos > o.getnTrofeos()) {
            return -1;
        } else if (nTrofeos < o.getnTrofeos()) {
            return 1;
        } else {
            return 0;
        }
    }

}
