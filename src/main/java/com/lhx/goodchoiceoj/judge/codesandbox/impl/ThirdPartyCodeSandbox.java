package com.lhx.goodchoiceoj.judge.codesandbox.impl;

import com.lhx.goodchoiceoj.judge.codesandbox.CodeSandbox;
import com.lhx.goodchoiceoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.lhx.goodchoiceoj.judge.codesandbox.model.ExecuteCodeResponse;

public class ThirdPartyCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeRequest) {
        System.out.println("第三方代码沙箱");

        return null;
    }
}
