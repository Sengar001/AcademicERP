package abhishek.academicerp.service;

import abhishek.academicerp.dto.DepartmentRequest;
import abhishek.academicerp.dto.DepartmentResponse;
import abhishek.academicerp.dto.EmployeeResponse;
import abhishek.academicerp.entity.Departments;
import abhishek.academicerp.entity.Employees;
import abhishek.academicerp.loginhelper.EncryptionService;
import abhishek.academicerp.loginhelper.JWThelper;
import abhishek.academicerp.mapper.DepartmentMapper;
import abhishek.academicerp.mapper.EmployeeMaper;
import abhishek.academicerp.repo.DepartmentRepo;
import abhishek.academicerp.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepo repo;
    private final DepartmentMapper mapper;
    private final EmployeeRepo employeeRepo;
    private final EncryptionService encryptionService;
    private final JWThelper jwThelper;
    private final EmployeeMaper employeeMaper;

    public String createDepartment(DepartmentRequest request) {
        Departments department = mapper.toEntity(request);
        repo.save(department);
        return "Department created";
    }

    public String deleteDepartment(Long id) {
        Departments department = retrieveDepartment(id);
        repo.delete(department);
        return "Department deleted";
    }

    public String updateDepartment(DepartmentRequest request, Long id) {
        Departments department = retrieveDepartment(id);
        department.setName(request.name());
        department.setCapacity(request.capacity());
        repo.save(department);
        return "Department updated";

    }

    public List<EmployeeResponse> getEmployeeOfDepartment(Long id) {
        return employeeRepo.findByDepartment_Id(id).stream()
                .map(employeeMaper::employeeResponse)
                .collect(Collectors.toList());
    }

    public List<DepartmentResponse> getAllDepartment() {
        return repo.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public Departments retrieveDepartment(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException(format("Department with id %s not found", id)));
    }

    public Employees retrieveEmployees(String email) {
        return employeeRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException((format("Employee with email %s not found", email))));
    }

    public String login(String username, String password) {
        Employees employees = retrieveEmployees(username);
        if(!encryptionService.validates(password, employees.getPassword())){
            return "Incorrect password";
        }

        return jwThelper.generateToken(username);
    }
}
