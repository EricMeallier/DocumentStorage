package fr.meallier.documentstorage.domain.services.data.filtering;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class InputDataProcessor extends DataProcessor {
    public InputDataProcessor(ApplicationContext context, InputDataProcessorConfig inputDataProcessorConfig) {
        buildFilters(context,inputDataProcessorConfig);
    }
}
