package com.secxndary.filiusmeretrixproject.controller;
import com.secxndary.filiusmeretrixproject.dto.PlayDto;
import com.secxndary.filiusmeretrixproject.entity.Play;
//import com.secxndary.filiusmeretrixproject.mapper.PlayMapper;
import com.secxndary.filiusmeretrixproject.service.PlayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Play REST Controller", description = "The controller accepts requests from the admin page")
@RestController
@RequestMapping(value = "/api/admin")
@RequiredArgsConstructor
@Slf4j
public class PlayController {

    private final PlayService playService;

    @Operation(
            summary = "Getting info about plays",
            description = "Allows you to get info about plays"
    )
    @GetMapping("/plays")
    public ResponseEntity<List<PlayDto>> getPlays() {
        return ResponseEntity.ok(playService.getAllPlays());
    }

    @Operation(
            summary = "Searching info about plays",
            description = "Allows you to search info about plays"
    )
    @GetMapping("/plays/{name}")
    public ResponseEntity<List<PlayDto>> searchFilm(@PathVariable(name = "name") String name) {
        return ResponseEntity.ok(playService.searchPlay(name));
    }

    @GetMapping("/filterPlays")
    public ResponseEntity<List<PlayDto>> searchFilm() {
        return ResponseEntity.ok(playService.filterByAlphabet());
    }

    @Operation(
            summary = "Saving info about plays",
            description = "Allows you to save info about plays"
    )
    @PostMapping("/plays")
    public ResponseEntity<PlayDto> save(@Valid @RequestBody PlayDto playDto) throws Exception {
        Play play = playService.save(playDto);
        return ResponseEntity.ok(playDto);
    }

    @Operation(
            summary = "Updating info about plays",
            description = "Allows you to update info about plays"
    )
    @PutMapping("/plays/{name}")
    public ResponseEntity<PlayDto> update(@Valid @RequestBody PlayDto playDto, @PathVariable(name = "name") String name) {
        Play play = playService.update(playDto, name);
        return ResponseEntity.ok(playDto);
    }

    @Operation(
            summary = "Deleting info about plays",
            description = "Allows you to delete info about plays"
    )
    @DeleteMapping("/plays/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable(name = "name") String name) {
        playService.delete(name);
    }
}
