package com.claudioneves.aulajparepository.services;

import com.claudioneves.aulajparepository.dto.ContactAttempt;
import com.claudioneves.aulajparepository.dto.SelectedCandidate;
import com.claudioneves.aulajparepository.entities.Candidate;
import com.claudioneves.aulajparepository.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public List<Candidate> findAll(){
        return candidateRepository.findAll();
    }

    public Page<Candidate> findAll(Pageable pageable){
        return candidateRepository.findAll(pageable);
    }

    public Page<Candidate> searchSalary(Double minSalary, Double maxSalary, Pageable pageable){
        return candidateRepository.searchSalary(minSalary,maxSalary,pageable);
    }

    public Page<Candidate> searchName(String name, Pageable pageable){
         return candidateRepository.searchName(name, pageable);

    }

    public List<SelectedCandidate> searchBaseSalaryGreaterUserSalary(Double baseSalary) {

        List<Candidate> candidates = candidateRepository.searchBaseSalaryGreaterUserSalary(baseSalary);
        List<SelectedCandidate> selectedCandidatesList = new ArrayList<>();

        int actualCandidate = 0;
        double pretendingSalary;


        while (actualCandidate < candidates.size()) {

            Candidate candidate = candidates.get(actualCandidate);
            pretendingSalary = candidate.getSalary();
            selectedCandidatesList.add(checkingCandidate(pretendingSalary, candidate, baseSalary));
            if (selectedCandidatesList.size() == 5){
                break;
            }
            actualCandidate++;
        }

        return selectedCandidatesList;
    }

   public SelectedCandidate checkingCandidate(double pretendingSalary, Candidate candidate, Double baseSalary) {

        SelectedCandidate selectedCandidate = new SelectedCandidate();

        if (baseSalary > pretendingSalary) {

            selectedCandidate.setCandidate(candidate.getName());
            selectedCandidate.setMessage("O candidato foi selecionado para a vaga, LIGAR PARA O CANDIDATO");
            selectedCandidate.setSalary(candidate.getSalary());
            selectedCandidate.setContactAttempt(gettingInTouch());



        } else if (baseSalary == pretendingSalary) {

            selectedCandidate.setCandidate(candidate.getName());
            selectedCandidate.setMessage("LIGAR PARA O CANDIDATO COM CONTRA PROPOSTA");
            selectedCandidate.setSalary(candidate.getSalary());
            selectedCandidate.setContactAttempt(gettingInTouch());

        } else {

            selectedCandidate.setCandidate(candidate.getName());
            selectedCandidate.setMessage("AGUARDANDO O RESULTADO DOS DEMAIS CANDIDADOS");

        }

        return selectedCandidate;
    }

    public ContactAttempt gettingInTouch(){


        int realizedAttempts = 1;
        boolean continueAttempt;
        boolean answered;
        ContactAttempt contactAttempt = new ContactAttempt();
        do{

            answered = answer();
            continueAttempt = !answered;
            if(continueAttempt)
                realizedAttempts++;
            //else
           //     System.out.println("CONTATO REALIZADO COM SUCESSO");


        }while(continueAttempt && realizedAttempts < 3);


        contactAttempt.setAttempts(realizedAttempts);
        if(answered)
            contactAttempt.setMessage("CONSEGUIMOS CONTATO  NA " + realizedAttempts + " TENTATIVA");
        else
            contactAttempt.setMessage("NÃO CONSEGUIMOS CONTATO NÚMERO MAXIMO TENTATIVAS " + realizedAttempts + " REALIZADAS");

        return contactAttempt;
    }

    boolean answer(){
        return new Random().nextInt(3) == 1;
    }
}
