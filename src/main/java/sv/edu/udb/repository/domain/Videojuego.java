package sv.edu.udb.repository.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "videojuegos")
@Data
public class Videojuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private Double calificacion;
}