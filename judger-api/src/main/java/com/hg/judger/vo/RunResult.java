package com.hg.judger.vo;

public class RunResult {
    private String source;
    private Long realTime;
    private Long memory;
    private Integer signal;
    private Integer exitCode;
    private Integer error;
    private Integer result;

    public RunResult() {
    }

    public RunResult(String source, Long realTime, Long memory, Integer signal, Integer exitCode, Integer error, Integer result) {
        this.source = source;
        this.realTime = realTime;
        this.memory = memory;
        this.signal = signal;
        this.exitCode = exitCode;
        this.error = error;
        this.result = result;
    }

    public String getSource() {
        return source;
    }

    public Long getRealTime() {
        return realTime;
    }

    public Long getMemory() {
        return memory;
    }

    public Integer getSignal() {
        return signal;
    }

    public Integer getExitCode() {
        return exitCode;
    }

    public Integer getError() {
        return error;
    }

    public Integer getResult() {
        return result;
    }
}
