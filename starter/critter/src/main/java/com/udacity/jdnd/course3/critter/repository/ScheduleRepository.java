package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.DTO.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByPets(Pet pet);
    List<Schedule> findByEmployees(Employee employee);
}
