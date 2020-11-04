package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    private ScheduleRepository scheduleRepository;
    private PetService petService;
    private EmployeeService employeeService;

    public ScheduleService(
            ScheduleRepository scheduleRepository,
            PetService petService,
            EmployeeService employeeService) {
        this.scheduleRepository = scheduleRepository;
        this.petService = petService;
        this.employeeService = employeeService;
    }

    public Schedule saveSchedule(Schedule schedule){
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules(){
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleForPet(Long petId){
        Pet pet = petService.getPetById(petId);
        return scheduleRepository.findByPets(pet);
    }

    public List<Schedule> getScheduleForEmployee(Long employeeId){
        Employee employee = employeeService.getEmployeeById(employeeId);
        return scheduleRepository.findByEmployees(employee);
    }

    public List<Schedule> getScheduleForCustomer(Long customerId){
        List<Pet> petList = petService.getAllPetsByOwner(customerId);
        List<Schedule> schedulesForCustomer = new ArrayList<>();
        for(Pet pet:petList){
            List<Schedule> schedulesForPet = getScheduleForPet(pet.getId());
            if(!schedulesForPet.isEmpty()){
                schedulesForCustomer.addAll(schedulesForPet);
            }
        }
        return schedulesForCustomer;
    }

}
