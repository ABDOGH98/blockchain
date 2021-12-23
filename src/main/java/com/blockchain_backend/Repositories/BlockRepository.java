package com.blockchain_backend.Repositories;


import com.blockchain_backend.Entities.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BlockRepository extends JpaRepository<Block,Long> {

}
