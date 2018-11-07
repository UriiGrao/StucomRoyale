/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipos;

import stucomroyale.Carta;

/**
 * Aqui tenemos la clase Hechizo donde tenemos constructor de Hechizo mas sus
 * propios elementos.
 *
 * @author UriiGrao
 */
public class Hechizo extends Carta {

    private final int alcance;

    /**
     * Constructor de Hechizo donde llamamos a la clase Carta y le pasamos los
     * parametros. tambien tenemos el alcance que es su propio parametro para el
     * juego.
     *
     * @param nombre
     * @param nAtaque
     * @param nDefensa
     * @param costElexir
     * @param vida
     * @param alcance
     */
    public Hechizo(String nombre, int nAtaque, int nDefensa, int costElexir, int vida, int alcance) {
        super(nombre, nAtaque, nDefensa, costElexir, vida);
        this.alcance = alcance;
    }

    /**
     * Sirve pare recibir el alcance.
     *
     * @return
     */
    public int getAlcance() {
        return alcance;
    }

    /**
     * sirve para calcular el daño con alcance y ataque que tiene la carta.
     *
     * @param alcance
     * @param ataque
     * @return
     */
    public int nuevoCalculoAtaque(int alcance, int ataque) {
        int ataqueFinal = ataque + alcance;
        return ataqueFinal;
    }

    /**
     * sirve para calcular la defensa con alcance y defensa que tiene la carta.
     *
     * @param alcance
     * @param defensa
     * @return
     */
    public int nuevoCalculoDefensa(int alcance, int defensa) {
        int defensaFinal = defensa + alcance;
        return defensaFinal;
    }
}
