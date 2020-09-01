package com.github.curriculeon.services;

import com.github.curriculeon.repositories.BakerRepository;
import com.github.curriculeon.models.Baker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BakerService {

    private BakerRepository repository;

    @Autowired
    public BakerService(BakerRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void setup() {
        Baker baker1 = new Baker();
        baker1.setName("Leon");
        baker1.setSpecialty("Bread");
        baker1.setEmployeeId("01");

        Baker baker2 = new Baker();
        baker2.setName("Haseeb");
        baker2.setSpecialty("Bagal");
        baker2.setEmployeeId("02");

        repository.save(baker1);
        repository.save(baker2);
    }

    public Iterable<Baker> index() {
        return repository.findAll();
    }

    public Baker show(Long id) {
        return repository.findById(id).get();
    }

    public Baker create(Baker baker) {
        return repository.save(baker);
    }

    public Baker update(Long id, Baker newBakerData) {
        Baker originalBaker = repository.findById(id).get();
        originalBaker.setName(newBakerData.getName());
        originalBaker.setSpecialty(newBakerData.getSpecialty());
        return repository.save(originalBaker);
    }

    public Boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }

    public List<Baker> readAll() {
        Iterable<Baker> personIterable = repository.findAll();
        List<Baker> personList = new ArrayList<>();
        personIterable.forEach(personList::add);
        return personList;
    }
}
