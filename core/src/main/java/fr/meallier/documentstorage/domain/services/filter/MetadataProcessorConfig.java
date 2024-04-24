package fr.meallier.documentstorage.domain.services.filter;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "document.processor")
public record MetadataProcessorConfig(List<String> filterNames) {
}
