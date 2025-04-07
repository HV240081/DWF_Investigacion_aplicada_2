package sv.edu.udb.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideojuegoRequest {
    private String nombre;
    private String genero;
    private Double calificacion;
}
