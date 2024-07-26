package fr.meallier.documentstorage.domain.services;

import fr.meallier.documentstorage.domain.services.data.DataStorage;
import fr.meallier.documentstorage.domain.services.data.filtering.InputDataProcessor;
import fr.meallier.documentstorage.domain.services.data.filtering.OutputDataProcessor;
import fr.meallier.documentstorage.domain.services.metadata.filtering.MetadataProcessor;
import fr.meallier.documentstorage.domain.services.metadata.MetadataStorage;
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
