package blockchain;

import java.util.Date;

public class BlockImpl implements Block {

    public Hash hash = new Hash();

    public  String blockHash;
    public  String previousBlockHash;
    private String data;
    private long timeStamp;
    private int nonce;

    private BlockImpl() {};

    public BlockImpl(String data, String previousBlockHashLocal ) {
        this.setData(data);
        this.previousBlockHash = previousBlockHashLocal;
        this.setTimeStamp(new Date().getTime());
        this.blockHash = calculateHash();
    }

    @Override
    public String getData() {
        return data;
    }

    @Override
    public void setData(String data) {
        this.data = data;
    }

    @Override
    public long getTimeStamp() {
        return timeStamp;
    }

    @Override
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String calculateHash() {
        return (hash.createHash(previousBlockHash
                                 + Long.toString(timeStamp)
                                 + Integer.toString(nonce)
                                 + data));
    }

    @Override
    public void mineBlock(int difficulty) {

        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with DIFFICULTY * "0"

        while ( ! blockHash.substring( 0, difficulty).equals(target) ) {
            nonce ++;
            blockHash = calculateHash();
            printNonce(difficulty);
        }
        System.out.println("Block Mined!!! : " + blockHash);
    }

    private void printNonce(final int difficulty) {
        int modulus = 0;
        if (difficulty > 0 && difficulty < 3) { modulus = 100; }
        else if (difficulty >= 3 && difficulty <= 5) { modulus = 100000; }
        else if (difficulty >  5 && difficulty < 8) {modulus = 10000000; }
        else if (difficulty >= 8) { modulus = 10000000; }

        if (nonce % modulus == 0 ) {
            System.out.println("--> nonce count=" + nonce );
        }
    }

}
