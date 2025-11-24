package fr.meallier.documentstorage;

import fr.meallier.documentstorage.domain.core.data.filtering.InputDataProcessorConfig;
import fr.meallier.documentstorage.domain.core.data.filtering.OutputDataProcessorConfig;
import fr.meallier.documentstorage.domain.core.metadata.filtering.MetadataProcessorConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({MetadataProcessorConfig.class, InputDataProcessorConfig.class, OutputDataProcessorConfig.class})
public class DocumentstorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentstorageApplication.class, args);
	}

}
