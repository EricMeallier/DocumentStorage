package fr.meallier.documentstorage.domain.services.data.filtering;

import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

public abstract class DataProcessor {
    List<DataFilter> filters;

    public byte [] applyFilters(byte [] data) {

        byte[] result = data.clone();
        for (DataFilter filter: filters) {
            result = filter.doFilter(result);
        }

        return result;
    }

    protected void buildFilters(ApplicationContext context, DataProcessorConfig dataProcessorConfig) {
        filters = new ArrayList<>();
        for (String filterName: dataProcessorConfig.filterNames()) {
            DataFilter filter = (DataFilter)context.getBean(filterName);
            filters.add(filter);
        }
    }
}
