package fr.meallier.documentstorage.domain.services.metadata.filtering;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "document.metadata.processor")
public record MetadataProcessorConfig(List<String> filterNames) {
}
