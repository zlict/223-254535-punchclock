package ch.zli.m223.controller;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;

import ch.zli.m223.model.Tag;
import ch.zli.m223.service.TagService;

@Path("/tags")
@org.eclipse.microprofile.openapi.annotations.tags.Tag(name = "Tags", description = "Handling of tags")
public class TagController {

    @Inject
    TagService tagService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all Tags.", description = "Returns a list of all tags.")
    public List<Tag> index() {
        return tagService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new tag.", description = "Creates a new tag and returns the newly added tag.")
    public Tag create(Tag tag) {
        return tagService.createTag(tag);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Tag update(@PathParam("id") Long id, Tag tag) {
        return tagService.updateTag(id, tag);
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Deletes an tag.", description = "Deletes an tag and returns nothing if successfull.")
    public void delete(@PathParam("id") Long id) {
        tagService.deleteTag(id);
    }

}
