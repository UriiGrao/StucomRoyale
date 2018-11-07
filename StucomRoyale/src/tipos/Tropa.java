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
public class Tropa extends Carta {

    private final int fuerza;

    /**
     * Constructor de Tropa donde llamamos a la clase Carta y le pasamos los
     * datos. tambien tenemos el nuevoDaño que es su propio dato para el
     * juego.
     *
     * @param nombre
     * @param nAtaque
     * @param nDefensa
     * @param costElexir
     * @param vida
     * @param fuerza
     */
    public Tropa(String nombre, int nAtaque, int nDefensa, int costElexir, int vida, int fuerza) {
        super(nombre, nAtaque, nDefensa, costElexir, vida);
        this.fuerza = fuerza;
    }

    /**
     * Siver para coger la fuerza.
     *
     * @return fuerza
     */
    public int getFuerza() {
        return fuerza;
    }

    /**
     * function para calcular el nuevo Daño de la carta a partir de fuerza y
     * ataque.
     *
     * @param ataque
     * @param fuerza
     * @return nuevoDaño
     */
    public int calcularNuevoDaño(int ataque, int fuerza) {
        int nuevoDaño = fuerza * ataque;
        return nuevoDaño;
    }
}
