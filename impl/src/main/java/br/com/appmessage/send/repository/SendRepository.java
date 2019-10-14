package br.com.appmessage.send.repository;

import br.com.appmessage.send.repository.entity.MessageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SendRepository extends MongoRepository<MessageEntity,String > {

    List<MessageEntity> findAllBySubsidiaryId(Long subsidiaryId);

    List<MessageEntity> findAllByGroupId(String groupId);

    List<MessageEntity> findAllByUserId(Long userId);
}
