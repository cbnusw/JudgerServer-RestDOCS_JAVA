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


    private String cppCompileCommand;
    private String javaCompileCommand;
    private String pythonCompileCommand;



    private String cRunCommand;
    private String javaRunCommand;
    private String pythonRunCommand;


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

    public void setCppCompileCommand(String cppCompileCommand) {
        this.cppCompileCommand = cppCompileCommand;
    }

    public void setJavaCompileCommand(String javaCompileCommand) {
        this.javaCompileCommand = javaCompileCommand;
    }

    public void setJavaRunCommand(String javaRunCommand) {
        this.javaRunCommand = javaRunCommand;
    }

    public void setPythonCompileCommand(String pythonCompileCommand) {
        this.pythonCompileCommand = pythonCompileCommand;
    }

    public void setPythonRunCommand(String pythonRunCommand) {
        this.pythonRunCommand = pythonRunCommand;
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
    public String getCppCompileCommand() {
        return cppCompileCommand;
    }

    public String getPythonCompileCommand() {
        return pythonCompileCommand;
    }

    public String getJavaCompileCommand() {
        return javaCompileCommand;
    }

    public String getJavaRunCommand() {
        return javaRunCommand;
    }

    public String getPythonRunCommand() {
        return pythonRunCommand;
    }
}
