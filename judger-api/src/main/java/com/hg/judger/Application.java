package com.hg.judger;

import com.hg.judger.utils.ShellCommandProperties;
import com.hg.judger.vo.SubmissionInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ShellCommandProperties.class)
public class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
