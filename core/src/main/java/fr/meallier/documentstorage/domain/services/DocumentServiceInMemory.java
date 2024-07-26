package fr.meallier.documentstorage.domain.services;

import fr.meallier.documentstorage.domain.services.data.DataStorage;
import fr.meallier.documentstorage.domain.services.data.filtering.InputDataProcessor;
import fr.meallier.documentstorage.domain.services.data.filtering.OutputDataProcessor;
import fr.meallier.documentstorage.domain.services.metadata.filtering.MetadataProcessor;
import fr.meallier.documentstorage.domain.services.metadata.MetadataStorage;
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
