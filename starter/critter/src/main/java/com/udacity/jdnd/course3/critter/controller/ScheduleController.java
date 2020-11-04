package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.DTO.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import java.util.ArrayList;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private ScheduleService scheduleService;
    private EmployeeService employeeService;
    private PetService petService;

    public ScheduleController(ScheduleService scheduleService,
            EmployeeService employeeService,
            PetService petService) {
        this.scheduleService = scheduleService;
        this.employeeService = employeeService;
        this.petService = petService;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule insertedSchedule = scheduleService
                .saveSchedule(convertScheduleDTOTOSchedule(scheduleDTO));
        return convertScheduleToScheduleDTO(insertedSchedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        return getScheduleDTOS(schedules);
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules = scheduleService.getScheduleForPet(petId);
        return getScheduleDTOS(schedules);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = scheduleService.getScheduleForEmployee(employeeId);
        return getScheduleDTOS(schedules);
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = scheduleService.getScheduleForCustomer(customerId);
        return getScheduleDTOS(schedules);
    }

    private List<ScheduleDTO> getScheduleDTOS(List<Schedule> schedules) {
        List<ScheduleDTO> scheduleDTOList = new ArrayList<ScheduleDTO>();
        for (Schedule schedule : schedules) {
            scheduleDTOList.add(convertScheduleToScheduleDTO(schedule));
        }
        return scheduleDTOList;
    }

    private ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        List<Long> petIds = new ArrayList<>();
        List<Long> employeeIds = new ArrayList<>();
        for (Pet pet : schedule.getPets()) {
            petIds.add(pet.getId());
        }
        for (Employee employee : schedule.getEmployees()) {
            employeeIds.add(employee.getId());
        }
        scheduleDTO.setPetIds(petIds);
        scheduleDTO.setEmployeeIds(employeeIds);
        return scheduleDTO;
    }

    private Schedule convertScheduleDTOTOSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        List<Pet> petList = new ArrayList<>();
        List<Employee> employeeList = new ArrayList<>();
        if(scheduleDTO.getPetIds()!=null){
            for (Long petId : scheduleDTO.getPetIds()) {
                petList.add(petService.getPetById(petId));
            }
        }
        if(scheduleDTO.getEmployeeIds()!=null){
            for (Long employeeId : scheduleDTO.getEmployeeIds()) {
                employeeList.add(employeeService.getEmployeeById(employeeId));
            }
        }
        schedule.setPets(petList);
        schedule.setEmployees(employeeList);
        return schedule;
    }
}
