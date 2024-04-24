package fr.meallier.documentstorage.domain.services.filter;

import fr.meallier.documentstorage.domain.core.Metadata;

import java.util.Map;

public interface MetadataFilter {

    void doFilter(byte[] data, Map<String, Metadata> metadatas);
}
