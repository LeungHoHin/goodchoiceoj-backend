package com.lhx.goodchoiceoj.judge.codesandbox;

import com.lhx.goodchoiceoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.lhx.goodchoiceoj.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@AllArgsConstructor
@Slf4j
public class CodeSandboxProxy implements CodeSandbox {

    private final CodeSandbox codeSandbox;

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeRequest) {
        log.info("代码沙箱请求信息" + executeRequest.toString());
        ExecuteCodeResponse codeResponse = codeSandbox.executeCode(executeRequest);
        log.info("代码沙箱相应信息" + codeResponse.toString());
        return codeResponse;
    }
}
