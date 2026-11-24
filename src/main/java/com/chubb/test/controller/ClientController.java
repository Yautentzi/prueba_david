package com.chubb.test.controller;

import com.chubb.test.model.dtos.ClientDto;
import com.chubb.test.model.dtos.ServiceResponse;
import com.chubb.test.service.IClientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = { "http://localhost:8080" })
@RequestMapping("/api")
@Tag (name = "Client", description = "Endpoints to manage the clients")
public class ClientController {

	@Autowired
	private IClientService clientService;
	
	@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/clients")
	@Operation (summary = "List all Clients", description = "Returns every client in the DataBase.")
	@ApiResponses ({
		@ApiResponse(responseCode = "200", description = "List of Clients. Or empty list.", content = @Content),
		@ApiResponse(responseCode = "500", description = "Server error", content = @Content)
	})
    public ResponseEntity<List<ClientDto>> getClients() {
    	return ResponseEntity.ok(clientService.findAll());
    }

	@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/client")
	@Operation (summary = "Create a Client", description = "Receive the data client and save it in DataBase")
	@ApiResponses ({
		@ApiResponse(responseCode = "200", description = "Client saved successfully", content = @Content),
		@ApiResponse(responseCode = "500", description = "Server error", content = @Content)
	})
    public ResponseEntity<ServiceResponse> saveClient(@RequestBody ClientDto client) {
    	ServiceResponse serviceReponse = new ServiceResponse();
    	int result = clientService.save(client);

    	if (result == 1) {
    		serviceReponse.setSuccess(true);
	    	serviceReponse.setMessage("Client saved with sucesss");
    	}
    	
    	return new ResponseEntity<>(serviceReponse, HttpStatus.OK);
    }

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/clients/{id}")
	@Operation (summary = "Delete a Client", description = "Receive the id client and remove it from DataBase")
	@ApiResponses ({
		@ApiResponse(responseCode = "204", description = "Client deleted successfully", content = @Content),
		@ApiResponse(responseCode = "404", description = "Client not found by id received", content = @Content),
		@ApiResponse(responseCode = "500", description = "Server error", content = @Content)
	})
	public ResponseEntity<Void> deleteClient(@PathVariable int id) {
		if (clientService.deleteById(id)) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}
	
}
