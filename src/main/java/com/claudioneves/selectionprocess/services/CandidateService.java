package com.claudioneves.selectionprocess.services;

import com.claudioneves.selectionprocess.dto.ContactAttempt;
import com.claudioneves.selectionprocess.dto.Manager;
import com.claudioneves.selectionprocess.dto.Salesman;
import com.claudioneves.selectionprocess.dto.SelectedCandidate;
import com.claudioneves.selectionprocess.entities.Candidate;
import com.claudioneves.selectionprocess.repositories.CandidateRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public Page<Candidate> searchByDDD(String DDD, Pageable pageable){
        return candidateRepository.searchByDDD(DDD,pageable);
    }


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

    public List<SelectedCandidate> searchByOptions(Double baseSalary, String DDD) {

        List<Candidate> candidates = candidateRepository.searchByOptions(baseSalary, DDD);
        List<SelectedCandidate> selectedCandidatesList = new ArrayList<>();

        for(Candidate candidate : candidates) {

            if(candidate.getPhone().indexOf(DDD) == 1 || candidate.getPhone().indexOf(DDD) == 10 ) {
                double pretendingSalary = candidate.getSalary();
                SelectedCandidate selectedCandidate = checkingCandidate(pretendingSalary, candidate, baseSalary);
                selectedCandidatesList.add(selectedCandidate);
                if (selectedCandidatesList.size() == 5) {
                    break;
                }
            }

        }

        messageReturn(selectedCandidatesList);
        return selectedCandidatesList;
    }

        public void messageReturn(List<SelectedCandidate> selectedCandidateList){


          for(SelectedCandidate candidate : selectedCandidateList){

                if(candidate.getContactAttempt().getAwnser()){

                    candidate.getContactAttempt().setMessage("CONSEGUIMOS CONTATO  NA " + candidate.getContactAttempt().getAttempts() + " TENTATIVA");

                }else{

                    candidate.getContactAttempt().setMessage("NÃO CONSEGUIMOS CONTATO NÚMERO MAXIMO TENTATIVAS " + candidate.getContactAttempt().getAttempts() + " REALIZADAS");
                }
          }

     }

   public SelectedCandidate checkingCandidate(Double pretendingSalary, Candidate candidate, Double baseSalary) {

        SelectedCandidate selectedCandidate;

        if(pretendingSalary > 5000) {

            selectedCandidate = checkingFuncition(new Manager(), baseSalary, pretendingSalary ,candidate);

        }else{

            selectedCandidate = checkingFuncition(new Salesman(), baseSalary, pretendingSalary ,candidate);

        }

        return selectedCandidate;
    }

    public ContactAttempt gettingInTouch(String phone){


        int realizedAttempts = 1;
        boolean continueAttempt;
        boolean answered;
        ContactAttempt contactAttempt = new ContactAttempt();
        do{

            answered = answer();
            continueAttempt = !answered;
            if(continueAttempt)
                realizedAttempts++;



        }while(continueAttempt && realizedAttempts < 3);


        contactAttempt.setAttempts(realizedAttempts);
        contactAttempt.setPhoneAttempted(phone);
        contactAttempt.setAwnser(answered);
        return contactAttempt;
    }



    boolean answer(){
        return new Random().nextInt(3) == 1;
    }

    public SelectedCandidate checkingFuncition(SelectedCandidate selectedCandidate, Double baseSalary ,Double pretendingSalary, Candidate candidate){

        if (baseSalary > pretendingSalary && (selectedCandidate instanceof Manager  || selectedCandidate instanceof Salesman)) {

            selectedCandidate.setCandidate(candidate.getName());
            selectedCandidate.setMessage("O candidato foi selecionado para a vaga de " + selectedCandidate.getClass().getSimpleName() +", LIGAR PARA O CANDIDATO");
            selectedCandidate.setSalary(candidate.getSalary());
            selectedCandidate.setContactAttempt(gettingInTouch(candidate.getPhone()));
            selectedCandidate.setVacancyCode("001");
            if(selectedCandidate instanceof Manager manager) {
                manager.setComission(1200);
            }else {
                Salesman salesman = (Salesman) selectedCandidate;
                salesman.setPercentPayablePerSold(10);
            }


        } else if (baseSalary.equals(pretendingSalary)) {

            selectedCandidate.setCandidate(candidate.getName());
            selectedCandidate.setMessage("LIGAR PARA O CANDIDATO COM CONTRA PROPOSTA");
            selectedCandidate.setSalary(candidate.getSalary());
            selectedCandidate.setVacancyCode("001");
            selectedCandidate.setContactAttempt(gettingInTouch(candidate.getPhone()));

        } else {

            selectedCandidate.setCandidate(candidate.getName());
            selectedCandidate.setVacancyCode("001");
            selectedCandidate.setMessage("AGUARDANDO O RESULTADO DOS DEMAIS CANDIDADOS");

        }

        return selectedCandidate;

    }
}
