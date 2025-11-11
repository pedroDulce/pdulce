package com.example.qaassistant.controller;

import com.example.qaassistant.model.Aplicacion;
import com.example.qaassistant.repository.AplicacionRepository;
import com.example.qaassistant.service.QARAGService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/qa-assistant")
@CrossOrigin(origins = "http://localhost:4200")
public class QAAssistantController {

    private final QARAGService qaRAGService;
    private final AplicacionRepository aplicacionRepository;

    public QAAssistantController(QARAGService qaRAGService, AplicacionRepository aplicacionRepository) {
        this.qaRAGService = qaRAGService;
        this.aplicacionRepository = aplicacionRepository;
    }

    @PostMapping("/chat")
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request) {
        ChatResponse response = qaRAGService.processQuestion(request.question());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<RankingDTO>> getRanking() {
        List<Object[]> results = aplicacionRepository.findRankingCobertura();
        List<RankingDTO> ranking = results.stream()
                .map(result -> new RankingDTO(
                        (Aplicacion) result[0],
                        ((Double) result[1]).floatValue()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(ranking);
    }
}

// DTOs
record ChatRequest(String question) {}

record RankingDTO(Aplicacion aplicacion, Float cobertura) {}

