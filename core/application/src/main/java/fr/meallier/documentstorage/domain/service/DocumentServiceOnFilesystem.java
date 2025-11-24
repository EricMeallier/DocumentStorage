package fr.meallier.documentstorage.domain.service;

import fr.meallier.documentstorage.domain.core.data.storage.DataStorage;
import fr.meallier.documentstorage.domain.core.data.filtering.InputDataProcessor;
import fr.meallier.documentstorage.domain.core.data.filtering.OutputDataProcessor;
import fr.meallier.documentstorage.domain.core.metadata.storage.MetadataStorage;
import fr.meallier.documentstorage.domain.core.metadata.filtering.MetadataProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Qualifier("OnFilesystem")
public class DocumentServiceOnFilesystem extends DocumentService {

    DataStorage dataStorageOnFilesystem;

    MetadataStorage metadataStorageInMemory;

    @Autowired
    public DocumentServiceOnFilesystem(DataStorage dataStorageOnFilesystem, MetadataStorage metadataStorageInMemory, MetadataProcessor metadataProcessor, InputDataProcessor inputDataProcessor, OutputDataProcessor outputDataProcessor) {
        super(dataStorageOnFilesystem,metadataStorageInMemory,metadataProcessor,inputDataProcessor,outputDataProcessor);
    }

    @Override
    public List<UUID> getAllDocuments() {
        // TODO
        throw new UnsupportedOperationException("Not implemented");
    }
}
