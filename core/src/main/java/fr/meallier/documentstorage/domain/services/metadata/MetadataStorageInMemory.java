package fr.meallier.documentstorage.domain.services.metadata;

import fr.meallier.documentstorage.domain.core.Metadata;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Qualifier("InMemory")
public class MetadataStorageInMemory implements MetadataStorage {

    static private final Map<UUID, Map<String, Metadata>> METADATAS = new HashMap<>();

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

    @Override
    public List<UUID> searchForMetadata(List<String> metadataKeys) {
        List<UUID> result = new ArrayList<>();

        metadataKeys.forEach(k -> result.addAll(searchUniqueForMetadata(k)));
        return result;    }

    @Override
    public Map<String, Metadata> indexDocument(byte[] data) {
        return null;
    }

    @Override
    public void removeMetadata(UUID documentId, List<String> metadataKeys) {
        if (metadataKeys != null && !metadataKeys.isEmpty()) {
            metadataKeys.forEach(s -> {
                METADATAS.get(documentId).remove(s);
            });
        }
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

    private List<UUID> searchUniqueForMetadata(String metadataKey) {
        List<UUID> result = new ArrayList<>();

        if (metadataKey != null && ! metadataKey.isEmpty()) {
            METADATAS.forEach((k, v) -> v.forEach((k2, v2) -> {
                if (k2.equals(metadataKey)) result.add(k);
            }));
        }

        return result;
    }


}
