package com.hg.judger.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "judge.shell.command")
public class ShellCommandProperties {
    private String testerDir;
    private String dockerDir;
    private String localInitCommand;
    private String cCompileCommand;
    private String cRunCommand;

    public void setTesterDir(String testerDir) {
        this.testerDir = testerDir;
    }

    public void setDockerDir(String dockerDir) {
        this.dockerDir = dockerDir;
    }

    public void setLocalInitCommand(String localInitCommand) {
        this.localInitCommand = localInitCommand;
    }

    public void setcCompileCommand(String cCompileCommand) {
        this.cCompileCommand = cCompileCommand;
    }

    public void setcRunCommand(String cRunCommand) {
        this.cRunCommand = cRunCommand;
    }

    public String getTesterDir() {
        return testerDir;
    }

    public String getDockerDir() {
        return dockerDir;
    }

    public String getLocalInitCommand() {
        return localInitCommand;
    }

    public String getcCompileCommand() {
        return cCompileCommand;
    }

    public String getcRunCommand() {
        return cRunCommand;
    }
}
