package fr.meallier.documentstorage;

import fr.meallier.documentstorage.domain.services.filter.MetadataProcessorConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(MetadataProcessorConfig.class)
public class DocumentstorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentstorageApplication.class, args);
	}

}
