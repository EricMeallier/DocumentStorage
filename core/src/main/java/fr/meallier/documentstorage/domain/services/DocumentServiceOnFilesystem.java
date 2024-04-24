package fr.meallier.documentstorage.domain.services;

import fr.meallier.documentstorage.domain.services.data.DataStorage;
import fr.meallier.documentstorage.domain.services.filter.MetadataProcessor;
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
    public DocumentServiceOnFilesystem(DataStorage dataStorageOnFilesystem, MetadataStorage metadataStorageInMemory, MetadataProcessor metadataProcessor) {
        super(dataStorageOnFilesystem,metadataStorageInMemory,metadataProcessor);
    }

    @Override
    public List<UUID> getAllDocuments() {
        // TODO
        throw new UnsupportedOperationException("Not implemented");
    }
}
