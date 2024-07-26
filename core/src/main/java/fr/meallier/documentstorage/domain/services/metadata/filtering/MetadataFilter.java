package fr.meallier.documentstorage.domain.services.metadata.filtering;

import fr.meallier.documentstorage.domain.core.Metadata;

import java.util.Map;

public interface MetadataFilter {

    void doFilter(byte[] data, Map<String, Metadata> metadatas);
}
