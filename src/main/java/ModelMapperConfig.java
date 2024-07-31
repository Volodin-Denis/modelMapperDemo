import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class ModelMapperConfig {
    public static ModelMapper initializeModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<Employee, EmployeeDTO>() {
            @Override
            protected void configure() {
                using(ctx -> {
                    Address address = (Address) ctx.getSource();
                    if (address == null) {
                        return null;
                    }
                    return new AddressDTO(address.getStreet() + ", " + address.getCity());
                }).map(source.getAddress()).setAddress(null);

                using(ctx -> {
                    List<Job> jobs = (List<Job>) ctx.getSource();
                    return jobs.stream()
                        .map(job -> new JobDTO(job.getTitle(), job.getSalary().intValue()))
                        .collect(Collectors.toList());
                }).map(source.getJobs()).setJobs(null);
            }
        });

        return modelMapper;
    }
}