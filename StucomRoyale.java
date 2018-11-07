/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stucomroyale;

import java.util.*;
import static stucomroyale.StucomRoyale.atacar;
import static stucomroyale.StucomRoyale.miniAtac;
import tipos.Estructura;
import tipos.Hechizo;
import tipos.Tropa;
import utilidades.EntradaDatos;

/**
 *
 * @author UriiGrao
 */
public class StucomRoyale {

    public static ArrayList<Carta> cartas = new ArrayList<>();
    public static ArrayList<Usuario> jugadores = new ArrayList<>();

    /*
     * @param args the command line arguments
     */
    public static void main(String[] args) throws CloneNotSupportedException {

        crearCartas();
        crearJugadores();

        // MENU + OPCIONES
        boolean salir = false;
        while (!salir) {
            switch (menu()) {
                case 1:
                    caseUno();
                    break;
                case 2:
                    caseDos();
                    break;
                case 3:
                    caseTres();
                    break;
                case 4:
                    System.out.println("See You!");
                    salir = true;
                    break;
            }
        }
    }

    /**
     * Aqui tenemos el caso 3 de la practica sirve para mostrar usuarios y
     * ordenarlos por trofeos.
     */
    private static void caseTres() {
        int num = 1;
        //vamos a ordenar la arraylist de jugadores con Collections.sort.
        Collections.sort(jugadores);
        for (Usuario jugador : jugadores) {
            System.out.println("");
            System.out.println(num + " - " + jugador.getNombre() + " Trofeos: " + jugador.getnTrofeos());
            num++;
        }
    }

    /**
     * Aqui tenemos el caso 2 la batalla entre usuarios.
     */
    private static void caseDos() {
        //creamos los array para clonar las cartas
        List<Carta> cartasJ1 = new ArrayList<>();
        List<Carta> cartasJ2 = new ArrayList<>();

        String nombre = EntradaDatos.pedirCadena("Nombre Usuaio");
        String pass = EntradaDatos.pedirCadena("Password Usuario");

        String nombre2 = EntradaDatos.pedirCadena("Nombre Usuaio 2");
        String pass2 = EntradaDatos.pedirCadena("Password Usuario 2");

        Usuario usu1 = validarUsuario(nombre, pass);
        Usuario usu2 = validarUsuario(nombre2, pass2);

        //comprobamos que no se ha validado 2 veces el mismo jugador
        if (usu1 != null && usu2 != null && usu1 != usu2) {
            System.out.println("Bien entrados los dos Usuarios");

            cartasJ1 = elegirCartas(usu1, usu1.getMazo());
            cartasJ2 = elegirCartas(usu2, usu2.getMazo());

            //comprobamos que los array estan llenos
            if (cartasJ1.size() > 0 && cartasJ2.size() > 0) {
                System.out.println("Cartas para la batalla de " + usu1.getNombre() + ":");
                for (Carta card : cartasJ1) {
                    System.out.println(card.getNombre());
                }
                System.out.println("Cartas para la batalla de " + usu2.getNombre() + ":");
                for (Carta card : cartasJ2) {
                    System.out.println(card.getNombre());
                }
            }

            // Ahora vamos atacar.
            accionCartas(cartasJ1, cartasJ2, usu1.getNombre(), usu2.getNombre(), usu1, usu2);

            //Comprobamos ganador.
            ganar(cartasJ1, cartasJ2, usu1, usu2);

        } else {
            System.out.println("Algun usuario esta mal o repetido");
        }
    }

    /**
     * Aqui tenemos el case 1 para mirar el mazo y coger cartas si no se tiene
     * 6.
     *
     * @throws CloneNotSupportedException
     */
    private static void caseUno() throws CloneNotSupportedException {
        String nombre = EntradaDatos.pedirCadena("Dime tu nombre");
        String pass = EntradaDatos.pedirCadena("Dime tu contraseña");

        Usuario caseUnoUsuario = validarUsuario(nombre, pass);
        if (caseUnoUsuario != null) {
            System.out.println("Entraste Bien!");

            System.out.println("Actualmente tienes: " + caseUnoUsuario.getMazo().size() + " Cartas");

            if (caseUnoUsuario.getMazo().size() < 6) {
                System.out.println("Elige Carta por nombre:");
                mostrarCartas();
                String cartaNombre = EntradaDatos.pedirCadena("Elige nombre Carta");
                validarCarta(cambiarNombreaCarta(cartaNombre), caseUnoUsuario);
            } else {
                System.out.println("Ya tienes 6 cartas No Puedes tener mas!");
                for (Carta carta : caseUnoUsuario.getMazo()) {
                    System.out.println("Nombre: " + carta.getNombre());
                    System.out.println("nAtaque: " + carta.getnAtaque());
                    System.out.println("nDefensa: " + carta.getnDefensa());
                    System.out.println("costElixir: " + carta.getCostElexir());
                    System.out.println("Vida: " + carta.getVida());
                    System.out.println("Tipo:" + carta.getClass().getSimpleName());
                    System.out.println("--------------------------------");
                }
            }
        } else {
            System.out.println("Nombre o Password mal!");
        }
    }

    /**
     * Aqui tenemos la funcion de ganar donde le pasamos los mazos + los
     * usuarios y comparamos la vida de todas las cartas y el jugador con mas
     * vida en las cartas gana 5 puntos el otro gana 1 en caso de empate no hay
     * puntos.
     *
     * @param cartas1
     * @param cartas2
     * @param usu1
     * @param usu2
     */
    public static void ganar(List<Carta> cartas1, List<Carta> cartas2, Usuario usu1, Usuario usu2) {
        int vidaJ1 = 0;
        int vidaJ2 = 0;
        //suma la vida que queda
        for (int i = 0; i < 3; i++) {
            vidaJ1 += cartas1.get(i).getVida();
            vidaJ2 += cartas2.get(i).getVida();
        }
        System.out.println("Las vidas son " + vidaJ1 + " - " + vidaJ2);
        //comprobamos quin gana 5 puntos y quien gana 1.
        if (vidaJ1 > vidaJ2) {
            usu1.setnTrofeos(usu1.getnTrofeos() + 5);
            usu2.setnTrofeos(usu2.getnTrofeos() + 1);
            System.out.println("Ganador es: " + usu1.getNombre());
        } else if (vidaJ1 < vidaJ2) {
            usu1.setnTrofeos(usu1.getnTrofeos() + 1);
            usu2.setnTrofeos(usu2.getnTrofeos() + 5);
            System.out.println("Ganador es: " + usu2.getNombre());
        } else {
            System.out.println("EMPATE!! entre: " + usu1.getNombre() + " y " + usu2.getNombre());
        }
    }

    /**
     * function para medir la defensa de la 2n carta y dar el daño dependiendo
     * del tipo.
     *
     * @param damage
     * @param cartas2
     * @param i
     */
    public static void miniAtac(int damage, List<Carta> cartas2, int i) {
        if (cartas2.get(i) instanceof Tropa) {
            int damageT = damage - cartas2.get(i).getnDefensa();
            if (damageT > 0) {
                cartas2.get(i).setVida(cartas2.get(i).getVida() - damageT);
                System.out.println("El daño ha sido de: " + damageT + " La vida actual: " + cartas2.get(i).getVida());
            } else {
                System.out.println("Ataque fallido");
            }
        } else if (cartas2.get(i) instanceof Hechizo) {
            Hechizo cartaH = (Hechizo) cartas2.get(i);
            int damageH = damage - cartaH.nuevoCalculoDefensa(cartaH.getAlcance(), cartaH.getnDefensa());
            if (damageH > 0) {
                cartaH.setVida(cartaH.getVida() - damageH);
                System.out.println("El daño a sido de: " + damageH + " La vida actual: " + cartaH.getVida());
            } else {
                System.out.println("Ataque fallido");
            }

        } else if (cartas2.get(i) instanceof Estructura) {
            Estructura cartaE = (Estructura) cartas2.get(i);
            int damageE = damage - cartaE.calcularNuevaDefensa(cartaE.getnDefensa(), cartaE.getEscudo());
            if (damageE > 0) {
                cartaE.setVida(cartaE.getVida() - damageE);
                System.out.println("El daño ha sido de: " + damageE + " La vida actual: " + cartaE.getVida());
            } else {
                System.out.println("Ataque fallido");
            }
        }
    }

    /**
     * functino para el ataque donde coguemos las 3 cartas y atacamos miramos
     * por tipo de carta.
     *
     * @param cartas1
     * @param cartas2
     */
    public static void atacar(List<Carta> cartas1, List<Carta> cartas2) {
        //acciones de las cartas
        for (int i = 0; i < 3; i++) {
            //comprobamos el tipo de carta
            if (cartas1.get(i) instanceof Tropa) {
                //pasamos la carta al tipo que es
                Tropa cartaT = (Tropa) cartas1.get(i);
                int damage = cartaT.calcularNuevoDaño(cartaT.getnAtaque(), cartaT.getFuerza());
                miniAtac(damage, cartas2, i);

            } else if (cartas1.get(i) instanceof Hechizo) {
                Hechizo cartaH = (Hechizo) cartas1.get(i);
                int damage = cartaH.nuevoCalculoAtaque(cartaH.getAlcance(), cartaH.getnAtaque());
                miniAtac(damage, cartas2, i);

            } else if (cartas1.get(i) instanceof Estructura) {
                int damage = cartas1.get(i).getnAtaque();
                miniAtac(damage, cartas2, i);
            }
        }
    }

    /**
     * funcion para ver que jugador empieza. Tenemos que mirar los trofeos para
     * saber que jugador empieza en caso de empate se usa un random.
     *
     * @param cartas1
     * @param cartas2
     * @param nombre1
     * @param nombre2
     * @param j1
     * @param j2
     */
    public static void accionCartas(List<Carta> cartas1, List<Carta> cartas2, String nombre1, String nombre2, Usuario j1, Usuario j2) {
        //vemos quien comienza

        if (j1.getnTrofeos() > j2.getnTrofeos()) {
            System.out.println("Comienza " + nombre1);
            atacar(cartas1, cartas2);
            atacar(cartas2, cartas1);
        } else if (j1.getnTrofeos() < j2.getnTrofeos()) {
            System.out.println("Comienza " + nombre2);
            atacar(cartas2, cartas1);
            atacar(cartas1, cartas2);
        } else {
            int random = EntradaDatos.numAleatorio(1);
            if (random == 0) {
                System.out.println("Comienza " + nombre1);
                atacar(cartas1, cartas2);
                atacar(cartas2, cartas1);
            } else {
                System.out.println("Comienza " + nombre2);
                atacar(cartas2, cartas1);
                atacar(cartas1, cartas2);
            }
        }
    }

    /**
     * function que muestra todas las cartas. Despues hacemos al jugador que
     * eliga entre las cartas solo 3 a partir de su nombre Si sobrepasa el
     * limite de elexir tendra que elegir otra carta. Las otras dos no las puede
     * cambiar una vez elegidas.
     *
     * @param j
     * @param cartasJ
     * @return
     */
    public static List<Carta> elegirCartas(Usuario j, List<Carta> cartasJ) {
        //creo una lista para las cartas clonadas y un contador para el número máximo de cartas que puede seleccionar
        List<Carta> cartaCopias = new ArrayList<>();
        int contador = 1;
        System.out.println(j.getNombre() + " elige 3 cartas en orden que quieras usarlas, no puedes tener + de 10 de elixir:");
        while (contador < 4) {
            boolean salir = false;
            do { //enseño las cartas del jugador
                for (Carta carta : j.getMazo()) {
                    System.out.println("Nombre carta: " + carta.getNombre() + " Elixir: " + carta.getCostElexir());
                }
                //Le digo que seleccione la carta por el nombre que tiene
                String nombre = EntradaDatos.pedirCadena("Dime el nombre de la " + contador + " Carta : - " + j.getNombre());
                //Compruebo que la carta está en su lista de cartas
                boolean comprobarQueLaCartaEsteEnSuLista = comprobarCarta(j.getMazo(), nombre);
                //compruebo que la carta seleccionada no la haya seleccionado ya
                boolean comprobarSiYaLaHaSeleccionado = comprobarCarta(cartaCopias, nombre);
                //Si esta en la lista y no la ha seleccionado
                if (comprobarQueLaCartaEsteEnSuLista && !comprobarSiYaLaHaSeleccionado) {
                    //obtengo la carta
                    Carta carta = cambiarNombreaCarta(nombre);
                    //compruebo que el coste de elixir no se pase
                    boolean elixir = comprobarCosteElixir(cartaCopias, carta);
                    if (!elixir) {
                        try {
                            //clono la carta
                            Carta copia = (Carta) carta.clone();
                            cartaCopias.add(copia);
                            contador++;
                            salir = true;
                        } catch (CloneNotSupportedException ex) {
                        }
                    } else {
                        System.out.println("El maximo elexir es 10.");
                    }
                } else {
                    System.out.println("Esta carta ya la tienes en el mazo, o no la has puesto.");
                }
            } while (!salir);
        }
        //devuelvo la lista de cartas clonadas
        return cartaCopias;
    }

    /**
     * function para mirar el coste de elixir no sobre pase los 10 elixir al
     * elegir las 3 cartas.
     *
     * @param cartasJugador
     * @param carta
     * @return
     */
    public static boolean comprobarCosteElixir(List<Carta> cartasJugador, Carta carta) {
        //compruebo coste de elixir, metodo para saber si las cartas clonadas y la nueva carta no superan los 10 de mana
        int elixir = carta.getCostElexir();
        for (Carta card : cartasJugador) {
            elixir += card.getCostElexir();
        }
        if (elixir > 10) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * function para comprobar la carta si la tiene o no.
     * @param lista
     * @param carta2
     * @return 
     */
    public static boolean comprobarCarta(List<Carta> lista, String carta2) {
        //comprobar si tiene la carta o no.
        boolean laTiene = false;
        for (Carta carta1 : lista) {
            if (carta1.getNombre().equalsIgnoreCase(carta2)) {
                return true;
            }
        }
        return laTiene;
    }

    /**
     * aqui añadimos la carta al usario.
     *
     * @param carta
     * @param usuario
     * @throws CloneNotSupportedException
     */
    private static void añadirCarta(Carta carta, Usuario usuario) throws CloneNotSupportedException {
        Carta cartaClonada = (Carta) carta.clone();
        usuario.setCarta(cartaClonada);
    }

    /**
     * Aqui cambiamos de tener el nombre de la Carta a tener el objeto de la
     * carta con ese nombre
     *
     * @param nombreCarta
     * @return
     */
    private static Carta cambiarNombreaCarta(String nombreCarta) {
        for (Carta carta : cartas) {
            if (carta.getNombre().equalsIgnoreCase(nombreCarta)) {
                return carta;
            }
        }
        return null;
    }

    /**
     * Aqui miramos si el nombre introducido de la carta es valido. Tambien
     * miramos si la carta ya esta en el mazo o no.
     *
     * @param carta
     * @param jugador
     * @throws CloneNotSupportedException
     */
    private static void validarCarta(Carta carta, Usuario jugador) throws CloneNotSupportedException {
        int contador = 0;
        if (carta == null) {
            System.out.println("Carta No Existe");
        } else if (carta != null) {
            for (Carta mazo : jugador.getMazo()) {
                if (mazo.equals(carta)) {
                    contador++;
                }
            }
            if (contador == 1) {
                System.out.println("Carta Ya Existe!");
                System.out.println(jugador.getMazo().size());
            } else {
                System.out.println("Carta Registrada!");
                añadirCarta(carta, jugador);
            }
        }
    }

    /**
     * Mostramos todas las cartas.
     */
    private static void mostrarCartas() {
        for (Carta carta : cartas) {
            System.out.println("Nombre: " + carta.getNombre());
            System.out.println("nAtaque: " + carta.getnAtaque());
            System.out.println("nDefensa: " + carta.getnDefensa());
            System.out.println("costElixir: " + carta.getCostElexir());
            System.out.println("Vida: " + carta.getVida());
            System.out.println("Tipo:" + carta.getClass().getSimpleName());
            System.out.println("--------------------------------");
        }
    }

    /**
     * Miramos si el usuario introducido existe y si se ha introducido bien la
     * password.
     *
     * @param nombre
     * @param pass
     * @return
     */
    private static Usuario validarUsuario(String nombre, String pass) {
        for (Usuario usuario : jugadores) {
            if (usuario.getNombre().equalsIgnoreCase(nombre)) {
                if (usuario.getPass().equals(pass)) {
                    return usuario;
                }
            }
        }
        return null;
    }

    /**
     * function para generar las cartas del juego.
     */
    private static void crearCartas() {
        // GENERAR TROPAS
        cartas.add(new Tropa("Pegaso", 2, 2, 2, 30, 2));
        cartas.add(new Tropa("Mireis", 5, 4, 4, 80, 5));
        cartas.add(new Tropa("Orito", 4, 5, 3, 100, 5));

        //GENERAR ESTRUCTURAS
        cartas.add(new Estructura("Pared", 2, 2, 1, 90, 3));
        cartas.add(new Estructura("Torre", 4, 4, 3, 60, 2));
        cartas.add(new Estructura("Castillo", 5, 5, 5, 100, 5));

        //GENERAR HECHIZOS
        cartas.add(new Hechizo("Veneno", 3, 3, 2, 20, 5));
        cartas.add(new Hechizo("Bola Fuego", 4, 2, 3, 50, 7));
        cartas.add(new Hechizo("Cohete", 5, 5, 5, 80, 10));
    }

    /**
     * 4 usuarios generados por defecto para poder jugar.
     */
    private static void crearJugadores() throws CloneNotSupportedException {
        // mazos de jugadores 1 y 2.
        List<Carta> mazo1 = new ArrayList<>();

        // GENERAR JUGADORES 
        jugadores.add(new Usuario("Orito", "1234"));
        jugadores.add(new Usuario("Mireis", "1234"));
        jugadores.add(new Usuario("JoLuis", "1234"));
        jugadores.add(new Usuario("MiniAna", "1234"));

        // jugadores con cartas.
        jugadores.add(new Usuario("oriol", "1111"));
        jugadores.add(new Usuario("ana", "1111"));
        for (Usuario j : jugadores) {
            if (j.getNombre().equalsIgnoreCase("oriol")) {
                validarCarta(cambiarNombreaCarta("Cohete"), j);
                validarCarta(cambiarNombreaCarta("Veneno"), j);
                validarCarta(cambiarNombreaCarta("Pared"), j);
                validarCarta(cambiarNombreaCarta("Torre"), j);
                validarCarta(cambiarNombreaCarta("Pegaso"), j);
                validarCarta(cambiarNombreaCarta("Orito"), j);
            } else if (j.getNombre().equalsIgnoreCase("ana")) {
                validarCarta(cambiarNombreaCarta("Cohete"), j);
                validarCarta(cambiarNombreaCarta("Veneno"), j);
                validarCarta(cambiarNombreaCarta("Pared"), j);
                validarCarta(cambiarNombreaCarta("Torre"), j);
                validarCarta(cambiarNombreaCarta("Pegaso"), j);
                validarCarta(cambiarNombreaCarta("Orito"), j);
            }
        }

    }

    /**
     * function de menu para poder realizar el juego y no llenar mucho el void
     * main.
     *
     * @return
     */
    private static int menu() {

        // MENU DONDE SE LLAMA QUE OPCION QUIERES.
        Scanner entrada = new Scanner(System.in);

        System.out.println("-- MENU --");
        System.out.println("1. Conseguir Cartas");
        System.out.println("2. Batalla");
        System.out.println("3. Ranking");
        System.out.println("4. Salir.");
        return entrada.nextInt();
    }

}
