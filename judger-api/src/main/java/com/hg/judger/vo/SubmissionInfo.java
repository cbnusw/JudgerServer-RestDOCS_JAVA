package com.hg.judger.vo;

public class SubmissionInfo {

    private String source;
    private String language;
    private String input;
    private String answer;

    public SubmissionInfo() {
    }

    public SubmissionInfo(String source, String language, String input, String answer) {
        this.source = source;
        this.language = language;
        this.input = input;
        this.answer = answer;
    }

    public String getSource() {
        return source;
    }

    public String getLanguage() {
        return language;
    }

    public String getInput() {
        return input;
    }

    public String getAnswer() {
        return answer;
    }
}
