package fr.meallier.documentstorage.domain.services;

import fr.meallier.documentstorage.domain.core.Metadata;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface DocumentService {

    UUID storeData(byte[] data);

    UUID storeData(byte[] data, Map<String, Metadata> metadatas);

    byte[] getData(UUID documentId);

    void addMetadata(UUID documentId, Map<String,Metadata> metadatas);

    void setMetadata(UUID documentId, Map<String,Metadata> metadatas);

    Map<String,Metadata> getMetadata(UUID documentId);

    List<UUID> search(Map<String, Metadata> metadatas);
}