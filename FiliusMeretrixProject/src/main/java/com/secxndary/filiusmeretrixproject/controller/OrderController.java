package com.secxndary.filiusmeretrixproject.controller;
import com.secxndary.filiusmeretrixproject.dto.OrderDto;
import com.secxndary.filiusmeretrixproject.entity.Order;
import com.secxndary.filiusmeretrixproject.mapper.OrderMapper;
import com.secxndary.filiusmeretrixproject.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Orders REST Controller", description = "The controller accepts requests from the admin page")
@RestController
@RequestMapping(value = "/api/admin")
@RequiredArgsConstructor
@Slf4j
public class OrderController {


    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Operation(
            summary = "Getting info about orders",
            description = "Allows you to get info about orders"
    )
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @Operation(
            summary = "Saving info about orders",
            description = "Allows you to save info about orders"
    )
    @PostMapping("/orders")
    public ResponseEntity<OrderDto> save(@Valid @RequestBody OrderDto orderDto) throws Exception {
        Order order = orderService.save(orderDto);
        return ResponseEntity.ok(orderMapper.toDTO(order));
    }

    @Operation(
            summary = "Deleting info about orders",
            description = "Allows you to delete info about orders"
    )
    @DeleteMapping("/orders/{orderId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable(name = "orderId") Long orderId) {
        orderService.delete(orderId);
    }
}
