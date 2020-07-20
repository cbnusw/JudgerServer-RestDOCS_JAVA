package com.hg.judger.vo;

public class ScoringResult {
    private String scoringCode;

    public ScoringResult(String scoringCode) {
        this.scoringCode = scoringCode;
    }

    public ScoringResult() {
    }

    public String getScoringCode() {
        return scoringCode;
    }

    @Override
    public String toString() {
        return "ScoringResult{" +
                "scoringCode='" + scoringCode + '\'' +
                '}';
    }
}
