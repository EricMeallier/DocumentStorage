package fr.meallier.documentstorage.domain.services.data.filtering;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class OutputDataProcessor extends DataProcessor {
    public OutputDataProcessor(ApplicationContext context, OutputDataProcessorConfig outputDataProcessorConfig) {
        buildFilters(context, outputDataProcessorConfig);
    }
}
