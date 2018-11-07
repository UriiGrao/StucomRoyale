/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stucomroyale;

import java.util.Objects;

/**
 *
 * @author UriiGrao
 */
public abstract class Carta implements Cloneable {

    private String nombre;
    private int nAtaque;
    private int nDefensa;
    private int costElexir;
    private int vida;

    /**
     * constructor de carta con estos datos.
     *
     * @param nombre
     * @param nAtaque
     * @param nDefensa
     * @param costElexir
     * @param vida
     */
    public Carta(String nombre, int nAtaque, int nDefensa, int costElexir, int vida) {
        this.nombre = nombre;
        this.nAtaque = nAtaque;
        this.nDefensa = nDefensa;
        this.costElexir = costElexir;
        this.vida = vida;
    }

    /**
     * Get the value of nombre
     *
     * @return the value of nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the value of nombre
     *
     * @param nombre new value of nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Get the value of nAtaque
     *
     * @return the value of nAtaque
     */
    public int getnAtaque() {
        return nAtaque;
    }

    /**
     * Set the value of nAtaque
     *
     * @param nAtaque new value of nAtaque
     */
    public void setnAtaque(int nAtaque) {
        this.nAtaque = nAtaque;
    }

    /**
     * Get the value of nDefensa
     *
     * @return the value of nDefensa
     */
    public int getnDefensa() {
        return nDefensa;
    }

    /**
     * Set the value of nDefensa
     *
     * @param nDefensa new value of nDefensa
     */
    public void setnDefensa(int nDefensa) {
        this.nDefensa = nDefensa;
    }

    /**
     * Get the value of costElexir
     *
     * @return the value of costElexir
     */
    public int getCostElexir() {
        return costElexir;
    }

    /**
     * Set the value of costElexir
     *
     * @param costElexir new value of costElexir
     */
    public void setCostElexir(int costElexir) {
        this.costElexir = costElexir;
    }

    /**
     * Get the value of vida
     *
     * @return the value of vida
     */
    public int getVida() {
        return vida;
    }

    /**
     * Set the value of vida
     *
     * @param vida new value of vida
     */
    public void setVida(int vida) {
        this.vida = vida;
    }

    /**
     * Para clonar la carta.
     *
     * @return super.clone()
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * para mirar si son iguales a la hora de comparar.
     *
     * @param obj
     * @return nombre.equalsIgnoreCase(other.getNombre())
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Carta other = (Carta) obj;
        return nombre.equalsIgnoreCase(other.getNombre());
    }

    /**
     * Lo usamos para el equals.
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

}
