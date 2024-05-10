package com.lhx.goodchoiceoj.judge.codesandbox;

import com.lhx.goodchoiceoj.judge.codesandbox.impl.ExampleCodeSandbox;
import com.lhx.goodchoiceoj.judge.codesandbox.impl.RemoteSandbox;
import com.lhx.goodchoiceoj.judge.codesandbox.impl.ThirdPartyCodeSandbox;

public class CodeSandboxFactory {

    public static CodeSandbox newInstance(String type) {
        switch (type) {
            case "example":
                return new ExampleCodeSandbox();
            case "remote":
                return new RemoteSandbox();
            case "thirdParty":
                return new ThirdPartyCodeSandbox();
            default:
                return new ExampleCodeSandbox();
        }
    }
}
