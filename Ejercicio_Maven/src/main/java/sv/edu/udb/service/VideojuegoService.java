package sv.edu.udb.service;

import sv.edu.udb.controller.request.VideojuegoRequest;
import sv.edu.udb.controller.response.VideojuegoResponse;
import sv.edu.udb.repository.domain.Videojuego;
import sv.edu.udb.repository.VideojuegoRepository;
import sv.edu.udb.service.mapper.VideojuegoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VideojuegoService {

    private final VideojuegoRepository videojuegoRepository;
    private final VideojuegoMapper videojuegoMapper;

    public VideojuegoService(VideojuegoRepository videojuegoRepository, VideojuegoMapper videojuegoMapper) {
        this.videojuegoRepository = videojuegoRepository;
        this.videojuegoMapper = videojuegoMapper;
    }

    public List<VideojuegoResponse> obtenerTodosLosVideojuegos() {
        List<Videojuego> videojuegos = videojuegoRepository.findAll();
        return videojuegoMapper.toResponseList(videojuegos);
    }

    public VideojuegoResponse obtenerVideojuegoPorId(Long id) {
        Videojuego videojuego = videojuegoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Videojuego no encontrado con ID: " + id));
        return videojuegoMapper.toResponse(videojuego);
    }

    public VideojuegoResponse crearVideojuego(VideojuegoRequest request) {
        Videojuego videojuego = videojuegoMapper.toEntity(request);
        Videojuego videojuegoGuardado = videojuegoRepository.save(videojuego);
        return videojuegoMapper.toResponse(videojuegoGuardado);
    }

    public VideojuegoResponse actualizarVideojuego(Long id, VideojuegoRequest request) {
        Videojuego videojuegoExistente = videojuegoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Videojuego no encontrado con ID: " + id));

        videojuegoMapper.toEntity(request, videojuegoExistente);
        Videojuego videojuegoActualizado = videojuegoRepository.save(videojuegoExistente);
        return videojuegoMapper.toResponse(videojuegoActualizado);
    }

    public void eliminarVideojuego(Long id) {
        if (!videojuegoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Videojuego no encontrado con ID: " + id);
        }
        videojuegoRepository.deleteById(id);
    }
}