package mapper;

public interface IEntityMapper<E, D> {

    void dtoToEntity(E entity,D dto);

    void entityToDto( D dto, E entity);
}
