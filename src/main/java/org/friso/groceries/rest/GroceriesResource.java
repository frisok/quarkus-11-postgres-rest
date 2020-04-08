package org.friso.groceries.rest;

import org.friso.groceries.entity.Groceries;
import org.friso.groceries.service.GroceriesService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/groceries")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GroceriesResource {

    @Inject
    private GroceriesService groceriesService;

    @GET
    public Response allGroceries() {
        return Response.ok(groceriesService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response groceries(@PathParam("id") final Long id) {
        final Groceries result = groceriesService.findById(id);
        return result != null ? Response.ok(result).build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/latest")
    public Response findLatest() {
        final Groceries result = groceriesService.findLatest();
        return result != null ? Response.ok(result).build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * curl --header "Content-Type: application/json" --request POST --data '{"date":"01-02-2020"}' http://localhost:8080/groceries/edit
     */
    @POST
    @Path("/edit")
    public Response edit(final Groceries groceries) {
        final boolean result = groceriesService.createOrUpdate(groceries.getId(), groceries.getDate());
        return result ? Response.ok().build() : Response.notModified().build();
    }

    /**
     * curl --header "Content-Type: application/json" --request POST 'http://localhost:8080/groceries/additem?groceries=31&item=22'
     */
    @POST
    @Path("/additem")
    public Response addItem(@QueryParam("groceries") final Long GroceriesId, @QueryParam("item") final Long itemId) {
        final boolean result = groceriesService.addItem(GroceriesId, itemId);
        return result ? Response.ok().build() : Response.notModified().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") final Long id) {
        final boolean result = groceriesService.delete(id);
        return result ? Response.ok().build() : Response.notModified().build();
    }


}
