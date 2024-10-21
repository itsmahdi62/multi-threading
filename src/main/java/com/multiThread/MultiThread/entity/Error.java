package com.multiThread.MultiThread.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Builder
public class Error implements Serializable {
    private String FILE_NAME;
    private Long RECORD_NUMBER;
    private String ERROR_CODE;
    private String ERROR_CLASSIFICATION_NAME;
    private String ERROR_DESCRIPTION;
    private String ERROR_DATE;
}
