package com.example.gkm;

public class Questions {

    private String questionText;
    private String choise_a;
    private String choise_b;
    private String choise_c;
    private String choise_d;
    private String choise_e;
    private String correctAnswer;

    public Questions() {
    }

    public Questions(String questionText, String choise_a, String choise_b, String choise_c, String choise_d, String choise_e, String correctAnswer) {
        this.questionText = questionText;
        this.choise_a = choise_a;
        this.choise_b = choise_b;
        this.choise_c = choise_c;
        this.choise_d = choise_d;
        this.choise_e = choise_e;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getChoise_a() {
        return choise_a;
    }

    public void setChoise_a(String choise_a) {
        this.choise_a = choise_a;
    }

    public String getChoise_b() {
        return choise_b;
    }

    public void setChoise_b(String choise_b) {
        this.choise_b = choise_b;
    }

    public String getChoise_c() {
        return choise_c;
    }

    public void setChoise_c(String choise_c) {
        this.choise_c = choise_c;
    }

    public String getChoise_d() {
        return choise_d;
    }

    public void setChoise_d(String choise_d) {
        this.choise_d = choise_d;
    }

    public String getChoise_e() {
        return choise_e;
    }

    public void setChoise_e(String choise_e) {
        this.choise_e = choise_e;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public boolean isCorrectAnswer(String selectedAnswerText)
    {
        return selectedAnswerText.matches(correctAnswer);
    }
}
