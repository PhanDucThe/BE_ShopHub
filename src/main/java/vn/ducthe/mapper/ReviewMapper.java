package vn.ducthe.mapper;

import org.springframework.stereotype.Component;
import vn.ducthe.dto.response.ReviewDTO;
import vn.ducthe.model.ReviewEntity;

import java.util.List;

@Component
public class ReviewMapper {
    public ReviewDTO toReviewDTO(List<ReviewEntity> reviewEntities) {
        ReviewDTO reviewDTO = new ReviewDTO();

        int countReview = reviewEntities.size();
        reviewDTO.setReviewCount(countReview);

        if (countReview == 0) {
            reviewDTO.setRating(String.format("%.2f", 0.00));
            return reviewDTO;
        }

        double calculatorRating = 0D;
        for (ReviewEntity item : reviewEntities) {
            calculatorRating += item.getRating();
        }
        reviewDTO.setRating(String.format("%.2f", calculatorRating / countReview));

        return reviewDTO;
    }
}
