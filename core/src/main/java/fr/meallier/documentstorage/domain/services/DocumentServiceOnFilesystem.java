package fr.meallier.documentstorage.domain.services;

import fr.meallier.documentstorage.domain.core.Metadata;
import fr.meallier.documentstorage.domain.services.data.DataStorage;
import fr.meallier.documentstorage.domain.services.metadata.MetadataStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Qualifier("OnFilesystem")
public class DocumentServiceOnFilesystem implements DocumentService {

    @Autowired
    DataStorage dataStorageOnFilesystem;

    @Autowired
    MetadataStorage metadataStorageInMemory;

    @Override
    public UUID storeData(byte[] data) {
        Map<String, Metadata> generatedMetadatas = metadataStorageInMemory.indexDocument(data);
        UUID documentId = dataStorageOnFilesystem.storeData(data);
        metadataStorageInMemory.setMetadata(documentId,generatedMetadatas);
        return documentId;
    }

    @Override
    public UUID storeData(byte[] data, Map<String, Metadata> metadatas) {
        Map<String, Metadata> generatedMetadatas = metadataStorageInMemory.indexDocument(data);
        UUID documentId = dataStorageOnFilesystem.storeData(data);
        metadataStorageInMemory.setMetadata(documentId,generatedMetadatas);
        metadataStorageInMemory.addMetadata(documentId,metadatas);
        return documentId;
    }

    @Override
    public byte[] getData(UUID documentId) {
        return dataStorageOnFilesystem.getData(documentId);
    }

    @Override
    public void addMetadata(UUID documentId, Map<String, Metadata> metadatas) {
        metadataStorageInMemory.addMetadata(documentId,metadatas);
    }

    @Override
    public void setMetadata(UUID documentId, Map<String, Metadata> metadatas) {
        metadataStorageInMemory.setMetadata(documentId,metadatas);
    }

    @Override
    public Map<String, Metadata> getMetadata(UUID documentId) {
        return metadataStorageInMemory.getMetadata(documentId);
    }

    @Override
    public List<UUID> search(Map<String, Metadata> metadatas) {
        return metadataStorageInMemory.search(metadatas);
    }

    @Override
    public List<UUID> searchForMetadata(List<String> metadataKey) {
        return metadataStorageInMemory.searchForMetadata(metadataKey);
    }

}
