package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public Page<MovieCardDTO> findAllPaged(String genreIdStr, Pageable pageable){
        Long genreId = !genreIdStr.isEmpty() ? Long.parseLong(genreIdStr) : null;
        return movieRepository.findMovies(genreId, pageable);
    }

    @Transactional(readOnly = true)
    public MovieDetailsDTO findById(Long id) {

        Optional<Movie> movie =  movieRepository.findById(id);

        if(movie.isEmpty()){
            throw new ResourceNotFoundException("Movie not found");
        }

        return new MovieDetailsDTO(movie.get());
    }

}
