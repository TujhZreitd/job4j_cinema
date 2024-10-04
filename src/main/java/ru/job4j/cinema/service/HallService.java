package ru.job4j.cinema.service;

import ru.job4j.cinema.dto.HallDto;

import java.util.Collection;
import java.util.Optional;

public interface HallService {
    Collection<HallDto> finAll();
    Optional<HallDto> findById(int id);
}
