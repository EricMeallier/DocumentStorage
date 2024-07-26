package fr.meallier.documentstorage.domain.services.data.storage;

import fr.meallier.documentstorage.domain.services.data.DataStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
@Qualifier("OnFilesystem")
public class DataStorageOnFilesystem implements DataStorage {

    static final private Logger LOGGER = LoggerFactory.getLogger(Class.class.getName());
    @Value("${document.filepath.root}")
    String filePathRoot;

    @Override
    public UUID storeData(byte[] data) {
        UUID id = UUID.randomUUID();
        var filePath = filePathRoot + File.separator + id.toString();

        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            outputStream.write(data);
        } catch (IOException ioException) {
            LOGGER.error("Erreur d'ecriture sur le filesystem $1",filePath, ioException);
        }
        return id;
    }

    @Override
    public byte[] getData(UUID documentId) {
        var filePath = filePathRoot + File.separator + documentId.toString();
        File inputFile = new File(filePath);
        byte[] bytes={};
        try (FileInputStream inputStream = new FileInputStream(inputFile)) {
            bytes = new byte[(int) inputFile.length()];
            int nbCount = inputStream.read(bytes);
            if (bytes.length != nbCount)
                throw new IOException("File not read entirely");
        } catch (IOException ioException) {
            LOGGER.error("Erreur de lecture depuis le filesystem $1",filePath, ioException);
        }
        return bytes;
    }

}
