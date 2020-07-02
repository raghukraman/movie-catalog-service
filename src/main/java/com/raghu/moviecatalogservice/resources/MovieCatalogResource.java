package com.raghu.moviecatalogservice.resources;

import com.raghu.moviecatalogservice.model.CatalogItem;
import com.raghu.moviecatalogservice.model.Movie;
import com.raghu.moviecatalogservice.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId")  String userId) {
        Rating r = restTemplate.getForObject("http://RATING-INFO/rating/2", Rating.class);
        Movie m = restTemplate.getForObject("http://MOVIE-INFO/movies/2", Movie.class);

        return Collections.singletonList(new CatalogItem(m.getName(),m.getName(),r.getRating()));

        //return getCatalogList();
    }

    public List<CatalogItem> getCatalogList() {
        CatalogItem movie1 = new CatalogItem("The Shawshank Redemption","Motivating",9.77);
        CatalogItem movie2 = new CatalogItem("Shindleres List","Feel Good",9.67);
        CatalogItem movie3 = new CatalogItem("Pulp Fiction","Non Linear",9.20);

        List<CatalogItem> items = new ArrayList<>();
        items.add(movie1);
        items.add(movie2);
        items.add(movie3);

        return items;



    }
}
