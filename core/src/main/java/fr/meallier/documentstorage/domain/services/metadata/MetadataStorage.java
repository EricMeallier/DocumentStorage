package fr.meallier.documentstorage.domain.services.metadata;

import fr.meallier.documentstorage.domain.core.Metadata;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface MetadataStorage {

    void addMetadata(UUID documentId, Map<String,Metadata> metadatas);

    void setMetadata(UUID documentId, Map<String,Metadata> metadatas);

    Map<String,Metadata> getMetadata(UUID documentId);

    List<UUID> search(Map<String, Metadata> metadatas);

    List<UUID> searchForMetadata(List<String> metadataKeys);

    Map<String, Metadata> indexDocument(byte[] data);

    void removeMetadata(UUID documentId, List<String> metadataKeys);
}