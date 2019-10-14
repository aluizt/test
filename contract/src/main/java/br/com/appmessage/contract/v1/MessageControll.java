package br.com.appmessage.contract.v1;

import br.com.appmessage.contract.v1.facade.MessageFacadeContract;
import br.com.appmessage.contract.v1.model.request.MessageRequest;
import br.com.appmessage.contract.v1.model.response.MessageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Api(tags = {"App Message v1"})
@AllArgsConstructor
@RestController
@RequestMapping("/app-message/v1")
public class MessageControll {

    private final MessageFacadeContract sendFacadeContract;

    @PostMapping(value = "/send")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Send message")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Message sent successfully.")})
    public ResponseEntity sendMessage(@RequestBody MessageRequest messageRequest) {
        sendFacadeContract.sendMessage(messageRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/resubmit")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Resubmit message")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Message resubmit successfully.")})
    public ResponseEntity resubmitMessage(@NotNull @RequestParam(value = "messageId", required = true) String messageId) {
        sendFacadeContract.resubmitMessage(messageId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "List all message")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully listed messages."),
            @ApiResponse(code = 404, message = "No messages to list")})
    public List<MessageResponse> findAllMessage() {
        return sendFacadeContract.findAllMessage();
    }

    @GetMapping(value = "/list/message/{messageId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("List messages by Id!")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully.")})
    public ResponseEntity<MessageResponse> findByMessageId(@NotNull @PathVariable(value = "messageId") String messageId) {
        return new ResponseEntity<>(sendFacadeContract.findByMessageId(messageId), HttpStatus.OK);
    }

    @GetMapping(value = "/list/subsidiary/{subsidiaryId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("List messages by subsidiary!")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully listed messages.")})
    public ResponseEntity<List<MessageResponse>> findBySubsidiary(@NotNull @PathVariable(value = "subsidiaryId") long subsidiaryId) {
        return new ResponseEntity<>(sendFacadeContract.findBySubsidiary(subsidiaryId), HttpStatus.OK);
    }
}
