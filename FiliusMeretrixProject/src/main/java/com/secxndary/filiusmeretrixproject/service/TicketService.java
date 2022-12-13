package com.secxndary.filiusmeretrixproject.service;
import com.secxndary.filiusmeretrixproject.dto.TicketDto;
import com.secxndary.filiusmeretrixproject.dto.TicketOrderDto;
import com.secxndary.filiusmeretrixproject.entity.Ticket;
import com.secxndary.filiusmeretrixproject.mapper.PlayMapper;
import com.secxndary.filiusmeretrixproject.mapper.TicketMapper;
import com.secxndary.filiusmeretrixproject.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.secxndary.filiusmeretrixproject.dto.ConcertDto;
import com.secxndary.filiusmeretrixproject.entity.Concert;
import com.secxndary.filiusmeretrixproject.entity.User;
import com.secxndary.filiusmeretrixproject.repository.ConcertRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final ConcertRepository playRepository;
    private final UserService userService;

    public List<TicketDto> getOrderTickets() {
        return getAllTickets().stream()
                .filter(ticketDto -> ticketDto.getAvailability().equals(true)).collect(Collectors.toList());
    }

    public List<TicketDto> getAllTickets() {
        List<TicketDto> ticketDtos = new ArrayList<>();
        List<Ticket> tickets = ticketRepository.findAll();
        for (Ticket ticket : tickets) {
            TicketDto ticketDto = TicketMapper.INSTANCE.toDTO(ticket);
            ConcertDto playDto = PlayMapper.INSTANCE.toDTO(ticket.getConcert());
            ticketDto.setConcertTitle(playDto.getTitle());
            ticketDtos.add(ticketDto);
        }
        return ticketDtos;
    }

    @Transactional
    public Ticket save(TicketDto ticketDto) throws Exception {
        List<Ticket> tickets = new ArrayList<>();
        User user = userService.findByUsername("root");

        Ticket ticket = TicketMapper.INSTANCE.fromDTO(ticketDto);
        String playName = ticketDto.getConcertTitle();
        Concert play = playRepository.findByTitle(playName);
        if (play == null) {
            throw new Exception("Play name doesn't exist");
        }
        ticket.setUser(user);
        ticket.setConcert(play);
        tickets.add(ticket);
        log.info("Created ticket {}", ticket);
        return ticketRepository.save(ticket);
    }

    @Transactional
    public void delete(Long ticketId) {
        Ticket ticket = ticketRepository.getById(ticketId);
        ticketRepository.deleteById(ticket.getId());
        log.info("Delete completed {}", ticket);
    }

    public Ticket order(TicketOrderDto ticketOrderDto) {
        

        String username = ticketOrderDto.getUserName();
        User user = userService.findByUsername(username);
        Concert play = playRepository.findByTitle(ticketOrderDto.getConcertTitle());
        Ticket ticket = ticketRepository.findByPlaceAndRowAndAndConcert(ticketOrderDto.getPlace(), ticketOrderDto.getRow(), play);
        ticket.setUser(user);
        ticket.setAvailability(false);
        return ticketRepository.save(ticket);
    }
}
