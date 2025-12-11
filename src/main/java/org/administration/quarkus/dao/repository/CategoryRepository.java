package org.administration.quarkus.dao.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.administration.quarkus.common.enums.Status;
import org.administration.quarkus.dao.entity.Category;

import java.util.List;

@ApplicationScoped
public class CategoryRepository implements PanacheRepositoryBase<Category, Integer> {

    /**
     * Busca categorías por estado con paginación.
     *
     * @param status    El estado a filtrar (ACTIVO, INACTIVO, etc.)
     * @param pageIndex El número de página (empieza en 0)
     * @param pageSize  El tamaño de la página
     * @return Lista de categorías paginada
     */
    public List<Category> findByStatus(Status status, int pageIndex, int pageSize) {
        return find("status", status)
                .page(pageIndex, pageSize)
                .list();
    }
}
