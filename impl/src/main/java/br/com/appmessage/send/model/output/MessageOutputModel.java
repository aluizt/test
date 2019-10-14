package br.com.appmessage.send.model.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class MessageOutputModel {

    private String mongoId;
    private Long subsidiaryId;
    private List<String> messageStatus;
    private String groupId;
    private Long userId;
    private LocalDate creationDate;
    private String resume;
    private String text;
    private Boolean sent;

}
