package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    private ScheduleRepository scheduleRepository;

    public ScheduleService(
            ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Schedule saveSchedule(Schedule schedule){
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules(){
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleForPet(Long petId){
        return scheduleRepository.getScheduleForPet(petId);
    }

    public List<Schedule> getScheduleForEmployee(Long employeeId){
        return scheduleRepository.getScheduleForEmployee(employeeId);
    }

    public List<Schedule> getScheduleForCustomer(Long customerId){
        return scheduleRepository.getScheduleForCustomer(customerId);
    }

}
