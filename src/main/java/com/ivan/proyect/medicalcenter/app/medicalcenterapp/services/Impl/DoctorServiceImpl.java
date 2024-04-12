package com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.dto.DoctorDto;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.ConsultoringRoom;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Day;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Doctor;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.Schedule;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.entities.security.User;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.mapper.DoctorMapperDto;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.request.DoctorRequest;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.persistence.util.Role;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories.DayRepository;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories.DoctorRepository;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories.RoleRepository;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories.RoomRepository;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories.ScheduleRepository;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.repositories.UserRepository;
import com.ivan.proyect.medicalcenter.app.medicalcenterapp.services.DoctorService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    DayRepository dayRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    private RoleRepository roleRepository;
    // @Autowired
    // private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<DoctorDto> findAll() {
        List<Doctor> doctors = (List<Doctor>) doctorRepository.findAll();
        return doctors.stream().map(doc -> DoctorMapperDto.builder().setDoctor(doc).build())
                .collect(Collectors.toList());
    }

    @SuppressWarnings("null")
    @Transactional
    @Override
    public DoctorDto save(Doctor doctor) {
        User user = doctor.getUser();
        setRole(user);
        user.setDoc(doctor);
        userRepository.save(user);
        days(doctor);
        if (doctor.getConsultoringRoom() != null && doctor.getConsultoringRoom().getId() != null) {
            ConsultoringRoom existingConsultoringRoom = roomRepository.findById(doctor.getConsultoringRoom().getId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Room not found with ID: " + doctor.getConsultoringRoom().getId()));
            existingConsultoringRoom.setDoctor(doctor);
            doctor.addConsultoringRoom(existingConsultoringRoom);
        }
        return DoctorMapperDto.builder().setDoctor(doctorRepository.save(doctor)).build();
    }

    @SuppressWarnings("null")
    @Transactional
    @Override
    public Optional<Doctor> delete(Long id) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        optionalDoctor.ifPresent(p -> {
            userRepository.delete(p.getUser());
            doctorRepository.delete(p);
        });
        return optionalDoctor;
    }

    @SuppressWarnings("null")
    @Override
    @Transactional(readOnly = true)
    public Optional<DoctorDto> findByIdDto(Long id) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        if (optionalDoctor.isPresent()) {
            return Optional.of(DoctorMapperDto.builder().setDoctor(optionalDoctor.get()).build());
        }
        return Optional.empty();
    }

    @SuppressWarnings("null")
    @Override
    @Transactional(readOnly = true)
    public Optional<Doctor> findById(Long id) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        if (optionalDoctor.isPresent()) {
            return optionalDoctor;
        }
        return Optional.empty();
    }

    @SuppressWarnings("null")
    @Override
    @Transactional
    public Optional<DoctorDto> update(Long id, DoctorRequest updatedDoctor) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        if (optionalDoctor.isPresent()) {
            Doctor newDoctor = optionalDoctor.get();
            // Update Doctor details
            newDoctor.setName(updatedDoctor.getName());
            newDoctor.setLastname(updatedDoctor.getLastname());
            newDoctor.setDni(updatedDoctor.getDni());
            newDoctor.setPhone(updatedDoctor.getPhone());
            newDoctor.setRegistration(updatedDoctor.getRegistration());
            newDoctor.setEspeciality(updatedDoctor.getEspeciality());
            newDoctor.setEmmergency(updatedDoctor.getEmmergency());
            setAtributtes(id, updatedDoctor, newDoctor);
            return Optional.of(DoctorMapperDto.builder().setDoctor(doctorRepository.save(newDoctor)).build());
        }

        return Optional.empty();
    }

    public User setRole(User user) {
        List<Role> roles = new ArrayList<>();
        Optional<Role> optionaRoleUser = roleRepository.findByName("ROLE_DOC");
        optionaRoleUser.ifPresent(roles::add);
        if (user.isAdmin()) {
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }
        user.setRoles(roles);
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPassword(user.getPassword());
        return user;
    }

    public void days(Doctor doctor) {
        String[] days = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };
        String start = doctor.getStart();
        String end = doctor.getEnd();
        for (int i = 0; i < days.length; i++) {
            doctor.addDays(new Day(start, end, days[i]));
        }
        doctor.getDays().stream().forEach(d -> {
            determinate(d);
            d.setDoctor(doctor);
            dayRepository.save(d);

        });
    }

    public Day determinate(Day day) {
        String[] auxStart = day.getStart().split(":");
        Integer start = Integer.parseInt(auxStart[0]);
        String[] auxEnd = day.getEnd().split(":");
        Integer end = (Integer.parseInt(auxEnd[0]) - Integer.parseInt(auxStart[0])) * 2 + 1;
        Boolean flag = auxEnd[1].equals("00") ? true : false;
        for (int i = 0; i < end; i++) {
            Schedule newSchedule = new Schedule();
            if (flag) {
                newSchedule.setHour(start + ":" + "00");
                day.addSchedule(newSchedule);
                scheduleRepository.save(newSchedule);
                flag = false;
            } else {
                newSchedule.setHour(start + ":" + "30");
                day.addSchedule(newSchedule);
                scheduleRepository.save(newSchedule);
                flag = true;
                start++;
            }
        }
        return day;
    }

    @SuppressWarnings("null")
    public void setAtributtes(Long id, DoctorRequest updatedDoctor, Doctor newDoctor) {
        Optional<ConsultoringRoom> optionalRoom = roomRepository.findByName(updatedDoctor.getRoom());
        if (optionalRoom.isPresent()) {
            newDoctor.removeConsultoringRoom();
            newDoctor.addConsultoringRoom(optionalRoom.get());
            roomRepository.save(optionalRoom.get());
        }
        days(newDoctor);
    }
}
