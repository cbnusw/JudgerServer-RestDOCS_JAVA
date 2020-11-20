package com.hg.judger.service;

import com.hg.judger.vo.ScoringResult;
import com.hg.judger.vo.SubmissionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class JudgeServiceTest {
    @Autowired
    private JudgeService judgeService;

    @Test
    void run() throws Exception {
        String code = "#include<stdio.h>\n" +
                "int main(){\n" +
                "\tint a, b;\n" +
                "\tscanf(\"%d %d\", &a, &b);\n" +
                "\tprintf(\"%d\", a+b);\n" +
                "\treturn 0;\n" +
                "}";
        SubmissionInfo submissionInfo = new SubmissionInfo(code, "c", "1 2", "3");
        ScoringResult scoringResult = judgeService.run(submissionInfo);
        assertThat(scoringResult.getScoringCode()).contains("CORRECT");
    }
}