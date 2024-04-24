package fr.meallier.documentstorage.domain.services.filter;

import fr.meallier.documentstorage.domain.core.Metadata;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MetadataProcessor {

    List<MetadataFilter> filters;

    public MetadataProcessor(ApplicationContext context, MetadataProcessorConfig metadataProcessorConfig) {
        filters = new ArrayList<>();
        for (String filterName: metadataProcessorConfig.filterNames()) {
            MetadataFilter filter = (MetadataFilter)context.getBean(filterName);
            filters.add(filter);
        }
    }

    public Map<String, Metadata> applyFilters(byte [] data) {
        Map<String, Metadata> metadatas = new HashMap<>();

        for (MetadataFilter filter: filters) {
            filter.doFilter(data,metadatas);
        }

        return metadatas;
    }
}
