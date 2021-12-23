package com.blockchain_backend.Services;



import com.blockchain_backend.Entities.Block;
import com.blockchain_backend.Entities.BlockChain;

import java.security.NoSuchAlgorithmException;

public interface BlockService  {

    public Block createBlock() throws NoSuchAlgorithmException;
    public String calculHashBlock(Block block) throws NoSuchAlgorithmException;
    public Block mining(BlockChain blockChain) throws NoSuchAlgorithmException;

}
