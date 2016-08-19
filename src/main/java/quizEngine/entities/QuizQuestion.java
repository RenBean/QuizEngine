package quizEngine.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import quizEngine.enums.Category;

import java.util.ArrayList;

@Entity
@Table(name = "quiz_question")
public class QuizQuestion {
    //a line of code breaks out of its class,... where did it go?
//    public static enum Category { ALL, CORE_JAVA, JAVA_IO, SQL, JAVA_DATABASE, SERVLET, JSP, HTML, CSS, SPRING, HIBERNATE, ANDROID };
    public static enum QuestionType { ALL, MULTIPLE_CHOICE, TRUE_FALSE, CODE };
    public static enum Difficulty { ALL, EASY, AVERAGE, DIFFICULT, CRAZY_HARD };
    public static enum QuizType { REGULAR, FLASH_CARD };
    public static enum QuizSize { SMALL, MEDIUM, LARGE, ALL };
    //looks like there is an enum that's not being used yet

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private Category category;

    @NotNull
    private QuestionType questionType;

    @NotNull
    private Difficulty difficulty;

    private String question;
    private String correctMultipleChoiceAnswer;
    private String wrongMultipleChoiceAnswer1;
    private String wrongMultipleChoiceAnswer2;
    private String wrongMultipleChoiceAnswer3;
    private boolean trueOrFalse;
    private ArrayList<String> codeLines;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectMultipleChoiceAnswer() {
        return correctMultipleChoiceAnswer;
    }

    public void setCorrectMultipleChoiceAnswer(String correctMultipleChoiceAnswer) {
        this.correctMultipleChoiceAnswer = correctMultipleChoiceAnswer;
    }

    public String getWrongMultipleChoiceAnswer1() {
        return wrongMultipleChoiceAnswer1;
    }

    public void setWrongMultipleChoiceAnswer1(String wrongMultipleChoiceAnswer1) {
        this.wrongMultipleChoiceAnswer1 = wrongMultipleChoiceAnswer1;
    }

    public String getWrongMultipleChoiceAnswer2() {
        return wrongMultipleChoiceAnswer2;
    }

    public void setWrongMultipleChoiceAnswer2(String wrongMultipleChoiceAnswer2) {
        this.wrongMultipleChoiceAnswer2 = wrongMultipleChoiceAnswer2;
    }

    public String getWrongMultipleChoiceAnswer3() {
        return wrongMultipleChoiceAnswer3;
    }

    public void setWrongMultipleChoiceAnswer3(String wrongMultipleChoiceAnswer3) {
        this.wrongMultipleChoiceAnswer3 = wrongMultipleChoiceAnswer3;
    }

    public boolean isTrueOrFalse() {
        return trueOrFalse;
    }

    public void setTrueOrFalse(boolean trueOrFalse) {
        this.trueOrFalse = trueOrFalse;
    }

    public ArrayList<String> getCodeLines() {
        return codeLines;
    }

    public void setCodeLines(ArrayList<String> codeLines) {
        this.codeLines = codeLines;
    }
}
