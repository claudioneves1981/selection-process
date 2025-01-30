package com.claudioneves.aulajparepository.services;

import com.claudioneves.aulajparepository.dto.ContactAttempt;
import com.claudioneves.aulajparepository.dto.SelectedCandidate;
import com.claudioneves.aulajparepository.entities.User;
import com.claudioneves.aulajparepository.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Page<User> findAll(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    public Page<User> searchSalary(Double minSalary, Double maxSalary, Pageable pageable){
        return userRepository.searchSalary(minSalary,maxSalary,pageable);
    }

    public Page<User> searchName(String name, Pageable pageable){
         return userRepository.searchName(name, pageable);

    }



    public List<SelectedCandidate> searchBaseSalaryGreaterUserSalary() {

        Double baseSalary = 5000.0;
        List<User> candidates = userRepository.searchBaseSalaryGreaterUserSalary(baseSalary);
        List<SelectedCandidate> selectedCandidatesList = new ArrayList<>();

        int selectedCandidates = 0;
        int actualCandidate = 0;
        double pretendingSalary = 0D;


        while (actualCandidate < candidates.size()) {

            User candidate = candidates.get(actualCandidate);
            pretendingSalary = candidate.getSalary();
            selectedCandidatesList.add(chekingCandidate(pretendingSalary, candidate, baseSalary));
            if (selectedCandidatesList.size() == 5){
                break;
            }
            actualCandidate++;
        }

        return selectedCandidatesList;
    }

   public SelectedCandidate chekingCandidate(double pretendingSalary, User candidate, Double baseSalary) {

        SelectedCandidate selectedCandidate = new SelectedCandidate();

        if (baseSalary > pretendingSalary) {

            selectedCandidate.setCandidate(candidate.getName());
            selectedCandidate.setMessage("O candidato foi selecionado para a vaga, LIGAR PARA O CANDIDATO");
            selectedCandidate.setContactAttempt(gettingInTouch());



        } else if (baseSalary == pretendingSalary) {

            selectedCandidate.setCandidate(candidate.getName());
            selectedCandidate.setMessage("LIGAR PARA O CANDIDATO COM CONTRA PROPOSTA");
            selectedCandidate.setContactAttempt(gettingInTouch());

        } else {

            selectedCandidate.setCandidate(candidate.getName());
            selectedCandidate.setMessage("AGUARDANDO O RESULTADO DOS DEMAIS CANDIDADOS");

        }

        return selectedCandidate;
    }

    public ContactAttempt gettingInTouch(){


        int realizedAttempts = 1;
        boolean continueAttempt = true;
        boolean answered = false;
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
