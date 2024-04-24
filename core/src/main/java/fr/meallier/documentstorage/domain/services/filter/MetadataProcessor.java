package fr.meallier.documentstorage.domain.services.filter;

import fr.meallier.documentstorage.domain.core.Metadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetadataProcessor {
    public MetadataProcessor(List<MetadataFilter> filters) {
        this.filters = filters;
    }

    List<MetadataFilter> filters;
    public Map<String, Metadata> applyFilters(byte [] data) {
        Map<String, Metadata> metadatas = new HashMap<>();

        for (MetadataFilter filter: filters) {
            filter.doFilter(data,metadatas);
        }

        return metadatas;
    }
}
