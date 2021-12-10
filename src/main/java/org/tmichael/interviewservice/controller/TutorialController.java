package org.tmichael.interviewservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tmichael.interviewservice.dao.TutorialDao;
import org.tmichael.interviewservice.model.Tutorial;
import org.tmichael.interviewservice.service.TutorialService;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorial(@PathVariable("id") long id) {
        ResponseEntity<Tutorial> response;
        try {
            Optional<Tutorial> tutorial = tutorialService.getTutorialById(id);
            response = tutorial.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(tutorial.get(), HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @GetMapping("tutorials/published")
    public ResponseEntity<List<Tutorial>> getPublishedTutorials() {
        ResponseEntity<List<Tutorial>> response;
        try {
            List<Tutorial> tutorials = tutorialService.getPublishedTutorials();
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
            Optional<Tutorial> tutorial = tutorialService.updateTutorial(id, dao);
            response = tutorial.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(tutorial.get(), HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @DeleteMapping("tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        ResponseEntity<HttpStatus> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        try {
            tutorialService.deleteTutorial(id);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @DeleteMapping("tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        ResponseEntity<HttpStatus> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        try {
            tutorialService.deleteAllTutorials();
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
}
