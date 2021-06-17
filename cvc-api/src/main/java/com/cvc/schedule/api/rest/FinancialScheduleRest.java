package com.cvc.schedule.api.rest;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cvc.schedule.api.dto.FilterFinancialScheduleDTO;
import com.cvc.schedule.api.dto.FinancialScheduleDTO;
import com.cvc.schedule.api.dto.QueryPageDTO;
import com.cvc.schedule.api.service.FinancialScheduleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/schedule-transfer")
public class FinancialScheduleRest {

	@Autowired
	private FinancialScheduleService financialScheduleService;
	
	@GetMapping("/filter")
	@Operation(summary = "Filter financial schedule.", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "financial schedule successfully returned.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = FinancialScheduleDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Not Found.")})
	public ResponseEntity<QueryPageDTO<FinancialScheduleDTO>> filterScheduleTransfer(final FilterFinancialScheduleDTO filter) {
		QueryPageDTO<FinancialScheduleDTO> page = financialScheduleService.filterScheduleTransfer(filter);
		
		if (page.getList() == null || page.getList().isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("/find/{id}")
	@Operation(summary = "Find financial schedule per id.", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Financial schedule successfully returned.", content = @Content(schema = @Schema(implementation = FinancialScheduleDTO.class))),
            @ApiResponse(responseCode = "404", description = "Not Found.")
     })
	public ResponseEntity<FinancialScheduleDTO> findId(@PathVariable final Long id){
		FinancialScheduleDTO financialScheduleDTO = financialScheduleService.findId(id);
		
		if (financialScheduleDTO == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(financialScheduleDTO);
	}
	
	@PostMapping("/create")
	@Operation(summary = "Create an financial schedule.", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Financial schedule created successfully.")})
	public ResponseEntity<FinancialScheduleDTO> createFinancialSchedule(@RequestBody final FinancialScheduleDTO financialScheduleDTO){
		FinancialScheduleDTO schedule = financialScheduleService.createFinancialSchedule(financialScheduleDTO);
		
		 URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				   .buildAndExpand(schedule.getId()).toUri();
		 
		 return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/update")
	@Operation(summary = "Change an financial schedule.", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Financial schedule successfully changed.")})
	public ResponseEntity<FinancialScheduleDTO> changeFinancialSchedule(@RequestBody final FinancialScheduleDTO financialScheduleDTO){
		financialScheduleService.createFinancialSchedule(financialScheduleDTO);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/delete/{id}")
	@Operation(summary = "Delete an financial schedule.", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Financial schedule successfully deleted.")})
	public ResponseEntity<FinancialScheduleDTO> excluirCliente(@PathVariable final Long id){
		FinancialScheduleDTO financialScheduleDTO = financialScheduleService.findId(id);
		
		if (financialScheduleDTO == null) {
			 return ResponseEntity.notFound().build();
		}
		
		financialScheduleService.deleteFinancialSchedule(financialScheduleDTO);
		return ResponseEntity.ok().build();
	}
}
