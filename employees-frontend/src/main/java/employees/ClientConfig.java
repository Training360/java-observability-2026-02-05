package employees;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;
import org.springframework.web.service.invoker.HttpExchangeAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration
@EnableConfigurationProperties(EmployeesProperties.class)
@ImportHttpServices(group = "employees-backend", types = EmployeesClient.class)
@Slf4j
public class ClientConfig {

    @Bean
    RestClientHttpServiceGroupConfigurer groupConfigurer(EmployeesProperties employeesProperties) {
        log.info("Employees backend url: {}", employeesProperties.getBackendUrl());
        return groups ->
            groups.filterByName("employees-backend").forEachClient((group, builder) ->
                    builder.baseUrl(employeesProperties.getBackendUrl()));

    }

//    @Bean
//    public EmployeesClient employeesClient(RestClient.Builder builder, EmployeesProperties employeesProperties) {
//        var restClient = builder
//                .baseUrl(employeesProperties.getBackendUrl())
//                .build();
//        var adapter = RestClientAdapter.create(restClient);
//        var factory = HttpServiceProxyFactory
//                .builderFor(adapter).build();
//        return factory.createClient(EmployeesClient.class);
//    }

}
