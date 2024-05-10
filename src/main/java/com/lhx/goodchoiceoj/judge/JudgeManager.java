package com.lhx.goodchoiceoj.judge;


import com.lhx.goodchoiceoj.judge.strategy.DefaultJudgeStrategy;
import com.lhx.goodchoiceoj.judge.strategy.JavaLanguageJudgeStrategy;
import com.lhx.goodchoiceoj.judge.strategy.JudgeContext;
import com.lhx.goodchoiceoj.judge.strategy.JudgeStrategy;
import com.lhx.goodchoiceoj.judge.codesandbox.model.JudgeInfo;
import com.lhx.goodchoiceoj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}
