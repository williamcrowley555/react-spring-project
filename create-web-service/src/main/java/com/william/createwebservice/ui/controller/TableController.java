package com.william.createwebservice.ui.controller;

import com.william.createwebservice.service.TableService;
import com.william.createwebservice.shared.dto.BookingDTO;
import com.william.createwebservice.shared.dto.TableDTO;
import com.william.createwebservice.ui.model.response.BookingResponse;
import com.william.createwebservice.ui.model.response.TableResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/table")
public class TableController {
    @Autowired
    private TableService tableService;

    @Operation(summary = "Get a table by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the table",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Table not found",
                    content = @Content) })
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getTableById(@PathVariable Long id) {
        ModelMapper modelMapper = new ModelMapper();
        TableDTO tableDTO = tableService.getTableById(id);
        TableResponse returnValue = modelMapper.map(tableDTO, TableResponse.class);
        return ResponseEntity.ok(returnValue);
    }

}
