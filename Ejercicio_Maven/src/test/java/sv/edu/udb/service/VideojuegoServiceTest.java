package sv.edu.udb.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.server.ResponseStatusException;
import sv.edu.udb.controller.request.VideojuegoRequest;
import sv.edu.udb.controller.response.VideojuegoResponse;
import sv.edu.udb.repository.VideojuegoRepository;
import sv.edu.udb.repository.domain.Videojuego;
import sv.edu.udb.service.mapper.VideojuegoMapper;

public class VideojuegoServiceTest {

    @Mock
    private VideojuegoRepository videojuegoRepository;

    @Mock
    private VideojuegoMapper videojuegoMapper;

    @InjectMocks
    private VideojuegoService videojuegoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerVideojuegoPorId_existente() {
        Long id = 1L;
        Videojuego videojuego = new Videojuego();
        videojuego.setId(id);

        VideojuegoResponse response = new VideojuegoResponse(id, "FIFA", "Deportes", 8.7);

        when(videojuegoRepository.findById(id)).thenReturn(Optional.of(videojuego));
        when(videojuegoMapper.toResponse(videojuego)).thenReturn(response);

        VideojuegoResponse resultado = videojuegoService.obtenerVideojuegoPorId(id);

        assertEquals("FIFA", resultado.getNombre());
        verify(videojuegoRepository).findById(id);
    }

    @Test
    void testObtenerVideojuegoPorId_noExiste() {
        Long id = 999L;
        when(videojuegoRepository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
            videojuegoService.obtenerVideojuegoPorId(id);
        });

        assertEquals("404 NOT_FOUND \"Videojuego no encontrado con ID: 999\"", ex.getMessage());
    }

    @Test
    void testCrearVideojuego() {
        VideojuegoRequest request = new VideojuegoRequest("Halo", "Shooter", 9.2);
        Videojuego videojuego = new Videojuego();
        Videojuego videojuegoGuardado = new Videojuego();
        videojuegoGuardado.setId(1L);

        VideojuegoResponse response = new VideojuegoResponse(1L, "Halo", "Shooter", 9.2);

        when(videojuegoMapper.toEntity(request)).thenReturn(videojuego);
        when(videojuegoRepository.save(videojuego)).thenReturn(videojuegoGuardado);
        when(videojuegoMapper.toResponse(videojuegoGuardado)).thenReturn(response);

        VideojuegoResponse resultado = videojuegoService.crearVideojuego(request);

        assertEquals("Halo", resultado.getNombre());
        assertEquals(1L, resultado.getId());
    }

    @Test
    void testEliminarVideojuego_existente() {
        Long id = 1L;
        when(videojuegoRepository.existsById(id)).thenReturn(true);

        videojuegoService.eliminarVideojuego(id);

        verify(videojuegoRepository).deleteById(id);
    }

    @Test
    void testEliminarVideojuego_noExiste() {
        Long id = 123L;
        when(videojuegoRepository.existsById(id)).thenReturn(false);

        assertThrows(ResponseStatusException.class, () -> {
            videojuegoService.eliminarVideojuego(id);
        });
    }
}
