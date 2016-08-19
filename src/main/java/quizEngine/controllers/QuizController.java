package quizEngine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import quizEngine.enums.Category;
import quizEngine.entities.QuizQuestion;
import quizEngine.entities.QuizQuestionDAO;
import quizEngine.entities.Tracker;
import quizEngine.entities.TrackerDAO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

@Controller
@RequestMapping(value="/quiz/")
public class QuizController {

    //add TrackerDAO
    private final QuizQuestionDAO quizQuestionDAO;
    private final TrackerDAO trackerDAO;

    @Autowired //this is how String calls its beans so add a Tracker for it to call
    public QuizController(QuizQuestionDAO quizQuestionDAO,TrackerDAO trackerDAO) {
        Assert.notNull(quizQuestionDAO, "QuizQuestionDAO must not be null!");
        Assert.notNull(trackerDAO, "TrackerDAO must not be null!");
        this.quizQuestionDAO = quizQuestionDAO;
        this.trackerDAO = trackerDAO;
    }
    @RequestMapping(value="quizResults")
    public String quizResults (ModelMap model) {
        return "quiz/quizResults";
    }

    @RequestMapping(value="/")
    public String dashboard(ModelMap model) {
        //TODO
        model.addAttribute("categories", Category.values());
        model.addAttribute("QuizTypes", QuizQuestion.QuizType.values());
        model.addAttribute("questionTypes", QuizQuestion.QuestionType.values());
        model.addAttribute("difficulties", QuizQuestion.Difficulty.values());
        model.addAttribute("tracker", new Tracker());
        //this is your mapper the add attribute allows these definitions to be called and linked
        //paired keys and values
        return "quiz/index";
    }

    @RequestMapping(value="startQuiz")
    public View startQuiz(String name, String email, String category, String quizType, String questionType, String difficulty, HttpServletRequest request) {
        request.getSession().setAttribute("name",name);
        request.getSession().setAttribute("email",email);
        request.getSession().setAttribute("category",category);
        request.getSession().setAttribute("quizType",quizType);
        request.getSession().setAttribute("questionType",questionType);
        request.getSession().setAttribute("difficulty",difficulty);
        //found my name and email call above
        //now lets add a Tracker tag set below
        Tracker tracker = new Tracker();
        tracker.setEmail(email);
        tracker.setName(name);

        //don't forget to save it and log it as a session

        Iterable<QuizQuestion> quizQuestions = null;
        int numberOfQuestions = 0;

        // category!=ALL && questionType==ALL && difficulty==ALL
        if(!category.equals(Category.ALL) && questionType.equals(QuizQuestion.QuestionType.ALL) && difficulty.equals(QuizQuestion.Difficulty.ALL)) {
            quizQuestions = quizQuestionDAO.findByCategory(Category.valueOf(category));
        }
        // category!=ALL && questionType!=ALL && difficulty==ALL
        else if(!category.equals(Category.ALL) && !questionType.equals(QuizQuestion.QuestionType.ALL) && difficulty.equals(QuizQuestion.Difficulty.ALL)) {
            quizQuestions = quizQuestionDAO.findByCategoryAndQuestionType(Category.valueOf(category), QuizQuestion.QuestionType.valueOf(questionType));
        }
        // category!=ALL && questionType!=ALL && difficulty!=ALL
        else if(!category.equals(Category.ALL) && !questionType.equals(QuizQuestion.QuestionType.ALL) && !difficulty.equals(QuizQuestion.Difficulty.ALL)) {
            quizQuestions = quizQuestionDAO.findByCategoryAndQuestionTypeAndDifficulty(Category.valueOf(category), QuizQuestion.QuestionType.valueOf(questionType), QuizQuestion.Difficulty.valueOf(difficulty));
        }
        // category==ALL && questionType!=ALL && difficulty==ALL
        else if(category.equals(Category.ALL) && !questionType.equals(QuizQuestion.QuestionType.ALL) && difficulty.equals(QuizQuestion.Difficulty.ALL)) {
            quizQuestions = quizQuestionDAO.findByQuestionType(QuizQuestion.QuestionType.valueOf(questionType));
        }
        // category==ALL && questionType!=ALL && difficulty!=ALL
        else if(category.equals(Category.ALL) && !questionType.equals(QuizQuestion.QuestionType.ALL) && !difficulty.equals(QuizQuestion.Difficulty.ALL)) {
            quizQuestions = quizQuestionDAO.findByQuestionTypeAndDifficulty(QuizQuestion.QuestionType.valueOf(questionType), QuizQuestion.Difficulty.valueOf(difficulty));
        }
        // category==ALL && questionType==ALL && difficulty!=ALL
        else if(category.equals(Category.ALL) && questionType.equals(QuizQuestion.QuestionType.ALL) && !difficulty.equals(QuizQuestion.Difficulty.ALL)) {
            quizQuestions = quizQuestionDAO.findByDifficulty(QuizQuestion.Difficulty.valueOf(difficulty));
        }
        // category!=ALL && questionType==ALL && difficulty!=ALL
        else if(!category.equals(Category.ALL) && questionType.equals(QuizQuestion.QuestionType.ALL) && !difficulty.equals(QuizQuestion.Difficulty.ALL)) {
            quizQuestions = quizQuestionDAO.findByCategoryAndDifficulty(Category.valueOf(category), QuizQuestion.Difficulty.valueOf(difficulty));
        }
        if(quizQuestions != null) {
            numberOfQuestions = countIterable(quizQuestions);
        }
        // category==ALL && questionType==ALL && difficulty==ALL || no results
        if(quizQuestions == null || numberOfQuestions < 1) {
            quizQuestions = quizQuestionDAO.findAll();
            numberOfQuestions = countIterable(quizQuestions);
        }
        int i = 0;
        HashMap<Integer,QuizQuestion> quizQuestionsHashMap = new HashMap<>();
        for(QuizQuestion quizQuestion:quizQuestions) {
            quizQuestionsHashMap.put(i,quizQuestion);
            i++;
        }
        tracker.setNumberOfQuestions(countIterable(quizQuestions));
        trackerDAO.save(tracker);
        request.getSession().setAttribute("tracker", tracker);
        //add the tracker number of questions
        request.getSession().setAttribute("quizQuestionsHashMap",quizQuestionsHashMap);
        ArrayList<Integer> usedQuestions = new ArrayList<>();
        request.getSession().setAttribute("usedQuestions",usedQuestions);

        return new RedirectView("nextQuestion");
    }

    @RequestMapping(value="nextQuestion")
    public String nextQuestion(ModelMap model, HttpServletRequest request) {
        ArrayList<Integer> usedQuestions = (ArrayList<Integer>)request.getSession().getAttribute("usedQuestions");
        HashMap<Integer,QuizQuestion> quizQuestionsHashMap = (HashMap<Integer,QuizQuestion>)request.getSession().getAttribute("quizQuestionsHashMap");
        int numberOfQuestions = quizQuestionsHashMap.size();
        if(usedQuestions.size() >= numberOfQuestions) {
            return "quiz/quizResults";
            //TODO
        }
        boolean isNewQuestion = false;
        int questionNumber = -1;
        while(!isNewQuestion) {
            questionNumber = randomInt(0,numberOfQuestions-1);
            if(!usedQuestions.contains(new Integer(questionNumber))) {
                isNewQuestion = true;
            }
        }
        request.getSession().setAttribute("questionNumber",questionNumber);
        model.addAttribute("quizQuestion",quizQuestionsHashMap.get(questionNumber));
        usedQuestions.add(questionNumber);
        request.getSession().setAttribute("usedQuestions",usedQuestions);

        return "quiz/question";
    }

    @RequestMapping(value="questionAnswer")
    public String questionAnswer(String multiAnswer, String trueFalseAnswer, ModelMap model, HttpServletRequest request) {
        String email = (String)request.getSession().getAttribute("email");
        Tracker tracker = trackerDAO.findByEmail(email);
        //insert String email and tracker object and query by email
        HashMap<Integer,QuizQuestion> quizQuestionsHashMap = (HashMap<Integer,QuizQuestion>)request.getSession().getAttribute("quizQuestionsHashMap");
        int questionNumber = (Integer) request.getSession().getAttribute("questionNumber");
        QuizQuestion quizQuestion = quizQuestionsHashMap.get(questionNumber);
        model.addAttribute("quizQuestion",quizQuestion);
        model.remove("correct");
        model.remove("incorrect");
        //clearing the pipe /removing the cache for key value pairs
        if(quizQuestion.getQuestionType().equals(QuizQuestion.QuestionType.MULTIPLE_CHOICE)) {
            if (multiAnswer != null && multiAnswer.equalsIgnoreCase("yes")) {
                model.addAttribute("correct","GREAT JOB!");
                tracker.setCorrect(tracker.getCorrect()+1);
            } else {
                model.addAttribute("incorrect","SORRY Wrong Answer");
                tracker.setIncorrect(tracker.getIncorrect()+1);
            }
        } else if (quizQuestion.getQuestionType().equals(QuizQuestion.QuestionType.TRUE_FALSE)) {
            if(trueFalseAnswer != null && quizQuestion.isTrueOrFalse() == Boolean.valueOf(trueFalseAnswer)) {
                model.addAttribute("correct","GREAT JOB!");
                tracker.setCorrect(tracker.getCorrect()+1);
            } else {
                model.addAttribute("incorrect","SORRY Wrong Answer");
                tracker.setIncorrect(tracker.getIncorrect()+1);
            }
            //lets add our counters above
        }
        //save and log the session
        trackerDAO.save(tracker);
        request.getSession().setAttribute("tracker", tracker);
        return "quiz/answer";
    }

    private int countIterable(Iterable<?> it) {
        if (it instanceof Collection)
            return ((Collection<?>)it).size();

        // else iterate
        int i = 0;
        for (Object obj : it) i++;
        return i;
    }

    private static int randomInt(int min, int max) {
        Random random = new Random();
        int randomNumber = random.nextInt((max - min) +1) +min;
        return randomNumber;
    }

}
