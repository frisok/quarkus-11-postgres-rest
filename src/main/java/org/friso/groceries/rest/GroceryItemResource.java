package org.friso.groceries.rest;

import org.friso.groceries.entity.GroceryItem;
import org.friso.groceries.service.GroceryItemService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

@Path("/groceries")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GroceryItemResource {

    @Inject
    private GroceryItemService groceryItemService;

    @GET
    @Path("/items")
    public Response groceryItems() {
        return Response.ok(groceryItemService.findAll()).build();
    }

    @GET
    @Path("/item/{id}")
    public Response groceryItem(@PathParam("id") Long id) {
        final GroceryItem result = groceryItemService.findById(id);
        return result != null ? Response.ok(result).build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * curl --header "Content-Type: application/json" --request POST --data '{"name":"peer","description":"conference","barcode":"3456"}' http://localhost:8080/groceries/item/edit
     */
    @POST
    @Path("/item/edit")
    public Response createOrUpdateGroceryItem(final GroceryItem groceryItem) {
        final boolean result = groceryItemService.createOrUpdate(groceryItem.getId(), groceryItem.getName(), groceryItem.getDescription(), groceryItem.getBarcode());
        return result ? Response.ok().build() : Response.notModified().build();
    }

    /**
     * curl --header "Content-Type: application/json" --request DELETE  http://localhost:8080/groceries/item/20
     */
    @DELETE
    @Path("/item/{id}")
    public Response deleteGroceryItem(@PathParam("id") Long id) {
        final boolean ok = groceryItemService.delete(id);
        return ok ? Response.ok(ok).build() : Response.notModified().build();
    }
/*
   @POST
    @Path("/item/edit")
    @Consumes(MediaType.APPLICATION_JSON)
    public GroceryItem createOrUpdateGroceryItem(
            @FormParam("id") Long id,
            @FormParam("name") String name,
            @FormParam("description") @DefaultValue("") String description,
            @FormParam("barcode") @DefaultValue("") String barcode) {
        return groceryItemService.createOrUpdate(id, name, description, barcode);
    }
*/

}