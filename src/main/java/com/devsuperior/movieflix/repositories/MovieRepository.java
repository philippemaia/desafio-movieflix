package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = """
            SELECT new com.devsuperior.movieflix.dto.MovieCardDTO(
                movie.id,
                movie.title,
                movie.subTitle,
                movie.year,
                movie.imgUrl)
            FROM Movie movie
            JOIN movie.genre genre
            WHERE :genreId is null or genre.id = :genreId
            """)
    Page<MovieCardDTO> findMovies(@Param("genreId") Long genreId, Pageable pageable);
}
