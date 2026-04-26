package com.example.demo.controllers;

import com.example.demo.models.Schedule;
import com.example.demo.repositories.ScheduleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@CrossOrigin
public class ScheduleController {

    private final ScheduleRepository repo;

    public ScheduleController(ScheduleRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Schedule> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Schedule getById(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public Schedule create(@RequestBody Schedule schedule) {
        return repo.save(schedule);
    }

    @PutMapping("/{id}")
    public Schedule update(@PathVariable Long id, @RequestBody Schedule newData) {
        Schedule s = repo.findById(id).orElse(null);
        if (s != null) {
            s.setTitle(newData.getTitle());
            s.setStartTime(newData.getStartTime());
            s.setEndTime(newData.getEndTime());
            return repo.save(s);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "Deleted";
    }
}