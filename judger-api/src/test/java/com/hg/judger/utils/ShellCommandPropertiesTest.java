package com.hg.judger.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ShellCommandPropertiesTest {

    @Autowired
    private ShellCommandProperties shellCommandProperties;

    @Test
    @DisplayName("Properties 바인딩 테스트 - testDir")
    void getTestDir() {
        assertThat(shellCommandProperties.getTesterDir()).isNotNull();
    }

    @Test
    @DisplayName("Properties 바인딩 테스트 - dockerDir")
     void getDockerDir() {
        assertThat(shellCommandProperties.getDockerDir()).isNotNull();
    }

    @Test
    @DisplayName("Properties 바인딩 테스트 - localInitCommand")
    void getLocalInitCommand() {
        assertThat(shellCommandProperties.getLocalInitCommand()).isNotNull();
    }

    @Test
    @DisplayName("Properties 바인딩 테스트 - cCompileCommand")
    void getCCompileCommand() {
        assertThat(shellCommandProperties.getcCompileCommand()).isNotNull();
    }

    @Test
    @DisplayName("Properties 바인딩 테스트 - cRunCommand")
    void getCRunCommand() {
        assertThat(shellCommandProperties.getcRunCommand()).isNotNull();
    }
}