package mapper;

public interface IEntityMapper<E, D> {

    void dtoToEntity(D dto, E entity);

    void entityToDto(E entity, D dto);
}
