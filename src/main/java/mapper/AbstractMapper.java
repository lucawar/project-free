package mapper;


import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

public abstract class AbstractMapper<E, D> implements IEntityMapper<E, D> {

    protected void doCopy(Object dest, Object orig) {
        try {
            BeanUtils.copyProperties(dest, orig);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Copy properties failed", e);
        }
    }
}
