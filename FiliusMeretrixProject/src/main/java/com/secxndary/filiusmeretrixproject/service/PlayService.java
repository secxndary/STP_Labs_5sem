package com.secxndary.filiusmeretrixproject.service;
import com.secxndary.filiusmeretrixproject.dto.PlayDto;
import com.secxndary.filiusmeretrixproject.entity.Play;
import com.secxndary.filiusmeretrixproject.mapper.PlayMapper;
import com.secxndary.filiusmeretrixproject.repository.PlayRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
public class PlayService {

    private final PlayRepository playRepository;
    private final PlayMapper playMapper;

    public List<PlayDto> getAllPlays() {
        List<PlayDto> playDtos = new ArrayList<>();
        List<Play> plays = playRepository.findAll();
        for (Play play : plays) {
            PlayDto playDto = playMapper.toDTO(play);
            playDtos.add(playDto);
        }
        return playDtos;
    }


    public List<PlayDto> searchPlay(String name) {
        List<Play> allPlays = new ArrayList<>();
        List<PlayDto> playDtos = new ArrayList<>();
        allPlays = playRepository.findAll()
                .stream()
                .filter(play -> play.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        for (Play play : allPlays) {
            PlayDto playDto = playMapper.toDTO(play);
            playDtos.add(playDto);
        }
        return playDtos;
    }


    public List<PlayDto> filterByAlphabet() {
        List<PlayDto> playDtos = getAllPlays();
        playDtos.sort(Comparator.comparing(PlayDto::getName));
        return playDtos;
    }

    @Transactional
    public Play save(PlayDto playDto) throws Exception {
        final Play play = playMapper.fromDTO(playDto);
        Play findPlay = playRepository.findByName(play.getName());
        if (findPlay == null) {
            log.info("Created play {}", play);
            return playRepository.save(play);
        }
        throw new Exception("Play names cannot be repeated");
    }

    @Transactional
    public Play update(PlayDto playDto, String name) {
        Play play = playRepository.findByName(name);
        Play changedPlay = playMapper.fromDTO(playDto);
        play.setName(changedPlay.getName());
        play.setAddress(changedPlay.getAddress());
        play.setDate(changedPlay.getDate());
        play.setDescription(changedPlay.getDescription());
        play.setPrice(changedPlay.getPrice());
        log.info("Update play {}", play);
        return playRepository.save(play);
    }

    // TODO: delete service
    @Transactional
    public void delete(String name) {
        Play play = playRepository.findByName(name);
        playRepository.deleteById(play.getName());
        log.info("Delete completed {}", play);
    }
}
