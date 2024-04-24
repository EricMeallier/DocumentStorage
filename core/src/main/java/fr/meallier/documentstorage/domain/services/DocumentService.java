package fr.meallier.documentstorage.domain.services;

import fr.meallier.documentstorage.domain.core.Metadata;
import fr.meallier.documentstorage.domain.services.data.DataStorage;
import fr.meallier.documentstorage.domain.services.filter.MetadataProcessor;
import fr.meallier.documentstorage.domain.services.metadata.MetadataStorage;

import java.util.*;

public abstract class DocumentService {

    MetadataProcessor metadataProcessor;

    DataStorage dataStorage;

    MetadataStorage metadataStorage;

    public DocumentService( DataStorage dataStorage, MetadataStorage metadataStorage,MetadataProcessor metadataProcessor) {
        this.dataStorage = dataStorage;
        this.metadataStorage = metadataStorage;
        this.metadataProcessor = metadataProcessor;
    }

    /**
     * Store data of a document to the system
     * @param data sent to the system
     * @return id of the storage
     */
    public UUID storeData(byte[] data) {
        Map<String, Metadata> generatedMetadatas = indexDocument(data);
        UUID documentId = dataStorage.storeData(data);
        metadataStorage.setMetadata(documentId,generatedMetadatas);
        return documentId;
    }

    /**
     * Store data of a document to the system with associated metadatas
     * @param data sent to the system
     * @param metadatas saved to the system
     * @return data
     */
    public UUID storeData(byte[] data, Map<String, Metadata> metadatas) {
        Map<String, Metadata> generatedMetadatas = indexDocument(data);
        UUID documentId = dataStorage.storeData(data);
        metadataStorage.setMetadata(documentId,generatedMetadatas);
        metadataStorage.addMetadata(documentId,metadatas);
        return documentId;
    }

    /**
     * Get all document Id - Use with care/testing
     * @return List of document Id
     */
    abstract List<UUID> getAllDocuments();

    /**
     * Retrieve the data of a document
     * @param documentId id of the document
     * @return data
     */
    public byte[] getData(UUID documentId) {
        return dataStorage.getData(documentId);
    }

    /**
     * Add metadatas to an already stored document
     * @param documentId document to enrich
     * @param metadatas to add
     */
    public void addMetadata(UUID documentId, Map<String,Metadata> metadatas) {
        metadataStorage.addMetadata(documentId,metadatas);
    }

    /**
     * Add unique metadata to an already stored document
     * @param documentId document to enrich
     * @param  metadata to add
     */
    public void addMetadata(UUID documentId, Metadata metadata) {
        Map<String,Metadata> metadatas = getMetadata(documentId);

        if (metadatas.containsKey(metadata.key())) {
            Metadata newMetadata = new Metadata(metadata.key(), metadatas.get(metadata.key()).values() + Metadata.separator + metadata.values());
            metadatas.put(metadata.key(),newMetadata);
        } else {
            metadatas.put(metadata.key(),metadata);
        }


        setMetadata(documentId,metadatas);
    }

    /**
     * Replace metadatas to an already stored document
     * @param documentId document to enrich
     * @param metadatas to set
     */
    public void setMetadata(UUID documentId, Map<String,Metadata> metadatas) {
        metadataStorage.setMetadata(documentId,metadatas);
    }

    /**
     * Replace unique metadata to an already stored document
     * @param documentId document to enrich
     * @param metadata to set
     */
    void setMetadata(UUID documentId, Metadata metadata) {
        Map<String,Metadata> metadatas = getMetadata(documentId);

        metadatas.put(metadata.key(),metadata);

        setMetadata(documentId,metadatas);
    }

    /**
     * Retrieve metadatas of the stored document
     * @param documentId targeted document
     * @return Map of metadatas
     */
    public Map<String,Metadata> getMetadata(UUID documentId) {
        return metadataStorage.getMetadata(documentId);
    }

    /**
     * Search for stored document, based on value of metadata
     * @param metadatas list of metadatas to match
     * @return list of document ids
     */
    public List<UUID> search(Map<String, Metadata> metadatas) {
        return metadataStorage.search(metadatas);
    }

    /**
     * Search for stored document, based on presence of metadata
     * @param metadataKey list of metadata keys
     * @return list of document ids
     */
    public List<UUID> searchForMetadata(List<String> metadataKey) {
        return metadataStorage.searchForMetadata(metadataKey);
    }

    /**
     * Export all repository as a String
     * @return big String
     */
    public String exportAll() {
        StringBuilder result = new StringBuilder();
        if (getAllDocuments() != null) {
            for (UUID documentId : getAllDocuments()) {
                result.append("documentId: ").append(documentId);
                for (String key : getMetadata(documentId).keySet()) {
                    result.append("\tkey: ").append(getMetadata(documentId).get(key));
                }
            }
        }
        return result.toString();
    }

    public Map<String, Metadata> indexDocument(byte[] data) {
        return metadataProcessor.applyFilters(data);
    }
}