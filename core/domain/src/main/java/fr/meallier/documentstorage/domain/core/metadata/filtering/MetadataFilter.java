package fr.meallier.documentstorage.domain.core.metadata.filtering;

import fr.meallier.documentstorage.domain.model.Metadata;

import java.util.Map;

public interface MetadataFilter {

    void doFilter(byte[] data, Map<String, Metadata> metadatas);
}
