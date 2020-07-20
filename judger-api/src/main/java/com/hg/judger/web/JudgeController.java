package com.hg.judger.web;

import com.hg.judger.service.JudgeService;
import com.hg.judger.vo.ScoringResult;
import com.hg.judger.vo.SubmissionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/judge")
public class JudgeController {

    private final JudgeService judgeService;

    public JudgeController(JudgeService judgeService) {
        this.judgeService = judgeService;
    }

    private final Logger logger = LoggerFactory.getLogger(JudgeController.class);

    @PostMapping
    ResponseEntity<ScoringResult> grade(@RequestBody SubmissionInfo submissionInfo) throws IOException {
        logger.info("submissionInfo: {}", submissionInfo);
        ScoringResult scoringResult = judgeService.run(submissionInfo);

        logger.info("scoringResult: {}", scoringResult);

        return ResponseEntity.ok().body(scoringResult);
    }
}