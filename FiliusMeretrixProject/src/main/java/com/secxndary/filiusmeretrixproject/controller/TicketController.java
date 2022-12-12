package com.secxndary.filiusmeretrixproject.controller;
import com.secxndary.filiusmeretrixproject.dto.OrderDto;
import com.secxndary.filiusmeretrixproject.dto.TicketDto;
import com.secxndary.filiusmeretrixproject.entity.Order;
import com.secxndary.filiusmeretrixproject.entity.Ticket;
import com.secxndary.filiusmeretrixproject.mapper.OrderMapper;
import com.secxndary.filiusmeretrixproject.mapper.TicketMapper;
import com.secxndary.filiusmeretrixproject.service.OrderService;
import com.secxndary.filiusmeretrixproject.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Tickets REST Controller", description = "The controller accepts requests from the admin page")
@RestController
@RequestMapping(value = "/api/admin")
@RequiredArgsConstructor
@Slf4j
public class TicketController {

    private final TicketService ticketService;


    @Operation(
            summary = "Getting info about tickets",
            description = "Allows you to get info about tickets"
    )
    @GetMapping("/tickets")
    public ResponseEntity<List<TicketDto>> getTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }


    @Operation(
            summary = "Saving info about tickets",
            description = "Allows you to save info about tickets"
    )
    @PostMapping("/tickets")
    public ResponseEntity<TicketDto> save(@Valid @RequestBody TicketDto ticketDto) throws Exception {
        Ticket ticket = ticketService.save(ticketDto);
        return ResponseEntity.ok(TicketMapper.INSTANCE.toDTO(ticket));
    }


    @Operation(
            summary = "Deleting info about tickets",
            description = "Allows you to delete info about tickets"
    )
    @DeleteMapping("/tickets/{ticketId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable(name = "ticketId") Long ticketId) {
        ticketService.delete(ticketId);
    }
}
