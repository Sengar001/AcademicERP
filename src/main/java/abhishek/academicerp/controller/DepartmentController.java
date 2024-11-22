package abhishek.academicerp.controller;

import abhishek.academicerp.dto.DepartmentRequest;
import abhishek.academicerp.loginhelper.Security;
import abhishek.academicerp.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final Security security;

    @PostMapping("/create")
    public ResponseEntity<String> createDepartment(@RequestHeader ("Authorization") String authHeader, @RequestBody @Valid DepartmentRequest departmentRequest) {
        if(!security.authenticate(authHeader)){
            return ResponseEntity.ok("Unauthorized");
        }
        return ResponseEntity.ok(departmentService.createDepartment(departmentRequest));
    }

}
