package net.javaguides.giftbackend.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.dto.ReplyDto;
import net.javaguides.giftbackend.entity.Reply;
import net.javaguides.giftbackend.entity.Review;
import net.javaguides.giftbackend.entity.Users;
import net.javaguides.giftbackend.mapper.ReplyMapper;
import net.javaguides.giftbackend.repository.ReplyRepository;
import net.javaguides.giftbackend.repository.ReviewRepository;
import net.javaguides.giftbackend.repository.UsersRepository;
import net.javaguides.giftbackend.service.ReplyService;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {
    ReviewRepository reviewRepository;
    UsersRepository usersRepository;
    ReplyRepository replyRepository;
    @Override
    public ReplyDto createReply(ReplyDto replyDto) {
        Long idReview = replyDto.getReview().getId();
        Long idUser = replyDto.getUsers().getId();
        Review review = reviewRepository.findById(idReview).orElse(null);
        Users users = usersRepository.findById(idUser).orElse(null);
        if(review == null || users == null){
            return null;
        }
        Reply reply = ReplyMapper.mapToReply(replyDto);
        reply.setReview(review);
        reply.setUsers(users);
        Reply saveReply = replyRepository.save(reply);
        updateStatus(idReview, 2L);
        return ReplyMapper.mapToReplyDto(saveReply);

    }

    @Override
    public ReplyDto getReplyByIdReview(Review review) {
        Long idReview = review.getId();
        Reply reply = replyRepository.findByReviewId(idReview);
        if(reply != null){
            return ReplyMapper.mapToReplyDto(reply);
        }
        return null;
    }

    @Override
    public ReplyDto updateContent(Long id, Reply content) {
        Reply reply = replyRepository.findById(id).orElse(null);
        if(reply != null){
            reply.setContent(content.getContent());
            Reply saveReply = replyRepository.save(reply);
            return ReplyMapper.mapToReplyDto(saveReply);
        }
        return null;
    }

    public String updateStatus (Long idReview, long status){
        Review review = reviewRepository.findById(idReview).orElse(null);
        if(review == null){
            return "Review not found";
        }
        review.setStatus(status);
        reviewRepository.save(review);
        return "Update status success";
    }


}
