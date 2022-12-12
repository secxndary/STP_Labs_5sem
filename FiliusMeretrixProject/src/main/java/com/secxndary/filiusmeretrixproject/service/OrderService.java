package com.secxndary.filiusmeretrixproject.service;
import com.secxndary.filiusmeretrixproject.dto.ConcertDto;
import com.secxndary.filiusmeretrixproject.dto.OrderDto;
import com.secxndary.filiusmeretrixproject.entity.Concert;
import com.secxndary.filiusmeretrixproject.entity.Order;
import com.secxndary.filiusmeretrixproject.entity.User;
import com.secxndary.filiusmeretrixproject.mapper.ConcertMapper;
import com.secxndary.filiusmeretrixproject.mapper.OrderMapper;
import com.secxndary.filiusmeretrixproject.repository.ConcertRepository;
import com.secxndary.filiusmeretrixproject.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ConcertRepository concertRepository;
    private final UserService userService;
    private final ConcertMapper concertMapper;
    private final OrderMapper orderMapper;


    public List<OrderDto> getOrderOrders() {
        return getAllOrders().stream()
                .filter(orderDto -> orderDto.getIs_active().equals(true)).collect(Collectors.toList());
    }

    public List<OrderDto> getAllOrders() {
        List<OrderDto> orderDtos = new ArrayList<>();
        List<Order> orders = orderRepository.findAll();
        for (Order order : orders) {
            OrderDto orderDto = orderMapper.toDTO(order);
//            ConcertDto concertDto = concertMapper.toDTO(order.getConcert());
//            orderDto.setConcertTitle(order.getConcert().getTitle());
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }

    @Transactional
    public Order save(OrderDto orderDto) throws Exception {
        List<Order> orders = new ArrayList<>();
        User user = userService.findByUsername("admin");

        Order order = orderMapper.fromDTO(orderDto);
        Concert concertName = orderDto.getConcert();
        Concert concert = concertRepository.findByTitle(concertName.getTitle());
        if (concert == null) {
            throw new Exception("Play name doesn't exist");
        }
        order.setUser(user);
        order.setConcert(concert);
        orders.add(order);
        log.info("Created order {}", order);
        return orderRepository.save(order);
    }

    @Transactional
    public void delete(Long orderId) {
        Order order = orderRepository.getById(orderId);
        orderRepository.deleteById(order.getId());
        log.info("Delete completed {}", order);
    }

//    public Order order(OrderOrderDto orderOrderDto) {
//        String username = orderOrderDto.getUsername();
//        User user = userService.findByUsername(username);
//        Play play = concertRepository.findByName(orderOrderDto.getPlayName());
//        Order order = orderRepository.findByPlaceAndRowAndAndPlay(orderOrderDto.getPlace(), orderOrderDto.getRow(), play);
//        order.setUser(user);
//        order.setAvailability(false);
//        return orderRepository.save(order);
//    }
}
