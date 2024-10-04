package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.HallDto;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.repository.HallRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class SimpleHallService implements HallService {
    private final HallRepository hallRepository;
    public SimpleHallService(HallRepository sql2oHallRepository) {
        this.hallRepository = sql2oHallRepository;
    }
    @Override
    public Collection<HallDto> finAll() {
        Collection<Hall> halls = hallRepository.findAll();
        Collection<HallDto> result = new ArrayList<>();
        for (Hall hall : halls) {
            result.add(new HallDto(
                    hall.getId(),
                    hall.getName(),
                    hall.getDescription(),
                    hall.getRowCount(),
                    hall.getPlaceCount()
            ));
        }
        return result;
    }

    @Override
    public Optional<HallDto> findById(int id) {
        var optionalHall = hallRepository.findById(id);
        if (optionalHall.isPresent()) {
            Hall hall = optionalHall.get();
            return Optional.of(new HallDto(
                    hall.getId(),
                    hall.getName(),
                    hall.getDescription(),
                    hall.getRowCount(),
                    hall.getPlaceCount()
            ));
        }
        return Optional.empty();
    }
}
