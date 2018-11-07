/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipos;

import stucomroyale.Carta;

/**
 *
 * @author UriiGrao
 */
public class Estructura extends Carta {

    private final int escudo;

    /**
     * Constructor de Estructura donde llamamos a la clase Carta y le pasamos
     * los datos. tambien tenemos el nuevaDefensa que es su propio dato para el
     * juego.
     *
     * @param nombre
     * @param nAtaque
     * @param nDefensa
     * @param costElexir
     * @param vida
     * @param escudo
     */
    public Estructura(String nombre, int nAtaque, int nDefensa, int costElexir, int vida, int escudo) {
        super(nombre, nAtaque, nDefensa, costElexir, vida);
        this.escudo = escudo;
    }

    /**
     * para coger el escudo de la carta
     *
     * @return escudo
     */
    public int getEscudo() {
        return escudo;
    }

    /**
     * calcular la defensa a partir de escudo y defensa.
     *
     * @param defensa
     * @param escudo
     * @return nuevaDefensa
     */
    public int calcularNuevaDefensa(int defensa, int escudo) {
        int nuevaDefensa = defensa * escudo;
        return nuevaDefensa;
    }
}
