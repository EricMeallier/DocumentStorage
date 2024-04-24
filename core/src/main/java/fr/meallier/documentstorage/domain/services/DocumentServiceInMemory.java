package fr.meallier.documentstorage.domain.services;

import fr.meallier.documentstorage.domain.services.data.DataStorage;
import fr.meallier.documentstorage.domain.services.filter.MetadataProcessor;
import fr.meallier.documentstorage.domain.services.metadata.MetadataStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Qualifier("InMemory")
public class DocumentServiceInMemory extends DocumentService {

    @Autowired
    public DocumentServiceInMemory(DataStorage dataStorageInMemory, MetadataStorage metadataStorageInMemory, MetadataProcessor metadataProcessor) {
        super(dataStorageInMemory,metadataStorageInMemory,metadataProcessor);
    }

    @Override
    public List<UUID> getAllDocuments() {
        return metadataStorage.getAllDocuments();
    }
}
