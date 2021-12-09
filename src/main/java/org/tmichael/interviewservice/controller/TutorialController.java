package org.tmichael.interviewservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tmichael.interviewservice.dao.TutorialDao;
import org.tmichael.interviewservice.model.Tutorial;
import org.tmichael.interviewservice.service.TutorialService;

import java.util.List;

@RestController
@RequestMapping("tutorial")
public class TutorialController {

    private final TutorialService tutorialService;

    @Autowired
    public TutorialController(TutorialService tutorialService) {
        this.tutorialService = tutorialService;
    }

    @GetMapping("tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(value = "title", required = false) String title) {
        ResponseEntity<List<Tutorial>> response;
        try {
            List<Tutorial> tutorials = tutorialService.getAllTutorials(title);
            response = tutorials.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @PostMapping("tutorials")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody TutorialDao dao) {
        ResponseEntity<Tutorial> response;
        try {
            Tutorial tutorial = tutorialService.createTutorial(dao);
            response = new ResponseEntity<>(tutorial, HttpStatus.CREATED);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @PutMapping("tutorials/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody TutorialDao dao) {
        ResponseEntity<Tutorial> response;
        try {
            Tutorial tutorial = tutorialService.updateTutorial(id, dao);
            response = tutorial == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(tutorial, HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
}
