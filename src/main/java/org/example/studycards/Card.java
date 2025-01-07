package org.example.studycards;

public class Card {
    private String question;
    private String answer;

    public Card(String question, String answer) {
        validateAndSetQuestion(question);
        validateAndSetAnswer(answer);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        validateAndSetQuestion(question);
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        validateAndSetAnswer(answer);
    }

    public void edit(String question, String answer) {
        validateAndSetQuestion(question);
        validateAndSetAnswer(answer);
    }

    private void validateAndSetQuestion(String question) {
        if (question == null || question.trim().isEmpty()) {
            throw new IllegalArgumentException("Question cannot be empty or null");
        }
        this.question = question.trim();
    }

    private void validateAndSetAnswer(String answer) {
        if (answer == null || answer.trim().isEmpty()) {
            throw new IllegalArgumentException("Answer cannot be empty or null");
        }
        this.answer = answer.trim();
    }

    public boolean isCorrectAnswer(String attemptedAnswer) {
        return answer != null && answer.trim().equalsIgnoreCase(attemptedAnswer.trim());
    }

    public boolean containsKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return false;
        }

        String lowercaseKeyword = keyword.toLowerCase().trim();
        return question.toLowerCase().contains(lowercaseKeyword) || answer.toLowerCase().contains(lowercaseKeyword);
    }

    @Override
    public String toString() {
        return "Card{Question='" + question + "'}";
    }
}