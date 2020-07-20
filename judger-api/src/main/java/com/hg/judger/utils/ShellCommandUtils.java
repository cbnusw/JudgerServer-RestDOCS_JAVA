package com.hg.judger.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShellCommandUtils {
    private static final String BIN_SH = "/bin/sh";
    private static final String BIN_SH_C_FLAG = "-c";
    private static final Logger logger = LoggerFactory.getLogger(ShellCommandUtils.class);

    public static void execCommand(String command) {
        Process process = null;
        Runtime runtime = Runtime.getRuntime();
        StringBuilder successOutput = new StringBuilder(); // 성공 스트링 버퍼
        StringBuilder errorOutput = new StringBuilder(); // 오류 스트링 버퍼
        BufferedReader successBufferReader = null; // 성공 버퍼

        BufferedReader errorBufferReader = null; // 오류 버퍼

        String[] commands = {BIN_SH, BIN_SH_C_FLAG, command};
        logger.info("command : {}", command);

        try {
            // 명령어 실행
            process = runtime.exec(commands);

            // shell 실행이 정상 동작했을 경우
            successBufferReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "EUC-KR"));

            // 메시지
            String msg = null;
            while ((msg = successBufferReader.readLine()) != null) {
                successOutput.append(msg)
                        .append(System.getProperty("line.separator"));
            }

            // shell 실행시 에러가 발생했을 경우
            errorBufferReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), "EUC-KR"));
            while ((msg = errorBufferReader.readLine()) != null) {
                errorOutput.append(msg)
                        .append(System.getProperty("line.separator"));
            }

            // 프로세스의 수행이 끝날때까지 대기
            process.waitFor();

            // shell 실행이 정상 종료되었을 경우
            if (process.exitValue() == 0) {
                logger.info("success");
                logger.info("success output : {}", successOutput.toString());
            } else {
                // shell 실행이 비정상 종료되었을 경우
                logger.info("abnormal termination");
                logger.info("abnormal termination OUTPUT : {}", errorOutput.toString());
            }

            // shell 실행시 에러가 발생
            if (!errorOutput.toString().isEmpty()) {
                // shell 실행이 비정상 종료되었을 경우
                logger.info("error");
                logger.info("error output : {}", errorOutput.toString());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (process != null) process.destroy();
                if (successBufferReader != null) successBufferReader.close();
                if (errorBufferReader != null) errorBufferReader.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}