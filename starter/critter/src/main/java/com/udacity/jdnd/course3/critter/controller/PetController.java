package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.DTO.PetDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import java.util.ArrayList;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    private PetService petService;
    private CustomerService customerService;

    public PetController(PetService petService,
            CustomerService customerService) {
        this.petService = petService;
        this.customerService = customerService;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = petService.savePet(convertPetDTOToPet(petDTO));
        return convertPetToPetDTO(pet);
    }

    @PostMapping("/{ownerId}")
    public PetDTO savePet(@RequestBody PetDTO petDTO,@PathVariable long ownerId) {
        petDTO.setOwnerId(ownerId);
        Pet pet = petService.savePet(convertPetDTOToPet(petDTO));
        return convertPetToPetDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return convertPetToPetDTO(petService.getPetById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets() {
        List<Pet> petList = petService.getAllPets();
        return getPetDTOs(petList);
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> petList = petService.getAllPetsByOwner(ownerId);
        return getPetDTOs(petList);
    }

    private List<PetDTO> getPetDTOs(List<Pet> petList) {
        List<PetDTO> petDTOList = new ArrayList<PetDTO>();
        for (Pet pet : petList) {
            petDTOList.add(convertPetToPetDTO(pet));
        }
        return petDTOList;
    }

    private PetDTO convertPetToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        Customer owner = pet.getCustomer();
        if (owner != null) {
            petDTO.setOwnerId(owner.getId());
        }
        return petDTO;
    }

    private Pet convertPetDTOToPet(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        Customer owner = customerService.findCustomerById(petDTO.getOwnerId());
        pet.setCustomer(owner);
        return pet;
    }
}
