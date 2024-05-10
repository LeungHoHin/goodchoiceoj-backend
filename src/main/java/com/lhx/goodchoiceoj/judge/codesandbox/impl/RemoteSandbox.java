package com.lhx.goodchoiceoj.judge.codesandbox.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.lhx.goodchoiceoj.common.ErrorCode;
import com.lhx.goodchoiceoj.exception.BusinessException;
import com.lhx.goodchoiceoj.judge.codesandbox.CodeSandbox;
import com.lhx.goodchoiceoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.lhx.goodchoiceoj.judge.codesandbox.model.ExecuteCodeResponse;

public class RemoteSandbox implements CodeSandbox {


    private static final String AUTH_REQUEST_HEADER = "LiangHaoxuan";

    private static final String AUTH_REQUEST_SECRET = "LeungHohin";

    private static final String REMOTE_SANDBOX_URL = "http://192.168.126.132:8090/executeCode";

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeRequest) {
        System.out.println("远程代码沙箱");
        String json = JSONUtil.toJsonStr(executeRequest);
        String responseStr = HttpUtil.createPost(REMOTE_SANDBOX_URL)
                .header(AUTH_REQUEST_HEADER, AUTH_REQUEST_SECRET)
                .body(json)
                .execute()
                .body();
        if (StrUtil.isBlank(responseStr)) {
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR, "远程代码沙箱执行错误：" + responseStr);
        }
        ExecuteCodeResponse response = JSONUtil.toBean(responseStr, ExecuteCodeResponse.class);
        return response;
    }
}
