package com.secxndary.filiusmeretrixproject.controller;
import com.secxndary.filiusmeretrixproject.dto.ConcertDto;
import com.secxndary.filiusmeretrixproject.entity.Concert;
import com.secxndary.filiusmeretrixproject.service.ConcertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Tag(name = "Concert REST Controller", description = "The controller accepts requests from the admin page")
@RestController
@RequestMapping(value = "/api/admin")
@RequiredArgsConstructor
@Slf4j
public class ConcertController {

    private final ConcertService concertService;


    @Operation(
            summary = "Getting info about concerts",
            description = "Allows you to get info about concerts"
    )
    @GetMapping("/concerts")
    public ResponseEntity<List<ConcertDto>> getConcerts() {
        return ResponseEntity.ok(concertService.getAllConcerts());
    }


    @Operation(
            summary = "Searching info about concerts",
            description = "Allows you to search info about concerts"
    )
    @GetMapping("/concerts/{name}")
    public ResponseEntity<List<ConcertDto>> searchConcert(@PathVariable(name = "name") String name) {
        return ResponseEntity.ok(concertService.searchConcert(name));
    }


    @GetMapping("/filterByCapacity")
    public ResponseEntity<List<ConcertDto>> filterByCapacity() {
        return ResponseEntity.ok(concertService.filterByCapacity());
    }


    @GetMapping("/filterByTitle")
    public ResponseEntity<List<ConcertDto>> filterByTitle() {
        return ResponseEntity.ok(concertService.filterByTitle());
    }


    @GetMapping("/filterByArtist")
    public ResponseEntity<List<ConcertDto>> filterByArtist() {
        return ResponseEntity.ok(concertService.filterByArtist());
    }


    @GetMapping("/filterByAddress")
    public ResponseEntity<List<ConcertDto>> filterByAddress() {
        return ResponseEntity.ok(concertService.filterByAddress());
    }


    @GetMapping("/filterByDate")
    public ResponseEntity<List<ConcertDto>> filterByDate() {
        return ResponseEntity.ok(concertService.filterByDate());
    }


    @Operation(
            summary = "Saving info about concerts",
            description = "Allows you to save info about concerts"
    )
    @PostMapping("/concerts")
    public ResponseEntity<ConcertDto> save(@Valid @RequestBody ConcertDto concertDto) throws Exception {
        Concert concert = concertService.save(concertDto);
        return ResponseEntity.ok(concertDto);
    }


    @Operation(
            summary = "Updating info about concerts",
            description = "Allows you to update info about concerts"
    )
    @PutMapping("/concerts/{name}")
    public ResponseEntity<ConcertDto> update(@Valid @RequestBody ConcertDto concertDto, @PathVariable(name = "name") String name) {
        Concert concert = concertService.update(concertDto, name);
        return ResponseEntity.ok(concertDto);
    }


    @Operation(
            summary = "Deleting info about concerts",
            description = "Allows you to delete info about concerts"
    )
    @DeleteMapping("/concerts/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable(name = "name") String name) {
        concertService.delete(name);
    }
}