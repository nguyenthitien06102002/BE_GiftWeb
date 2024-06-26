package net.javaguides.giftbackend.service;

import net.javaguides.giftbackend.dto.ReplyDto;
import net.javaguides.giftbackend.entity.Reply;
import net.javaguides.giftbackend.entity.Review;

public interface ReplyService {
    ReplyDto createReply(ReplyDto replyDto);

    ReplyDto getReplyByIdReview(Review review);

    ReplyDto updateContent (Long idReview, Reply content);
}
