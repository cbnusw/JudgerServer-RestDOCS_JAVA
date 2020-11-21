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
        }
        else if(language.equals("c++")){
            ShellCommandUtils.execCommand(shellCommandProperties.getCppCompileCommand());
        }

        ShellCommandUtils.execCommand(shellCommandProperties.getcRunCommand());

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