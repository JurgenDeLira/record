package org.ms.record.api;


import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.ms.record.dto.RecordRequest;
import org.ms.record.service.RecordService;

@Path("/customers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RecordResource {
    @Inject
    RecordService recordService;

    @GET
    public Uni<Response> listRecords(){
        return recordService.listAllRecords()
                .onItem().transform(result -> {
                    if (result.isSuccess()){
                        return Response.ok(result.getValue()).build();
                    } else {
                        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(result.getError()).build();
                    }
                });
    }

    @GET
    @Path("/{id}")
    public Uni<Response> getRecord(@PathParam("id")Long id) {
        return recordService.findRecordById(id)
                .onItem().transform(result -> {
                    if (result.isSuccess()) {
                        return Response.ok(result.getValue()).build();
                    } else {
                        return Response.status(Response.Status.NOT_FOUND)
                                .entity(result.getError()).build();
                    }
                });
    }

    @POST
    public Uni<Response> createRecord(RecordRequest record) {
        return recordService.createRecord(record)
                .onItem().transform(result -> {
                    if (result.isSuccess()) {
                        return Response.status(Response.Status.CREATED)
                                .entity(result.getValue()).build();
                    } else {
                        return Response.status(Response.Status.BAD_REQUEST)
                                .entity(result.getError()).build();
                    }
                });
    }

    @PUT
    @Path("/{id}")
    public Uni<Response> updateRecord(@PathParam("id") Long id, Record updatedRecord){
        return recordService.findRecordById(id)
                .onItem().transformToUni(result -> {
                    if (result.isSuccess() && result.getValue() != null) {
                        Record existing = result.getValue();
                        existing.setName(updatedRecord.getName());
                        existing.setArtist(updatedRecord.getArtist());
                        existing.setYear(updatedRecord.getYear());
                        existing.setGenre(updatedRecord.getGenre());
                        existing.setFormat(updatedRecord.getFormat());
                        return recordService.createRecord(existing)
                                .onItem().transform(updatedResult -> {
                                    if (updatedResult.isSuccess()) {
                                        return Response.ok(updatedResult.getValue()).build();
                                    } else {
                                        return Response.status(Response.Status.BAD_REQUEST)
                                                .entity(updatedResult.getError()).build();
                                    }
                                });
                    } else {
                        return Uni.createFrom().item(Response.status(Response.Status.NOT_FOUND)
                                .entity(result.getError()).build());
                    }
                });
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> deleteRecord(@PathParam("id") Long id) {
        return recordService.deleteRecord(id)
                .onItem().transform(result -> {
                    if (result.isSuccess()) {
                        return Response.noContent().build();
                    } else {
                        return Response.status(Response.Status.BAD_REQUEST)
                                .entity(result.getError()).build();
                    }
                });
    }
}



















