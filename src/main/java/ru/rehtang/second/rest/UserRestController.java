package ru.rehtang.second.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.rehtang.second.dto.AnimalDto;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserRestController {
    @GetMapping(value = "/createAnimal", produces = MediaType.APPLICATION_JSON_VALUE)
    public AnimalDto createAnimal(@RequestParam String type, @RequestParam LocalDate birthday, @RequestParam String sex, @RequestParam String name){
        return service.createAnimal(name, birthday,sex,type);
    }

    @GetMapping(value = "/editAnimal", produces = MediaType.APPLICATION_JSON_VALUE)
    public AnimalDto editAnimal(@RequestParam String type, @RequestParam LocalDate birthday, @RequestParam String sex, @RequestParam String name){
        return service.editAnimal(type,birthday,sex,name);
    }
    @DeleteMapping("/deleteAnimal")
    public void deleteAnimal(@RequestParam String name){
        service.deleteAnimal;
    }
    @GetMapping(value = "/findOwnAnimals", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AnimalDto> findOwnAnimals(@RequestParam String name){
        return service.findOwmAnmimals;
    }
}
