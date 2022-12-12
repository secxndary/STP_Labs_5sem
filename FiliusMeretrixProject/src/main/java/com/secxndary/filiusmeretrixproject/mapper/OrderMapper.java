package com.secxndary.filiusmeretrixproject.mapper;
import com.secxndary.filiusmeretrixproject.dto.OrderDto;
import com.secxndary.filiusmeretrixproject.entity.Concert;
import com.secxndary.filiusmeretrixproject.entity.Order;
import com.secxndary.filiusmeretrixproject.repository.ConcertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final ConcertRepository concertRepository;

    public OrderDto toDTO(Order order) {
        return new OrderDto(order.getId(), order.getIs_active(), new Concert(), order.getUser());
    }

    public Order fromDTO(OrderDto orderDto) {
        return new Order(orderDto.getId(), orderDto.getIs_active(), new Concert(), orderDto.getUser());
    }
}
