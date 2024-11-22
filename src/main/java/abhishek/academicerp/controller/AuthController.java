package abhishek.academicerp.controller;

import abhishek.academicerp.loginhelper.EncryptionService;
import abhishek.academicerp.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final DepartmentService departmentService;
    private final EncryptionService encryptionService;

    @GetMapping
    public ResponseEntity<String> getAuthToken() {
        String name = "sengar";
        return ResponseEntity.ok(encryptionService.encode(name));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam ("user") String user, @RequestParam ("password") String password) {
        return ResponseEntity.ok(departmentService.login(user, password));
    }

}
