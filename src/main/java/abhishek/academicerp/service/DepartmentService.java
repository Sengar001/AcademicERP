package abhishek.academicerp.service;

import abhishek.academicerp.dto.DepartmentRequest;
import abhishek.academicerp.entity.Departments;
import abhishek.academicerp.mapper.DepartmentMapper;
import abhishek.academicerp.repo.DepartmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepo repo;
    private final DepartmentMapper mapper;

    public String createDepartment(DepartmentRequest request) {
        Departments department = mapper.toEntity(request);
        repo.save(department);
        return "Department created";
    }
}
