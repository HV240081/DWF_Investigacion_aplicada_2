package sv.edu.udb.controller.response;
import lombok.Data;

@Data
public class VideojuegoResponse {
    private Long id;
    private String nombre;
    private String genero;
    private Double calificacion;
}