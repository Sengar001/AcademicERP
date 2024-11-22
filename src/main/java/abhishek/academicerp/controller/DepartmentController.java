package abhishek.academicerp.controller;

import abhishek.academicerp.dto.DepartmentRequest;
import abhishek.academicerp.dto.DepartmentResponse;
import abhishek.academicerp.loginhelper.Security;
import abhishek.academicerp.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final Security security;

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateDepartment(@RequestHeader ("Authorization") String authHeader, @PathVariable ("id") Long id , @RequestBody @Valid DepartmentRequest departmentRequest) {
        if(!security.authenticate(authHeader)){
            return ResponseEntity.ok("Unauthorized");
        }
        return ResponseEntity.ok(departmentService.updateDepartment(departmentRequest, id));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> getDepartment(@RequestHeader ("Authorization") String authHeader) {
        if(!security.authenticate(authHeader)){
            return ResponseEntity.status(401).build();
        }
        List<DepartmentResponse> department = departmentService.getAllDepartment();
        return ResponseEntity.ok(department);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createDepartment(@RequestHeader ("Authorization") String authHeader, @RequestBody @Valid DepartmentRequest departmentRequest) {
        if(!security.authenticate(authHeader)){
            return ResponseEntity.ok("Unauthorized");
        }
        return ResponseEntity.ok(departmentService.createDepartment(departmentRequest));
    }

}
