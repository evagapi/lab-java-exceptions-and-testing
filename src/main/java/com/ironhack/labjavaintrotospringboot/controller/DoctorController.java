    package com.ironhack.labjavaintrotospringboot.controller;

    import com.ironhack.labjavaintrotospringboot.model.user.Department;
    import com.ironhack.labjavaintrotospringboot.model.user.Doctor;
    import com.ironhack.labjavaintrotospringboot.model.user.DoctorStatus;
    import com.ironhack.labjavaintrotospringboot.repository.DoctorRepository;
    import com.ironhack.labjavaintrotospringboot.service.DoctorService;
    import jakarta.validation.Valid;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.server.ResponseStatusException;

    import java.util.List;
    import java.util.Optional;

    import static com.ironhack.labjavaintrotospringboot.utils.Helper.stringToUpperCase;
    import static org.springframework.http.HttpStatus.BAD_REQUEST;
    import static org.springframework.http.HttpStatus.NOT_FOUND;

    @RestController
    public class DoctorController {

        @Autowired
        DoctorService doctorService;
        @Autowired
        DoctorRepository doctorRepository;

        @GetMapping("/doctors")
        @ResponseStatus(HttpStatus.OK)
        public List<Doctor> getAllDoctors() {
            return doctorService.getAllDoctors();
        }

        @GetMapping("/doctors/{id}")
        @ResponseStatus(HttpStatus.OK)
        public Doctor getDoctorById(@PathVariable(name = "id") int doctorId) {
            Optional<Doctor> $doctor = doctorRepository.findById(doctorId);
            if ($doctor.isPresent()) {
                return $doctor.get();
            }
            throw new ResponseStatusException(NOT_FOUND, "Unable to find the doctor");
        }

        @PostMapping ("/doctors")
        @ResponseStatus(HttpStatus.CREATED)
        public void addDoctor(@RequestBody @Valid Doctor doctor) {
            doctorRepository.save(doctor);
        }

        @GetMapping("/doctors/status/{status}")
        @ResponseStatus(HttpStatus.OK)
        public List<Doctor> getDoctorsByStatus(@PathVariable(name = "status") String status) {
            String upperCaseStatus = stringToUpperCase(status);
            try {
                DoctorStatus statusEnum = DoctorStatus.valueOf(upperCaseStatus);
                return doctorRepository.findByStatus(statusEnum);
            } catch (IllegalArgumentException e) {
                throw new ResponseStatusException(BAD_REQUEST, "Unable to find this status");
            }

        }

        @PutMapping("/doctors/{id}")
        @ResponseStatus(value = HttpStatus.NO_CONTENT)
        public void updateDoctorStatus(@PathVariable int id, @RequestBody Doctor doctor) {
            doctorService.updateStatus(id, doctor);
        }

        @GetMapping("/doctors/department/{department}")
        @ResponseStatus(HttpStatus.OK)
        public List<Doctor> getDoctorsByDepartment(@PathVariable(name = "department") String department) {
            String upperCaseDepartment = stringToUpperCase(department);
            try {
                Department departmentEnum = Department.valueOf(upperCaseDepartment);
                return doctorRepository.findByDepartment(departmentEnum);
            } catch (IllegalArgumentException e) {
                throw new ResponseStatusException(BAD_REQUEST, "Unable to find this department");
            }

        }

        @PutMapping("/doctors/department/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void updateDoctorDepartment(@PathVariable int id, @RequestBody Doctor doctor) {
            doctorService.updateDepartment(id, doctor);
        }
    }
