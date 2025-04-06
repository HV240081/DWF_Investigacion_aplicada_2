package sv.edu.udb.controller;

import sv.edu.udb.controller.request.VideojuegoRequest;
import sv.edu.udb.controller.response.VideojuegoResponse;
import sv.edu.udb.service.VideojuegoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videojuegos")
public class VideojuegoController {

    private final VideojuegoService videojuegoService;

    public VideojuegoController(VideojuegoService videojuegoService) {
        this.videojuegoService = videojuegoService;
    }

    @GetMapping
    public ResponseEntity<List<VideojuegoResponse>> obtenerTodosLosVideojuegos() {
        return ResponseEntity.ok(videojuegoService.obtenerTodosLosVideojuegos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideojuegoResponse> obtenerVideojuegoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(videojuegoService.obtenerVideojuegoPorId(id));
    }

    @PostMapping
    public ResponseEntity<VideojuegoResponse> crearVideojuego(@RequestBody VideojuegoRequest request) {
        VideojuegoResponse nuevoVideojuego = videojuegoService.crearVideojuego(request);
        return new ResponseEntity<>(nuevoVideojuego, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideojuegoResponse> actualizarVideojuego(@PathVariable Long id, @RequestBody VideojuegoRequest request) {
        VideojuegoResponse videojuegoActualizado = videojuegoService.actualizarVideojuego(id, request);
        return ResponseEntity.ok(videojuegoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVideojuego(@PathVariable Long id) {
        videojuegoService.eliminarVideojuego(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}