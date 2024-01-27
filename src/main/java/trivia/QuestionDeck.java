package trivia;

import java.util.LinkedList;

public class QuestionDeck {

    final LinkedList<String> popQuestions = new LinkedList<>();
    final LinkedList<String> scienceQuestions = new LinkedList<>();
    final LinkedList<String> sportsQuestions = new LinkedList<>();
    final LinkedList<String> rockQuestions = new LinkedList<>();

    public QuestionDeck() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast("Science Question " + i);
            sportsQuestions.addLast("Sports Question " + i);
            rockQuestions.addLast("Rock Question " + i);
        }
    }

    public String fetchQuestion(Category category) {
        return switch (category) {
            case POP -> popQuestions.removeFirst();
            case SCIENCE -> scienceQuestions.removeFirst();
            case SPORTS -> sportsQuestions.removeFirst();
            case ROCK -> rockQuestions.removeFirst();
        };
    }
}
