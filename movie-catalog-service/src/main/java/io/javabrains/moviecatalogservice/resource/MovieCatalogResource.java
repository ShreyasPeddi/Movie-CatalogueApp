package io.javabrains.moviecatalogservice.resource;

import io.javabrains.moviecatalogservice.models.CatalogueItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;
import io.javabrains.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WebClient.Builder builder;

    @RequestMapping("/{id}")
    public List<CatalogueItem> getCatologue(@PathVariable("id") String userId){

        UserRating ratings=restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+userId,
                UserRating.class);

        return ratings.getUserRating().stream().map(rating ->{
            Movie movie=restTemplate.getForObject("http://movie-info-service/movies/"+rating.getId(), Movie.class);
            return new CatalogueItem(movie.getName(),"test",rating.getRating());
        })

                .collect(Collectors.toList());


    }
    @RequestMapping("/")
    public String test(){
        return "Hello World";
    }
}

// Web Client Builder to call API:

//            Movie movie= builder.build()
//                    .get()
//                    .uri("http://localhost:8081/movies/"+rating.getId())
//                    .retrieve()
//                    .bodyToMono(Movie.class)
//                    .block();