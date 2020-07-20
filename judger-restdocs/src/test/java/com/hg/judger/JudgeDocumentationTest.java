package com.hg.judger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hg.judger.service.JudgeService;
import com.hg.judger.vo.ScoringResult;
import com.hg.judger.vo.SubmissionInfo;
import com.hg.judger.web.JudgeController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.hg.judger.ApiDocumentUtils.getDocumentRequest;
import static com.hg.judger.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(JudgeController.class)
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "docs.api.com")
public class JudgeDocumentationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JudgeService judgeService;

    @Test
    void grade() throws Exception {
        //given
        String code = "#include<stdio.h>\n" +
                "int main(){\n" +
                "\tint a, b;\n" +
                "\tscanf(\"%d %d\", &a, &b);\n" +
                "\tprintf(\"%d\", a+b);\n" +
                "\treturn 0;\n" +
                "}";

        SubmissionInfo submissionInfo = new SubmissionInfo(code, "c", "1 2", "3");
        ScoringResult scoringResult = new ScoringResult("CORRECT ANSWER !!");

        given(judgeService.run(any(SubmissionInfo.class))).willReturn(scoringResult);

        //when
        ResultActions result = mockMvc.perform(
                post("/api/judge")
                        .content(objectMapper.writeValueAsString(submissionInfo))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        //then
        result.andExpect(status().isOk())
                .andDo(document("judge-grade",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("source").type(JsonFieldType.STRING).description("소스코드"),
                                fieldWithPath("language").type(JsonFieldType.STRING).description("언어"),
                                fieldWithPath("input").type(JsonFieldType.STRING).description("입력값"),
                                fieldWithPath("answer").type(JsonFieldType.STRING).description("정답")
                        ),
                        responseFields(
                                fieldWithPath("scoringCode").type(JsonFieldType.STRING).description("채점코드")
                        )
                ));
    }
}
