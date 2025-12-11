package org.administration.quarkus.mapper;

import org.administration.quarkus.dao.entity.Category;
import org.administration.quarkus.expose.dto.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "status", target = "status")
    CategoryDTO toDto(Category category);

    @Mapping(target = "status", ignore = true) // Ignoramos el status al convertir de DTO a entidad
    Category toEntity(CategoryDTO categoryDTO);

    List<CategoryDTO> toDtoList(List<Category> categories);
}
