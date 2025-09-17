package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserService userService;


    @Transactional
    public ReviewDTO insert(ReviewDTO dto) {
        Review review = getReview(dto);
        review = reviewRepository.save(review);
        return new ReviewDTO(review);
    }

    private Review getReview(ReviewDTO dto) {

        User user = new User();
        user.setId(userService.getProfile().getId());
        user.setName(userService.getProfile().getName());
        user.setEmail(userService.getProfile().getEmail());

        Review review = new Review();
        review.setText(dto.getText());
        review.setMovie(movieRepository.getReferenceById(dto.getMovieId()));
        review.setUser(user);

        return review;
    }

}
