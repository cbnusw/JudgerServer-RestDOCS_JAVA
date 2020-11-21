package com.hg.judger.service;

import com.hg.judger.utils.ShellCommandProperties;
import com.hg.judger.utils.ShellCommandUtils;
import com.hg.judger.vo.ScoringResult;
import com.hg.judger.vo.SubmissionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class JudgeService {

    @Autowired
    private final ShellCommandProperties shellCommandProperties;

    JudgeService(ShellCommandProperties shellCommandProperties) {
        this.shellCommandProperties = shellCommandProperties;
    }

    public ScoringResult run(SubmissionInfo submissionInfo) throws IOException {
        ShellCommandUtils.isCompileError=false;
        ShellCommandUtils.execCommand(shellCommandProperties.getLocalInitCommand());

        String language=submissionInfo.getLanguage();

        createInputFile(submissionInfo.getInput());
        createSourceFile(submissionInfo.getSource(),language);

        if(language.equals("c")) {
            ShellCommandUtils.execCommand(shellCommandProperties.getcCompileCommand());
            ShellCommandUtils.execCommand(shellCommandProperties.getcRunCommand());
        }
        else if(language.equals("c++")){
            ShellCommandUtils.execCommand(shellCommandProperties.getCppCompileCommand());
            ShellCommandUtils.execCommand(shellCommandProperties.getcRunCommand());
        }
        else if(language.equals("java")){
            ShellCommandUtils.execCommand(shellCommandProperties.getJavaCompileCommand());
            ShellCommandUtils.execCommand(shellCommandProperties.getJavaRunCommand());
        }
        else if(language.equals("python")){
            ShellCommandUtils.execCommand(shellCommandProperties.getPythonCompileCommand());
            String test=shellCommandProperties.getPythonCompileCommand();
            System.out.println(test);
            ShellCommandUtils.execCommand(shellCommandProperties.getPythonRunCommand());
        }




        if(ShellCommandUtils.isCompileError==true)
            return new ScoringResult("Compile Error");
        else if(ShellCommandUtils.isRuntimeError==true)
            return new ScoringResult("Runtime Error");
        return new ScoringResult(checkAnswer(submissionInfo.getAnswer()));
    }

    private void createInputFile(String input) throws IOException {
        FileWriter fileWriter = new FileWriter(shellCommandProperties.getTesterDir() + "/input.txt");
        fileWriter.write(input);
        fileWriter.close();
    }

    private void createSourceFile(String source, String language) throws IOException {
        FileWriter fileWriter=null;
        if(language.equals("c")){
            fileWriter= new FileWriter(shellCommandProperties.getTesterDir() + "/test.c");
        }else if(language.equals("c++")){
            fileWriter= new FileWriter(shellCommandProperties.getTesterDir() + "/test.cc");
        }else if(language.equals("java")){
            fileWriter= new FileWriter(shellCommandProperties.getTesterDir() + "/test.java");
        }else if(language.equals("python")){
            fileWriter= new FileWriter(shellCommandProperties.getTesterDir() + "/test.py");
        }

        fileWriter.write(source);
        fileWriter.close();
    }

    private String checkAnswer(String answer) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(shellCommandProperties.getTesterDir() + "/output.txt"));
        String output = "";
        String temp;
        while ((temp = br.readLine()) != null) output += temp;

        System.out.println("Testcase Answer: "+ answer);
        System.out.println("Compile Answer: "+ output);
        if (output.equals(answer)) {
            return "CORRECT ANSWER !!";
        }
        return "WRONG ANSWER !!";
    }
}