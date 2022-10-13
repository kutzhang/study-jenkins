package com.sanlea.study.jenkins.support.api;

import lombok.*;

/**
 * Endpoint response
 *
 * @param <T> data type
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EndpointResponse<T> {

    // success code
    public static final int CODE_SUCCESS = 0;

    // code
    private int code;

    // error
    private String error;

    // data
    private T data;
}
