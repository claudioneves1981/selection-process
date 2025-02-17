package com.claudioneves.selectionprocess.controllers;

import com.claudioneves.selectionprocess.dto.SelectedCandidate;
import com.claudioneves.selectionprocess.entities.Candidate;
import com.claudioneves.selectionprocess.repositories.CandidateRepository;
import com.claudioneves.selectionprocess.services.CandidateService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(value = "/candidates")
public class CandidateController {

    @Autowired
    private CandidateRepository repository;

    @Autowired
    private CandidateService service;

    @GetMapping
    public ResponseEntity<List<Candidate>> findAll() {
        List<Candidate> result = service.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<Candidate>> findAll(Pageable pageable) {
        Page<Candidate> result = service.findAll(pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/search-salary")
    public ResponseEntity<Page<Candidate>> searchBySalary(@RequestParam(defaultValue = "0") Double minSalary, @RequestParam(defaultValue = "1000000000000") Double maxSalary, Pageable pageable) {
        Page<Candidate> result = service.searchSalary(minSalary, maxSalary, pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/search-name")
    public ResponseEntity<Page<Candidate>> searchByName(@RequestParam(defaultValue = "") String name, Pageable pageable) {
        Page<Candidate> result = service.searchName(name,pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/select-candidates-by-options")
    public ResponseEntity<List<SelectedCandidate>> selectCandidates(@RequestParam(defaultValue = "2000") Double baseSalary, @RequestParam(defaultValue = "-") String DDD) {
        List<SelectedCandidate> result = service.searchByOptions(baseSalary, DDD);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/search-by-ddd")
    public ResponseEntity<Page<Candidate>> searchByDDD(@RequestParam String DDD, Pageable pageable) {
        Page<Candidate> result = service.searchByDDD(DDD, pageable);
        return ResponseEntity.ok(result);
    }

}
