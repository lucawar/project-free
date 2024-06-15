package mapper;

public interface IEntityMapper <E, D> {

    E dtoToEntity(D dto, E entity);
    D entityToDto(E entity, D dto);
}
