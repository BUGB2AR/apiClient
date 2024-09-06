package com.providertechapi.cliente.core.api.swagger;

import com.providertechapi.cliente.core.request.ClientRequest;
import com.providertechapi.cliente.core.response.ClientResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;

public interface ApiSwagger {

    @Operation(summary = "Create client", description = "Create a new client in the application")
    @ApiResponse(responseCode = "200", description = "Successfully created client")
    ResponseEntity<ClientResponse> add(
            @Parameter(description = "Client request body") @RequestBody ClientRequest requestBody);

    @Operation(summary = "Get all clients", description = "Retrieve a list of all clients.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of clients")
    ResponseEntity<List<ClientResponse>> findAll();

    @Operation(summary = "Find client by ID", description = "Retrieve a client by their ID.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved client")
    @ApiResponse(responseCode = "404", description = "Client not found")
    ResponseEntity<ClientResponse> findById(
            @Parameter(description = "ID of the client to be retrieved") @PathVariable Long id);

    @Operation(summary = "Delete client by ID", description = "Deletes a client by their ID.")
    @ApiResponse(responseCode = "204", description = "Successfully deleted client")
    @ApiResponse(responseCode = "404", description = "Client not found")
    void delete(
            @Parameter(description = "ID of the client to be deleted") @PathVariable Long id);

    @Operation(summary = "Update client by ID", description = "Updates an existing client entry by their ID.")
    @ApiResponse(responseCode = "200", description = "Successfully updated client")
    @ApiResponse(responseCode = "404", description = "Client not found")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    ResponseEntity<ClientResponse> update(
            @Parameter(description = "ID of the client to be updated") @PathVariable Long id,
            @Parameter(description = "Client request body with updated data") @RequestBody ClientRequest requestBody);

    @Operation(summary = "Find client by name", description = "Find a client by their name.")
    @ApiResponse(responseCode = "204", description = "Successfully find name client")
    @ApiResponse(responseCode = "404", description = "Client not found")
    ResponseEntity<List<ClientResponse>> findByFilter(String filter);
}