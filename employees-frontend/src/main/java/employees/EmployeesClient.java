package employees;


import io.micrometer.observation.annotation.Observed;
import org.springframework.resilience.annotation.ConcurrencyLimit;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@HttpExchange("/api/employees")
public interface EmployeesClient {

    @GetExchange
    @Retryable(maxRetries = 3, delay = 100, multiplier = 2, maxDelay = 1000)
    @ConcurrencyLimit(3)
    List<Employee> listEmployees();

    @PostExchange
    Employee createEmployee(@RequestBody Employee employee);

    @GetMapping
    Employee findEmployeeById(@PathVariable Long employeeId);
}
