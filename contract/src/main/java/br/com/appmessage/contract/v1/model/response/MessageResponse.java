package br.com.appmessage.contract.v1.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageResponse {

    @ApiModelProperty(value = "Mongo Id..", example = "5d9e0d20ac11b539ee661e54")
    private String mongoId;
    @ApiModelProperty(value = "Subisidiary Id..", example = "1001")
    private Long subsidiaryId;
    @ApiModelProperty(value = "Message Status.", example = "new")
    private List<String> messageStatus;
    @ApiModelProperty(value = "Group Id.", example = "P101")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String groupId;
    @ApiModelProperty(value = "User Id.", example = "8875")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long userId;
    @ApiModelProperty(value = "Creation Date.", example = "07/10/2019")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate creationDate;
    @ApiModelProperty(value = "Text Summary", example = "Conforme novo programa de pontos ...")
    private String resume;
    @ApiModelProperty(value = "Message Text.", example = "Conforme novo programa de pontos, " +
            "todas as compras realizadas no dia 07/10.")
    private String text;
}
