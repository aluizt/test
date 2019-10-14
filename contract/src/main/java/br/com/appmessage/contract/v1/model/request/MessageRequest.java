package br.com.appmessage.contract.v1.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {
    private Long subsidiaryId;
    private String groupId;
    private Long userId;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate creationDate;
    private String resume;
    private String text;
}
