package fr.meallier.documentstorage.domain.services;

import fr.meallier.documentstorage.domain.core.Metadata;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DocumentServiceImpl implements DocumentService {

    static private final Map<UUID, byte[]> DATA = new HashMap<>();
    static private final Map<UUID, Map<String, Metadata>> METADATAS = new HashMap<>();

    @Override
    public UUID storeData(byte[] data) {
        UUID id = UUID.randomUUID();
        DATA.put(id, data);
        return id;
    }

    @Override
    public UUID storeData(byte[] data, Map<String, Metadata> metadatas) {
        UUID id = UUID.randomUUID();
        DATA.put(id, data);
        METADATAS.put(id, metadatas);
        return id;
    }

    @Override
    public byte[] getData(UUID documentId) {
        return DATA.get(documentId);
    }

    @Override
    public void addMetadata(UUID documentId, Map<String, Metadata> metadatas) {
        if (metadatas != null && !metadatas.isEmpty()) {
            if (!METADATAS.containsKey(documentId))
                METADATAS.put(documentId,new HashMap<>());
            metadatas.forEach(METADATAS.get(documentId)::put);
        }
    }

    @Override
    public void setMetadata(UUID documentId, Map<String, Metadata> metadatas) {
        if (METADATAS.get(documentId) != null && !METADATAS.get(documentId).isEmpty())
            METADATAS.get(documentId).clear();
        addMetadata(documentId,metadatas);
    }

    @Override
    public Map<String, Metadata> getMetadata(UUID documentId) {
        return METADATAS.get(documentId);
    }

    @Override
    public List<UUID> search(Map<String, Metadata> metadatas) {
        List<UUID> result = new ArrayList<>();

        metadatas.forEach((k, v) -> result.addAll(searchUnique(v)));
        return result;
    }

    private List<UUID> searchUnique(Metadata metadata) {
        List<UUID> result = new ArrayList<>();

        if (metadata != null && metadata.key() != null) {
            METADATAS.forEach((k, v) -> v.forEach((k2, v2) -> {
                if (k2.equals(metadata.key()) && v2.values().equals(metadata.values())) result.add(k);
            }));
        }

        return result;
    }
}
