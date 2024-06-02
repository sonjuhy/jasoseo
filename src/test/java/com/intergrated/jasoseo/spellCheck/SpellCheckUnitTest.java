package com.intergrated.jasoseo.spellCheck;

import com.intergrated.jasoseo.api.dto.SpellCheckDto;
import com.intergrated.jasoseo.api.service.SpellCheckServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.system.OutputCaptureRule;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class SpellCheckUnitTest {

    @Rule
    public OutputCaptureRule outputCaptureRule = new OutputCaptureRule();

    @DisplayName("CheckSpellAboutMSG")
    @Test
    public void checkSpell(){
        // Given
        String msg = "외않대";
        SpellCheckServiceImpl spellCheckService = new SpellCheckServiceImpl();

        // When
        SpellCheckDto checkedMsg = spellCheckService.check(msg);

        // Then
        assertThat(outputCaptureRule.toString().contains("test"));
        assertNotNull(checkedMsg);

    }

    @DisplayName("GetPassportKey")
    @Test
    public void getPassportKey(){
        SpellCheckServiceImpl spellCheckService = new SpellCheckServiceImpl();

        String key = spellCheckService.getPassportKey();
        System.out.println(key);

        assertNotNull(key);
    }
}
