package com.secxndary.filiusmeretrixproject.service;
import com.secxndary.filiusmeretrixproject.dto.ConcertDto;
import com.secxndary.filiusmeretrixproject.entity.Concert;
import com.secxndary.filiusmeretrixproject.mapper.ConcertMapper;
import com.secxndary.filiusmeretrixproject.repository.ConcertRepository;
import com.secxndary.filiusmeretrixproject.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class ConcertService {

    private final ConcertRepository concertRepository;
    private final OrderRepository orderRepository;
    private final ConcertMapper concertMapper;


    public List<ConcertDto> getAllConcerts() {
        List<ConcertDto> concertDtos = new ArrayList<>();
        List<Concert> concerts = concertRepository.findAll();
        for (Concert concert : concerts) {
            ConcertDto concertDto = concertMapper.toDTO(concert);
            concertDtos.add(concertDto);
        }
        return concertDtos;
    }


    public List<ConcertDto> searchConcert(String name) {
        List<Concert> allConcerts = new ArrayList<>();
        List<ConcertDto> concertDtos = new ArrayList<>();
        allConcerts = concertRepository.findAll()
                .stream()
                .filter(concert -> concert.getTitle().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        for (Concert concert : allConcerts) {
            ConcertDto concertDto = concertMapper.toDTO(concert);
            concertDtos.add(concertDto);
        }
        return concertDtos;
    }


    public List<ConcertDto> filterByTitle() {
        List<ConcertDto> concertDtos = getAllConcerts();
        concertDtos.sort(Comparator.comparing(ConcertDto::getTitle));
        return concertDtos;
    }


    public List<ConcertDto> filterByCapacity() {
        List<ConcertDto> concertDtos = getAllConcerts();
        concertDtos.sort(Comparator.comparing(ConcertDto::getCapacity));
        return concertDtos;
    }


    public List<ConcertDto> filterByDate() {
        List<ConcertDto> concertDtos = getAllConcerts();
        concertDtos.sort(Comparator.comparing(ConcertDto::getDate));
        return concertDtos;
    }


    public List<ConcertDto> filterByAddress() {
        List<ConcertDto> concertDtos = getAllConcerts();
        concertDtos.sort(Comparator.comparing(ConcertDto::getAddress));
        return concertDtos;
    }


    public List<ConcertDto> filterByArtist() {
        List<ConcertDto> concertDtos = getAllConcerts();
        concertDtos.sort(Comparator.comparing(ConcertDto::getArtist));
        return concertDtos;
    }


    @Transactional
    public Concert save(ConcertDto concertDto) throws Exception {
        final Concert concert = concertMapper.fromDTO(concertDto);
        Concert findConcert = concertRepository.findByTitle(concert.getTitle());
        if (findConcert == null) {
            log.info("Created concert {}", concert);
            return concertRepository.save(concert);
        }
        throw new Exception("Concert names cannot be repeated");
    }

    @Transactional
    public Concert update(ConcertDto concertDto, String name) {
        Concert concert = concertRepository.findByTitle(name);
        Concert changedConcert = concertMapper.fromDTO(concertDto);
        concert.setTitle(changedConcert.getTitle());
        concert.setAddress(changedConcert.getAddress());
        concert.setDate(changedConcert.getDate());
        concert.setArtist(changedConcert.getArtist());
        concert.setCapacity(changedConcert.getCapacity());
        log.info("Update concert {}", concert);
        return concertRepository.save(concert);
    }


    @Transactional
    public void delete(String name) {
        Concert concert = concertRepository.findByTitle(name);
        orderRepository.deleteByConcert(concert);
        concertRepository.deleteById(concert.getTitle());
        log.info("Delete completed {}", concert);
    }
}