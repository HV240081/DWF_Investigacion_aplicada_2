package sv.edu.udb.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideojuegoResponse {
    private Long id;
    private String nombre;
    private String genero;
    private Double calificacion;
}
