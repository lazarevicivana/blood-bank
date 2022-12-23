package ftn.uns.ac.rs.bloodbank.examination.controller;

import ftn.uns.ac.rs.bloodbank.examination.dto.ExaminationDto;
import ftn.uns.ac.rs.bloodbank.examination.service.ExaminationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/examination")
@AllArgsConstructor
public class ExaminationController {
    private final ExaminationService examinationService;
    @PostMapping()
    @PreAuthorize("hasAnyRole('ROLE_CENTER_ADMIN')")
    public ResponseEntity<?> createCenter(@RequestBody ExaminationDto examinationDto){
        examinationService.createExamination(examinationDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
