package sv.edu.udb.controller.request;

import lombok.Data;

@Data
public class VideojuegoRequest {
    private String nombre;
    private String genero;
    private Double calificacion;
}