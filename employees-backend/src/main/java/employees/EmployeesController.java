package employees;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/employees")
@Slf4j
@RequiredArgsConstructor
public class EmployeesController {

    private final EmployeesService employeesService;

    private Random random = new Random();

    @GetMapping
    public List<EmployeeResource> listEmployees(@RequestHeader HttpHeaders headers) {
        if (random.nextInt(3) == 0) {
            throw new RuntimeException("Simulated error");
        }
        log.info("Listing employees, headers: {}", headers);
        return employeesService.listEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeResource findEmployeeById(@PathVariable long id) {
        return employeesService.findEmployeeById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EmployeeResource> createEmployee(@Valid @RequestBody EmployeeResource command, UriComponentsBuilder builder) {
        var resource = employeesService.createEmployee(command);
        return ResponseEntity.created(builder.path("/api/employees/{id}").buildAndExpand(resource.getId()).toUri()).body(resource);
    }

    @PutMapping("/{id}")
    public EmployeeResource updateEmployee(@PathVariable long id, @RequestBody EmployeeResource command) {
        return employeesService.updateEmployee(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable long id) {
        employeesService.deleteEmployee(id);
    }

}
