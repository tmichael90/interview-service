package org.tmichael.interviewservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tmichael.interviewservice.dao.TutorialDao;
import org.tmichael.interviewservice.model.Comment;
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

    public List<Tutorial> getAllTutorials(String title, boolean includeComments) {
        List<Tutorial> tutorials = new ArrayList<>();
        if (title != null && includeComments) {
            tutorials.addAll(tutorialRepo.findByTitleContainingAndFetchComments(title));
        } else if (title == null && includeComments) {
            tutorials.addAll(tutorialRepo.findAllAndFetchComments());
        } else if (title != null) {
            tutorials.addAll(tutorialRepo.findByTitleContaining(title));
        } else {
            tutorials.addAll(tutorialRepo.findAll());
        }
        if (!includeComments) {
            tutorials.forEach(t -> t.setComments(new ArrayList<>()));
        }

        return tutorials;
    }

    public List<Tutorial> getPublishedTutorials() {
        return tutorialRepo.findByPublished(true);
    }

    public Optional<Tutorial> getTutorialById(long id) {
        return tutorialRepo.findById(id);
    }

    public Tutorial createTutorial(TutorialDao dao) {
        List<Comment> comments = new ArrayList<>();
        dao.getComments().forEach(c -> comments.add(new Comment(c.getUsername(), c.getComment())));

        return tutorialRepo.save(new Tutorial(dao.getTitle(), dao.getDescription(), dao.isPublished(), comments));
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
