package br.com.appmessage.send.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class StandardError implements Serializable {

    private String name;
    private Integer status;
    private Issue issues;
    private String suggestedApplicationActions;
    private String suggestedUserActions;

}
