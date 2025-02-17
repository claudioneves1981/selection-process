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

   public SelectedCandidate checkingCandidate(double pretendingSalary, Candidate candidate, Double baseSalary) {


        SelectedCandidate selectedCandidate;

        if(pretendingSalary > 5000) {

            Manager manager = new Manager();

            if (baseSalary > pretendingSalary) {

                manager.setCandidate(candidate.getName());
                manager.setMessage("O candidato foi selecionado para a vaga de Gerente, LIGAR PARA O CANDIDATO");
                manager.setSalary(candidate.getSalary());
                manager.setContactAttempt(gettingInTouch(candidate.getPhone()));
                manager.setVacancyCode("001");
                manager.setComission(1200);
                selectedCandidate = manager;


            } else if (baseSalary == pretendingSalary) {

                manager.setCandidate(candidate.getName());
                manager.setMessage("LIGAR PARA O CANDIDATO COM CONTRA PROPOSTA");
                manager.setSalary(candidate.getSalary());
                manager.setVacancyCode("001");
                manager.setContactAttempt(gettingInTouch(candidate.getPhone()));
                selectedCandidate = manager;

            } else {

                manager.setCandidate(candidate.getName());
                manager.setVacancyCode("001");
                manager.setMessage("AGUARDANDO O RESULTADO DOS DEMAIS CANDIDADOS");
                selectedCandidate = manager;

            }

        }else{

            Salesman salesman = new Salesman();

            if (baseSalary > pretendingSalary) {

                salesman.setCandidate(candidate.getName());
                salesman.setMessage("O candidato foi selecionado para a vaga de Vendedor, LIGAR PARA O CANDIDATO");
                salesman.setSalary(candidate.getSalary());
                salesman.setContactAttempt(gettingInTouch(candidate.getPhone()));
                salesman.setVacancyCode("001");
                salesman.setPercentPayablePerSold(10);
                selectedCandidate = salesman;



            } else if (baseSalary == pretendingSalary) {

                salesman.setCandidate(candidate.getName());
                salesman.setMessage("LIGAR PARA O CANDIDATO COM CONTRA PROPOSTA");
                salesman.setSalary(candidate.getSalary());
                salesman.setVacancyCode("001");
                salesman.setContactAttempt(gettingInTouch(candidate.getPhone()));
                selectedCandidate = salesman;

            } else {

                salesman.setCandidate(candidate.getName());
                salesman.setVacancyCode("001");
                salesman.setMessage("AGUARDANDO O RESULTADO DOS DEMAIS CANDIDADOS");
                selectedCandidate = salesman;

            }

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
}
