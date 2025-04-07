package sv.edu.udb.service.mapper;

import sv.edu.udb.controller.request.VideojuegoRequest;
import sv.edu.udb.controller.response.VideojuegoResponse;
import sv.edu.udb.repository.domain.Videojuego;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VideojuegoMapper {

    VideojuegoResponse toResponse(Videojuego videojuego);

    List<VideojuegoResponse> toResponseList(List<Videojuego> videojuegos);

    Videojuego toEntity(VideojuegoRequest videojuegoRequest);

    void toEntity(VideojuegoRequest videojuegoRequest, @MappingTarget Videojuego videojuego);
}