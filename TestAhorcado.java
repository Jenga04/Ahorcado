import org.example.Ahorcado;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestAhorcado {
    @Test
    public void TestAhorcado() {
        Ahorcado getPalabraAleatoria = new Ahorcado();
        assertEquals("sonido", getPalabraAleatoria.getPalabraAleatoria("sonido"));
    }
}