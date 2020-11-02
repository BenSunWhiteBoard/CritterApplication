package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.DTO.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT DISTINCT s FROM Schedule s LEFT JOIN Employee e ON e.id = :employeeId")
    List<Schedule> getScheduleForEmployee(Long employeeId);

    @Query("SELECT DISTINCT s FROM Schedule s LEFT JOIN Customer c ON c.id = :customerId")
    List<Schedule> getScheduleForCustomer(Long customerId);

    @Query("SELECT DISTINCT s FROM Schedule s LEFT JOIN Pet p ON p.id = :petId")
    List<Schedule> getScheduleForPet(Long petId);

}
