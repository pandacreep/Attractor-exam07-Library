package com.pandacreep.library.repo;

import com.pandacreep.library.model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepository extends MongoRepository<Image, String> {}
