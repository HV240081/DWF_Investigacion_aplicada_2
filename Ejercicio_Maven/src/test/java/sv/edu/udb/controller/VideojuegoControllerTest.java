package sv.edu.udb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sv.edu.udb.controller.request.VideojuegoRequest;
import sv.edu.udb.controller.response.VideojuegoResponse;
import sv.edu.udb.service.VideojuegoService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VideojuegoController.class)
public class VideojuegoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideojuegoService videojuegoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testObtenerTodosLosVideojuegos() throws Exception {
        List<VideojuegoResponse> lista = Arrays.asList(
                new VideojuegoResponse(1L, "Zelda", "Aventura", 9.5),
                new VideojuegoResponse(2L, "Mario", "Plataforma", 8.7)
        );

        Mockito.when(videojuegoService.obtenerTodosLosVideojuegos()).thenReturn(lista);

        mockMvc.perform(get("/api/videojuegos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].nombre").value("Zelda"));
    }

    @Test
    public void testCrearVideojuego() throws Exception {
        VideojuegoRequest request = new VideojuegoRequest("Halo", "Shooter", 9.0);
        VideojuegoResponse response = new VideojuegoResponse(3L, "Halo", "Shooter", 9.0);

        Mockito.when(videojuegoService.crearVideojuego(any(VideojuegoRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/videojuegos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.nombre").value("Halo"));
    }

    @Test
    public void testActualizarVideojuego() throws Exception {
        VideojuegoRequest request = new VideojuegoRequest("Halo 2", "Shooter", 9.2);
        VideojuegoResponse response = new VideojuegoResponse(3L, "Halo 2", "Shooter", 9.2);

        Mockito.when(videojuegoService.actualizarVideojuego(eq(3L), any(VideojuegoRequest.class))).thenReturn(response);

        mockMvc.perform(put("/api/videojuegos/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Halo 2"));
    }

    @Test
    public void testEliminarVideojuego() throws Exception {
        Mockito.doNothing().when(videojuegoService).eliminarVideojuego(4L);

        mockMvc.perform(delete("/api/videojuegos/4"))
                .andExpect(status().isNoContent());
    }
}
