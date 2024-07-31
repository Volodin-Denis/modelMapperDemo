import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ModelMapper modelMapper = ModelMapperConfig.initializeModelMapper();

        Employee sourceObject = new Employee();
        sourceObject.setName("Олег");
        sourceObject.setAge(33);
        sourceObject.setEmployed(true);

        Address address = new Address();
        address.setStreet("Улица");
        address.setCity("Город");
        sourceObject.setAddress(address);

        List<Job> jobs = List.of(new Job("Разработчик", BigDecimal.valueOf(80000)), new Job("Дизайнер", BigDecimal.valueOf(60000)));
        sourceObject.setJobs(jobs);

        EmployeeDTO sourceObjectDTO = modelMapper.map(sourceObject, EmployeeDTO.class);

        System.out.println("DTO Name: " + sourceObjectDTO.getName());
        System.out.println("DTO Age: " + sourceObjectDTO.getAge());
        System.out.println("DTO Employed: " + sourceObjectDTO.isEmployed());
        System.out.println("DTO Address: " + sourceObjectDTO.getAddress().getStreetAndCity());

        for (JobDTO jobDTO : sourceObjectDTO.getJobs()) {
            System.out.println("Job Title: " + jobDTO.getTitle() + ", Salary: " + jobDTO.getSalary());
        }
    }
}

@Data
class Employee {
    private String name;
    private int age;
    private boolean isEmployed;
    private Address address;
    private List<Job> jobs;
}

@Data
class Address {
    private String street;
    private String city;
}

@Data
@AllArgsConstructor
class Job {
    private String title;
    private BigDecimal salary;
}

@Data
class EmployeeDTO {
    private String name;
    private int age;
    private boolean isEmployed;
    private AddressDTO address;
    private List<JobDTO> jobs;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class AddressDTO {
    private String streetAndCity;
}

@Data
@AllArgsConstructor
class JobDTO {
    private String title;
    private int salary;
}