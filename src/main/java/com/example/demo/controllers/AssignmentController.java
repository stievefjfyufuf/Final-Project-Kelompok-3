package com.example.demo.controllers;

import com.example.demo.models.Assignment;
import com.example.demo.repositories.AssignmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@CrossOrigin
public class AssignmentController {

    private final AssignmentRepository repo;

    public AssignmentController(AssignmentRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Assignment> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Assignment getById(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public Assignment create(@RequestBody Assignment assignment) {
        return repo.save(assignment);
    }

    @PutMapping("/{id}")
    public Assignment update(@PathVariable Long id, @RequestBody Assignment newData) {
        Assignment a = repo.findById(id).orElse(null);
        if (a != null) {
            a.setTitle(newData.getTitle());
            a.setStatus(newData.getStatus());
            a.setDeadline(newData.getDeadline());
            return repo.save(a);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "Deleted";
    }
}