package org.tmichael.interviewservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tmichael.interviewservice.dao.TutorialDao;
import org.tmichael.interviewservice.model.Tutorial;
import org.tmichael.interviewservice.repo.TutorialRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TutorialService {

    private final TutorialRepo tutorialRepo;

    @Autowired
    public TutorialService(TutorialRepo tutorialRepo) {
        this.tutorialRepo = tutorialRepo;
    }

    public List<Tutorial> getAllTutorials(String title) {
        return new ArrayList<>(title == null ? tutorialRepo.findAll() : tutorialRepo.findByTitleContaining(title));
    }

    public List<Tutorial> getPublishedTutorials() {
        return tutorialRepo.findByPublished(true);
    }

    public Optional<Tutorial> getTutorialById(long id) {
        return tutorialRepo.findById(id);
    }

    public Tutorial createTutorial(TutorialDao dao) {
        return tutorialRepo.save(new Tutorial(dao.getTitle(), dao.getDescription(), dao.isPublished()));
    }

    public Optional<Tutorial> updateTutorial(long id, TutorialDao dao) {
        Tutorial tutorial = null;
        Optional<Tutorial> tutorialOptional = tutorialRepo.findById(id);
        if (tutorialOptional.isPresent()) {
            tutorial = tutorialOptional.get();
            tutorial.setTitle(dao.getTitle());
            tutorial.setDescription(dao.getDescription());
            tutorial.setPublished(dao.isPublished());
            tutorial = tutorialRepo.save(tutorial);
        }

        return tutorial == null ? Optional.empty() : Optional.of(tutorial);
    }

    public void deleteTutorial(long id) {
        tutorialRepo.deleteById(id);
    }

    public void deleteAllTutorials() {
        tutorialRepo.deleteAll();
    }
}
