package br.com.appmessage.send.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "data_message")
public class MessageEntity {

    @Id
    private String mongoId;
    private List<String> messageStatus;
    private Long subsidiaryId;
    private String groupId;
    private Long userId;
    private LocalDate creationDate;
    private String resume;
    private String text;
    private Boolean sent;
}
