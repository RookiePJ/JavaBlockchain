package blockchain;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Main {

    public static int DIFFICULTY = 4;
    public static int NUMBER_OF_BLOCKS=10;

    public static ArrayList<BlockImpl> blockchain = new ArrayList<BlockImpl>();

    public static void main(String[] args) {

    /*
        blockchain.add(new BlockImpl("Hi im the first block", "0"));
        System.out.println("Trying to Mine block 1... ");
        blockchain.get(0).mineBlock(DIFFICULTY);

        blockchain.add(new BlockImpl("Yo im the second block", blockchain.get(blockchain.size()-1).blockHash));
        System.out.println("Trying to Mine block 2... ");
        blockchain.get(1).mineBlock(DIFFICULTY);

        blockchain.add(new BlockImpl("Hey im the third block", blockchain.get(blockchain.size()-1).blockHash));
        System.out.println("Trying to Mine block 3... ");
        blockchain.get(2).mineBlock(DIFFICULTY);
    */

        blockchain.add(new BlockImpl("Hi im the first block", "0"));
        System.out.println("Trying to Mine block 1... ");
        blockchain.get(0).mineBlock(DIFFICULTY);

        for (int i = 0; i < NUMBER_OF_BLOCKS; i++) {
            blockchain.add(new BlockImpl("Yo im the second block", blockchain.get(blockchain.size()-1).blockHash));
            System.out.format("Trying to Mine block %d... ", i);
            blockchain.get(i).mineBlock(DIFFICULTY);
        }

        System.out.println("\nBlockchain is Valid: " + isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);
    }

    public static Boolean isChainValid() {
        BlockImpl currentBlock;
        BlockImpl previousBlock;

        //loop through blockchain to check hashes:
        for(int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);

            //compare registered hash and calculated hash:
            if(!currentBlock.blockHash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }

            //compare previous hash and registered previous hash
            if(!previousBlock.blockHash.equals(currentBlock.previousBlockHash) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
        }
        return true;
    }
}
