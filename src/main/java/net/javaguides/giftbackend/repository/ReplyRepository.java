package net.javaguides.giftbackend.repository;

import net.javaguides.giftbackend.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    Reply findByReviewId(Long idReview);
}
