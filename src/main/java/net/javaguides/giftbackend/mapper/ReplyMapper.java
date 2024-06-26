package net.javaguides.giftbackend.mapper;

import net.javaguides.giftbackend.dto.ReplyDto;
import net.javaguides.giftbackend.entity.Reply;

public class ReplyMapper {

    public static ReplyDto mapToReplyDto(Reply reply){
        return new ReplyDto(
                reply.getId(),
                reply.getReview(),
                reply.getContent(),
                reply.isStatus(),
                reply.getUsers(),
                reply.getCreatedDate(),
                reply.getIp()
        );
    }
    public static Reply mapToReply(ReplyDto replyDto){
        return new Reply(
                replyDto.getId(),
                replyDto.getReview(),
                replyDto.getContent(),
                replyDto.isStatus(),
                replyDto.getUsers(),
                replyDto.getCreatedDate(),
                replyDto.getIp()
        );
    }

}
