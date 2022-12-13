package com.secxndary.filiusmeretrixproject.controller;
import com.secxndary.filiusmeretrixproject.dto.TicketDto;
import com.secxndary.filiusmeretrixproject.dto.TicketOrderDto;
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


@Tag(name = "TicketOrder REST Controller", description = "The controller accepts requests from the user page")
@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
@Slf4j
public class TicketOrderController {

    private final TicketService ticketService;


    @Operation(
            summary = "Getting info about order tickets",
            description = "Allows you to get info about order tickets"
    )
    @GetMapping("/ticketOrder")
    public ResponseEntity<List<TicketDto>> getTickets() {
        return ResponseEntity.ok(ticketService.getOrderTickets());
    }


    @Operation(
            summary = "Saving info about order tickets",
            description = "Allows you to save info about order tickets"
    )
    @PostMapping("/ticketOrder")
    @ResponseStatus(value = HttpStatus.OK)
    public void save(@Valid @RequestBody TicketOrderDto ticketOrderDto) {
        ticketService.order(ticketOrderDto);
    }

}