package ru.rehtang.second.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rehtang.second.dto.OwnDto;
import ru.rehtang.second.persistance.repository.AnimalRepository;
import ru.rehtang.second.persistance.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class OwnedService {
    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;

    public void addOwned(OwnDto dto){
        var animal = animalRepository.getById(dto.getName());
        var user = userRepository.getById(dto.getUsername()).addToOwned(animal);
        userRepository.save(user);
    }

    public void removeOwned(OwnDto dto){
        var animal = animalRepository.getById(dto.getName());
        var user = userRepository.getById(dto.getUsername()).removeFromOwned(animal);
        userRepository.save(user);
    }

}
