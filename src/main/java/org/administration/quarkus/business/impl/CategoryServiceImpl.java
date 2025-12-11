package org.administration.quarkus.business.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.administration.quarkus.business.service.CategoryService;
import org.administration.quarkus.common.enums.Status;
import org.administration.quarkus.common.exception.ModelNotFoundException;
import org.administration.quarkus.dao.entity.Category;
import org.administration.quarkus.dao.repository.CategoryRepository;
import org.administration.quarkus.expose.dto.CategoryDTO;
import org.administration.quarkus.mapper.CategoryMapper;

import java.util.List;

@ApplicationScoped
public class CategoryServiceImpl implements CategoryService {

    @Inject
    CategoryRepository categoryRepository;

    @Inject
    CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> listActiveCategories(int pageIndex, int pageSize) {
        List<Category> categories = categoryRepository.findByStatus(Status.ACTIVE, pageIndex, pageSize);
        return categoryMapper.toDtoList(categories);
    }

    @Override
    public CategoryDTO findCategoryById(Integer id) {
        Category category = categoryRepository.findById(id);
        if (category == null) {
            throw new ModelNotFoundException("Category with id " + id + " not found");
        }
        return categoryMapper.toDto(category);
    }

    @Override
    @Transactional
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.toEntity(categoryDTO);
        category.setStatus(Status.ACTIVE);
        categoryRepository.persist(category);
        return categoryMapper.toDto(category);
    }

    @Override
    @Transactional
    public CategoryDTO updateCategory(Integer id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id);
        if (category == null) {
            throw new ModelNotFoundException("Category with id " + id + " not found");
        }
        category.setCategoryName(categoryDTO.getCategoryName());
        categoryRepository.persist(category);
        return categoryMapper.toDto(category);
    }

    @Override
    @Transactional
    public void deleteCategoryLogically(Integer id, boolean enable) {
        Category category = categoryRepository.findById(id);
        if (category == null) {
            throw new ModelNotFoundException("Category with id " + id + " not found");
        }
        Status newStatus = enable ? Status.ACTIVE : Status.INACTIVE;
        category.setStatus(newStatus);
        categoryRepository.persist(category);
    }
}
