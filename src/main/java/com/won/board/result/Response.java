package com.won.board.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@ApiModel("응답 모델")
@Getter @Setter(AccessLevel.PRIVATE)
public class Response<T> {

    @ApiModelProperty(value = "응답 코드", position = 1)
    private int resultCode = 200;

    @ApiModelProperty(value = "응답 메세지", position = 2)
    private String resultMsg = "";

    @ApiModelProperty(value = "응답 데이터", position = 3)
    private T resultData;


    //===== 생성 메서드 =====//

    public static Response<?> of(int resultCode, String resultMsg) {
        Response<?> response = new Response<>();
        response.setResultCode(resultCode);
        response.setResultMsg(resultMsg);
        return response;
    }

    public static Response<Object> of(int resultCode, String resultMsg, Object resultData) {
        Response<Object> response = new Response<>();
        response.setResultCode(resultCode);
        response.setResultMsg(resultMsg);
        response.setResultData(resultData);
        return response;
    }

    public static <T> Response<T> from(T resultData) {
        Response<T> response = new Response<>();
        response.setResultCode(200);
        response.setResultMsg("");
        response.setResultData(resultData);
        return response;
    }

    public static Response<?> from(int resultCode) {
        return of(resultCode, "");
    }

}
