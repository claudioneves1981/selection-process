package com.claudioneves.aulajparepository.repositories;

import com.claudioneves.aulajparepository.entities.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long>{

    @Query("SELECT obj FROM Candidate obj WHERE obj.salary >= :minSalary AND obj.salary <= :maxSalary")
    Page<Candidate> searchSalary(Double minSalary, Double maxSalary, Pageable pageable);

    @Query("SELECT obj FROM Candidate obj WHERE LOWER(obj.name) LIKE LOWER(CONCAT('%',:name,'%'))")
    Page<Candidate> searchName(String name, Pageable pageable);

    @Query("SELECT obj FROM Candidate obj WHERE :baseSalary >= obj.salary AND (obj.phone LIKE (CONCAT('%(',:DDD,')%')) OR obj.phone LIKE (CONCAT('%',:DDD,'%')))")
    List<Candidate> searchByOptions(Double baseSalary, String DDD);

    @Query("SELECT obj FROM Candidate obj WHERE obj.phone LIKE (CONCAT('%(',:DDD,')%'))")
    Page<Candidate> searchByDDD(String DDD, Pageable pageable);


    Page<Candidate> findBySalaryBetween(Double minSalary, Double maxSalary, Pageable pageable);

    Page<Candidate> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
