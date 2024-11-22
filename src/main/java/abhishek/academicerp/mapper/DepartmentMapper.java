package abhishek.academicerp.mapper;

import abhishek.academicerp.dto.DepartmentRequest;
import abhishek.academicerp.entity.Departments;
import org.springframework.stereotype.Service;

@Service
public class DepartmentMapper {
    public Departments toEntity(DepartmentRequest request) {
        return Departments.builder()
                .name(request.name())
                .capacity(request.capacity())
                .build();
    }
}
