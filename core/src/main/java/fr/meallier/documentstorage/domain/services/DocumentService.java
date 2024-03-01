package fr.meallier.documentstorage.domain.services;

import fr.meallier.documentstorage.domain.core.Metadata;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface DocumentService {

    /**
     * Store data of a document to the system
     * @param data sent to the system
     * @return id of the storage
     */
    UUID storeData(byte[] data);

    /**
     * Store data of a document to the system with associated metadatas
     * @param data sent to the system
     * @param metadatas saved to the system
     * @return data
     */
    UUID storeData(byte[] data, Map<String, Metadata> metadatas);

    /**
     * Retrieve the data of a document
     * @param documentId id of the document
     * @return data
     */
    byte[] getData(UUID documentId);

    /**
     * Add metadatas to an already stored document
     * @param documentId document to enrich
     * @param metadatas to add
     */
    void addMetadata(UUID documentId, Map<String,Metadata> metadatas);

    /**
     * Replace metadatas to an already stored document
     * @param documentId document to enrich
     * @param metadatas to set
     */
    void setMetadata(UUID documentId, Map<String,Metadata> metadatas);

    /**
     * Retrieve metadatas of the stored document
     * @param documentId targeted document
     * @return list of metadatas
     */
    Map<String,Metadata> getMetadata(UUID documentId);

    /**
     * Search for stored document, based on value of metadata
     * @param metadatas list of metadatas to match
     * @return list of document ids
     */
    List<UUID> search(Map<String, Metadata> metadatas);

    /**
     * Search for stored document, based on presence of metadata
     * @param metadataKey list of metadata keys
     * @return list of document ids
     */
    List<UUID> searchForMetadata(List<String> metadataKey);
}