package fr.meallier.documentstorage.domain.service;

import fr.meallier.documentstorage.domain.core.data.storage.DataStorage;
import fr.meallier.documentstorage.domain.core.data.filtering.InputDataProcessor;
import fr.meallier.documentstorage.domain.core.data.filtering.OutputDataProcessor;
import fr.meallier.documentstorage.domain.core.metadata.storage.MetadataStorage;
import fr.meallier.documentstorage.domain.core.metadata.filtering.MetadataProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Qualifier("InMemory")
public class DocumentServiceInMemory extends DocumentService {

    @Autowired
    public DocumentServiceInMemory(DataStorage dataStorageInMemory, MetadataStorage metadataStorageInMemory, MetadataProcessor metadataProcessor, InputDataProcessor inputDataProcessor, OutputDataProcessor outputDataProcessor) {
        super(dataStorageInMemory,metadataStorageInMemory,metadataProcessor,inputDataProcessor,outputDataProcessor);
    }

    @Override
    public List<UUID> getAllDocuments() {
        return metadataStorage.getAllDocuments();
    }
}
