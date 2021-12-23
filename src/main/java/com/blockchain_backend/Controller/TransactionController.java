package com.blockchain_backend.Controller;


import com.blockchain_backend.Entities.Block;
import com.blockchain_backend.Entities.BlockChain;
import com.blockchain_backend.Entities.Transaction;
import com.blockchain_backend.Repositories.BlockChaineRepository;
import com.blockchain_backend.Repositories.TransactionRepository;
import com.blockchain_backend.Services.BlockService;
import com.blockchain_backend.Services.BlockchainService;
import com.blockchain_backend.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;


@RestController
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository ;
    @Autowired
    private BlockchainService blockchainService;
    @Autowired
    private BlockService blockService;
    @Autowired
    private BlockChaineRepository blockChaineRepository;
    @Autowired
    TransactionService transactionService;


    @PostMapping("/sign")
    public void sign(@RequestBody Transaction transaction){
        transactionService.signTransaction(transaction);
    }

    @GetMapping("/mine/{id}")
    public Block mine(@PathVariable String id) throws NoSuchAlgorithmException {
        BlockChain blockChain = blockChaineRepository.findById(id).get();
        Block blockMiner = blockService.mining(blockChain);
        Block block = blockchainService.minerBlock(null,blockMiner,blockChain);
        transactionRepository.deleteAll();
        return block;
    }


}
