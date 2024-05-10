package com.lhx.goodchoiceoj.judge.codesandbox;

import com.lhx.goodchoiceoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.lhx.goodchoiceoj.judge.codesandbox.model.ExecuteCodeResponse;

public interface CodeSandbox {

    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeRequest);

}
