package blockchain;

public interface Block {
    String getData();

    void setData(String data);

    long getTimeStamp();

    void setTimeStamp(long timeStamp);

    String calculateHash();

    void mineBlock(int difficulty);
}
