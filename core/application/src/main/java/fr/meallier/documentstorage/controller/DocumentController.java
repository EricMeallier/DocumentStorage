package fr.meallier.documentstorage.controller;

import fr.meallier.documentstorage.domain.service.DocumentService;
import netscape.javascript.JSObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
public class DocumentController {

    public DocumentController(DocumentService documentServiceInMemory) {
        this.documentService = documentServiceInMemory;
    }

    private final DocumentService documentService;

    @GetMapping
    public ResponseEntity<List<UUID>> getAllDocument() {
        List<UUID> result = documentService.getAllDocuments();
        if (result.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UUID> putDocument(@RequestParam("file") MultipartFile file,
                                            RedirectAttributes redirectAttributes) throws IOException {
        UUID id = documentService.storeData(file.getBytes());

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded [" + id.toString() + "] " + file.getOriginalFilename() + " !");

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getDocument(@PathVariable UUID id, RedirectAttributes redirectAttributes) {

        byte[] data = documentService.getData(id);

        if (data.length == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(new String(data), HttpStatus.OK);
    }
}
