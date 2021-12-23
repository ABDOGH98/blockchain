package com.blockchain_backend.Services;

import com.blockchain_backend.Entities.Block;
import com.blockchain_backend.Entities.BlockChain;
import com.blockchain_backend.Entities.Transaction;
import com.blockchain_backend.Repositories.BlockChaineRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Service
public class BlockchainServiceImpl implements BlockchainService {
    @Autowired
    BlockChaineRepository blockChaineRepository ;



    @Override
    public BlockChain createBlockchain(String nom ,int difficulty,double rewards) {

        BlockChain blockChain = new BlockChain();
        blockChain.setNom(nom);
        blockChain.setRewards(rewards);
        blockChain.setDifficulty(difficulty);
        blockChain.setId(nom);
        blockChaineRepository.save(blockChain);
        return blockChain ;
    }

    @Override
    public Block minerBlock(String address_mineur, Block block, BlockChain blockChain)  {

        Transaction transaction = new Transaction();
        transaction.setAddress_src("miner rewards");
        transaction.setAddress_dst(address_mineur);
        transaction.setMontant(blockChain.getRewards());
        transaction.setDate(new Date());
        List<Transaction> transactionsList =  block.getTransactions();
        if(transactionsList.size()==0){
            return null;
        }
        transactionsList.add(transaction);
        block.setTransactions(transactionsList);

        Block last = blockChain.getLastBlock();
        if(last == null){
            blockChain.setLastBlock(block);
            blockChaineRepository.save(blockChain);
            return block;
        }
        block.setPrevBlock(last);
        block.setPrev_hashCode(last.getHashCode());
        blockChain.setLastBlock(block);
        blockChaineRepository.deleteById(blockChain.getId());
        blockChaineRepository.save(blockChain);

        return block ;

    }

    @Override
    public Block getLastBlock(BlockChain blockChain) {

        return blockChain.getLastBlock();
    }

    @Override
    public boolean blockchianeValide() {
        return false;
    }
}
