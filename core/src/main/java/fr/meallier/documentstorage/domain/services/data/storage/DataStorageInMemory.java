package fr.meallier.documentstorage.domain.services.data.storage;

import fr.meallier.documentstorage.domain.services.data.DataStorage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Qualifier("InMemory")
public class DataStorageInMemory implements DataStorage {

    static private final Map<UUID, byte[]> DATA = new HashMap<>();
    @Override
    public UUID storeData(byte[] data) {
        UUID id = UUID.randomUUID();
        DATA.put(id, data);
        return id;
    }

    @Override
    public byte[] getData(UUID documentId) {
        return DATA.get(documentId).clone();
    }
}
