package com.blockchain_backend.Services;

import com.blockchain_backend.Entities.Block;
import com.blockchain_backend.Entities.BlockChain;
import com.blockchain_backend.Entities.Transaction;
import com.blockchain_backend.Repositories.BlockRepository;
import com.blockchain_backend.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Service
public class BlockServiceImpl implements BlockService {

    private MessageDigest msg = MessageDigest.getInstance("SHA-256");
    @Autowired
    private TransactionRepository transactionRepository ;
    @Autowired
    private BlockchainService blockchainService ;
    @Autowired
    private BlockRepository blockRepository;
    public BlockServiceImpl() throws NoSuchAlgorithmException {
    }

    @Override
    public Block createBlock()  {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        Block block = new Block();
        block.setCreation_date(timestamp);

        block.setNonce(0);
        List<Transaction> transactions = new LinkedList<>();
        transactionRepository.findAll().forEach(transaction -> {
            transactions.add(transaction);
        });

        block.setTransactions(transactions);
        String hash = calculHashBlock(block);
        block.setHashCode(hash);
        System.out.println(hash);
        blockRepository.save(block);
        return block;
    }

    @Override
    public String calculHashBlock(Block block)  {

        String str = block.toString();
        StringBuilder s = new StringBuilder();
        byte[] hash = msg.digest(str.getBytes(StandardCharsets.UTF_8));
        // convertir bytes en hexadécimal
        for (byte b : hash) {
            s.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return s.toString() ;
    }

    @Override
    public Block mining(BlockChain blockChain)  {

        Block block = createBlock();
        int c = 0 ;
        String h = new String(new char[blockChain.getDifficulty()]).replace("\0","f");
        String difficulty = new String(new char[blockChain.getDifficulty()]).replace("\0","0");

        while ( !h.substring(0,blockChain.getDifficulty()).equals(difficulty) ){
            block.setNonce(c);
            String str = block.toString();
            byte[] hash = msg.digest(str.getBytes(StandardCharsets.UTF_8));
            StringBuilder s = new StringBuilder();
            // convertir bytes en hexadécimal
            for (byte b : hash) {
                s.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            h = s.toString();
            System.out.println(s.toString());
            c++ ;
        }
        return block;

    }
}
