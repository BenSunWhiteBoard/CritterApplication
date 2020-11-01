package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    private PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet getPetById(Long id){
        Optional<Pet> result = petRepository.findById(id);
        return result.orElse(null);
    }

    public List<Pet> getAllPets(){
        return petRepository.findAll();
    }

    public Pet savePet(Pet pet){
        return petRepository.save(pet);
    }

    public List<Pet> getAllPetsByOwner(Long ownerId){
        return petRepository.findPetByCustomer_Id(ownerId);
    }
}
