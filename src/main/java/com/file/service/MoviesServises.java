package com.file.service;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.file.dto.MovieDto;
import com.file.dto.MoviePageResponse;

public interface MoviesServises {
	
	
	 MovieDto addmovie(MovieDto movieDto,MultipartFile file) throws IOException;
	 MovieDto getMovieById(Integer movieId);
	 List<MovieDto>getAllMovie();
	 MovieDto updateMovie(Integer movieId,MovieDto movieDto,MultipartFile file)throws IOException;
	 String deleteMovie(Integer movieId) throws IOException;
	 MoviePageResponse getAllMovieWithPagination(Integer pageNumber,Integer pageSize);
	 MoviePageResponse getAllMovieWithSorting(Integer pageNumber,Integer pageSize,String sortBy,String dir);

}
