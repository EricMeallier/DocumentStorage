package fr.meallier.documentstorage.service;

import fr.meallier.documentstorage.domain.core.Metadata;
import fr.meallier.documentstorage.domain.services.DocumentService;
import fr.meallier.documentstorage.domain.services.filter.DocumentSizeFilter;
import fr.meallier.documentstorage.domain.services.filter.MetadataFilter;
import fr.meallier.documentstorage.domain.services.filter.MetadataProcessor;
import org.springframework.stereotype.Component;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class DocumentManagement {

    private final DocumentService documentService;

    public DocumentManagement(DocumentService documentServiceInMemory) {
        this.documentService = documentServiceInMemory;
    }

    public void storeFile(File file) throws Exception {

        byte[] bytes = null;

        DataInputStream reader = new DataInputStream(new FileInputStream(file));
        int nBytesToRead = reader.available();
        if(nBytesToRead > 0) {
            bytes = new byte[nBytesToRead];
            int red = reader.read(bytes);
            if (red != nBytesToRead)
                throw new Exception("File not read entirely");
        }

        UUID id = documentService.storeData(bytes);
        documentService.addMetadata(id,new Metadata("Name", file.getName()));
    }

    public String exportAll() {
        return documentService.exportAll();
    }
}
