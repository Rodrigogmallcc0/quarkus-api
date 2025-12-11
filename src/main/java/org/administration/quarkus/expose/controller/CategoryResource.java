package org.administration.quarkus.expose.controller;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.administration.quarkus.business.service.CategoryService;
import org.administration.quarkus.expose.dto.CategoryDTO;

import java.util.List;

@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResource {

    @Inject
    CategoryService categoryService;

    @GET
    public Response listCategories(
            @QueryParam("page") @DefaultValue("0") int pageIndex,
            @QueryParam("size") @DefaultValue("10") int pageSize) {
        List<CategoryDTO> categories = categoryService.listActiveCategories(pageIndex, pageSize);
        return Response.ok(categories).build();
    }

    @GET
    @Path("/{id}")
    public Response getCategory(@PathParam("id") Integer id) {
        CategoryDTO category = categoryService.findCategoryById(id);
        return Response.ok(category).build();
    }

    @POST
    public Response createCategory(@Valid CategoryDTO categoryDTO) {
        CategoryDTO createdCategory = categoryService.createCategory(categoryDTO);
        return Response.status(Response.Status.CREATED).entity(createdCategory).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCategory(@PathParam("id") Integer id, @Valid CategoryDTO categoryDTO) {
        CategoryDTO updatedCategory = categoryService.updateCategory(id, categoryDTO);
        return Response.ok(updatedCategory).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCategory(@PathParam("id") Integer id) {
        // Borrado lógico: enable = false
        categoryService.deleteCategoryLogically(id, false);
        return Response.noContent().build();
    }

    @PATCH
    @Path("/{id}/reactivate")
    public Response reactivateCategory(@PathParam("id") Integer id) {
        // Reactivación: enable = true
        categoryService.deleteCategoryLogically(id, true);
        return Response.ok().build();
    }
}
