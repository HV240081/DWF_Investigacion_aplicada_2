package sv.edu.udb.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sv.edu.udb.repository.domain.Videojuego;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class VideojuegoRepositoryTest {

    @Autowired
    private VideojuegoRepository videojuegoRepository;

    @Test
    public void testGuardarYBuscarVideojuego() {
        Videojuego v = new Videojuego();
        v.setNombre("Zelda");
        v.setGenero("Aventura");
        v.setCalificacion(9.5);

        Videojuego guardado = videojuegoRepository.save(v);

        assertNotNull(guardado.getId());

        Optional<Videojuego> encontrado = videojuegoRepository.findById(guardado.getId());
        assertTrue(encontrado.isPresent());
        assertEquals("Zelda", encontrado.get().getNombre());
    }

    @Test
    public void testEliminarVideojuego() {
        Videojuego v = new Videojuego();
        v.setNombre("Mario");
        v.setGenero("Plataforma");
        v.setCalificacion(8.7);

        Videojuego guardado = videojuegoRepository.save(v);
        Long id = guardado.getId();

        videojuegoRepository.deleteById(id);
        Optional<Videojuego> encontrado = videojuegoRepository.findById(id);
        assertFalse(encontrado.isPresent());
    }
}
