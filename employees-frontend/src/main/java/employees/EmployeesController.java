package employees;

import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
@Slf4j
public class EmployeesController {

    private EmployeesClient employeesClient;

    @GetMapping("/")
    @Observed(name = "employees.list", contextualName = "employees.list", lowCardinalityKeyValues = { "client-type", "rest-client" })
    public ModelAndView listEmployees() {
        log.info("Employees list page");
        Map<String, Object> model = new HashMap<>();
        model.put("employees", employeesClient.listEmployees());
        model.put("command", new Employee());

        return new ModelAndView("index", model);
    }

    @GetMapping("/create-employee")
    public ModelAndView createEmployee() {
        var model = Map.of(
                "command", new Employee()
        );
        return new ModelAndView("create-employee", model);
    }

    @PostMapping("/create-employee")
    public ModelAndView createEmployeePost(@ModelAttribute Employee command) {
        employeesClient.createEmployee(command);
        return new ModelAndView("redirect:/");
    }

}