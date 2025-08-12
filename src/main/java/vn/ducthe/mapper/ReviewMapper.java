package vn.ducthe.mapper;

import org.springframework.stereotype.Component;
import vn.ducthe.dto.response.ReviewDTO;
import vn.ducthe.entity.ReviewsEntity;

import java.util.List;

@Component
public class ReviewMapper {
    public ReviewDTO toReviewDTO(List<ReviewsEntity> reviewsEntity) {
        ReviewDTO reviewDTO = new ReviewDTO();

        int countReview = reviewsEntity.size();
        reviewDTO.setReviewCount(countReview);

        if (countReview == 0) {
            reviewDTO.setRating(String.format("%.2f", 0.00));
            return reviewDTO;
        }

        double calculatorRating = 0D;
        for (ReviewsEntity item : reviewsEntity) {
            calculatorRating += item.getRating();
        }
        reviewDTO.setRating(String.format("%.2f", calculatorRating / countReview));

        return reviewDTO;
    }
}

