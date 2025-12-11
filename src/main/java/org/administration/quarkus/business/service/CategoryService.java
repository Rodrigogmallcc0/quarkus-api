package org.administration.quarkus.business.service;

import org.administration.quarkus.expose.dto.CategoryDTO;
import java.util.List;

public interface CategoryService {

    List<CategoryDTO> listActiveCategories(int pageIndex, int pageSize);

    CategoryDTO findCategoryById(Integer id);

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(Integer id, CategoryDTO categoryDTO);

    void deleteCategoryLogically(Integer id, boolean enable);
}
