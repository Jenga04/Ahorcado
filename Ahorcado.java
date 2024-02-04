package org.example;
import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;

public class Ahorcado extends JFrame {

    private final String Palabra;
    private final boolean[] GuionPalabra;
    private int Intentos;

    public Ahorcado() {
        //Ventana
        setTitle ("Ahorcado");
        setSize (300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //Palabra aleatroria elegida y sus intentos
        Palabra = getPalabraAleatoria();
        GuionPalabra = getGuionDePalabra(Palabra);
        Intentos = 6;
        //Intentos restantes, se reduce por cada intento fallido
        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setText(getPalabraSecreta() + "\nIntentos restantes: " + Intentos);
        add(outputArea, BorderLayout.CENTER);
        //Aparte, texto de escribir una letra
        JLabel inputLabel = new JLabel("Ingresa una letra:");
        JTextField inputField = getjTextField(outputArea);
        //Entrada de letra
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputLabel, BorderLayout.NORTH);
        inputPanel.add(inputField, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
    }
//Verificación de letra adivinada
    private String getPalabraSecreta(){
        StringBuilder palabraSecreta = new StringBuilder();
        for (int x=0; x<Palabra.length(); x++) {
            if (GuionPalabra[x]) {
                palabraSecreta.append(Palabra.charAt(x)).append(" ");
            } else {
                palabraSecreta.append(" _ ");
            }
        }
        return palabraSecreta.toString();
    }
    //Diálogo de victoria o derrota
    private void mostrarResultado(){
        if (juegoGanado()) {
            JOptionPane.showMessageDialog(this, "¡Has ganado!");
        } else {
            JOptionPane.showMessageDialog(this, "Has perdido, la palabra correcta era " + Palabra);
        }
    }
    //Comprobacion de que siguen habiendo letras por adivinar
    private boolean juegoGanado() {
        for (boolean b: GuionPalabra) {
            if (!b) {
                return false;
            }
        }
        return true;
    }
    //Control de la entrada de letra
    private JTextField getjTextField(JTextArea outputArea){
        JTextField inputField = new JTextField();
        inputField.addActionListener(e -> {
            String Letra = inputField.getText().toLowerCase();
            inputField.setText("");
            if (Letra.length() == 1 && Character.isLetter(Letra.charAt(0))) {
                boolean LetraCorrecta = false;
                for (int x = 0; x < Palabra.length(); x++) {
                    if (Palabra.charAt(x) == Letra.charAt(0)) {
                        GuionPalabra[x] = true;
                        LetraCorrecta = true;
                    }
                }
                if (!LetraCorrecta) {
                    Intentos--;
                }
                if (Intentos <= 0 || juegoGanado()) {
                    mostrarResultado();
                } else {
                    outputArea.setText(getPalabraSecreta() + "\nIntentos restantes: " + Intentos);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Debes ingresar una única letra.");
            }
        });
        return inputField;
    }
//Palabras y selección aleatoria de una
    private String getPalabraAleatoria() {
        String[] PalabrasAleatorias = {"Sonido", "Acuario", "Inteligente", "Nebuloso", "Divertido", "Circuito", "Connotar", "Contaminación", "Extravagante", "Exuberante", "Asociado", "Calidez", "Aproximación", "Frontera", "Atemporal", "Interacción", "Sangre", "Observar", "Crujido", "Firma", "Maduro", "Gratis", "Sabroso", "Holístico", "Renunciar", "Columna vertebral", "Alfabetizado", "Anhelar", "Descuidado", "Único", "Delicioso", "Juvenil", "Problema", "Glandular", "Abducción", "Ansioso", "Objeto", "Reflexionar", "Tolete", "Implosionar", "Caprichoso", "Estallar", "Dimensión", "Crecimiento", "Enterrado", "Silencio", "Frustración", "Devoto", "Buffet", "Décimo", "Experto", "Humilde", "Esterilizar", "Picayune", "Preparar", "Pintura", "También", "Privilegio", "Diamétrico", "Hereje", "Nudoso", "Pintamonas", "Alfiler", "Fascinado", "Huérfano", "Atrocidad", "Obligar", "Mielada"};
        return PalabrasAleatorias[(int) (Math.random() * PalabrasAleatorias.length)].toLowerCase();
    }
//De la palabra seleccionada, define los guiones de la palabra
    private boolean[] getGuionDePalabra (String palabra) {
        return new boolean[palabra.length()];
    }
//Ejecución de la ventana
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Ahorcado juego = new Ahorcado();
            juego.setVisible(true);
        });
    }
}