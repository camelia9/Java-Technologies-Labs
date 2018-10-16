package model;

public class TokenMetadata {

    int userDatahashCode;
    long expireDate;

    public TokenMetadata(){

    }

    public TokenMetadata(int userDatahashCode, long expireDate){
        this.userDatahashCode = userDatahashCode;
        this.expireDate = expireDate;
    }

    public int getUserDatahashCode() {
        return userDatahashCode;
    }

    public void setUserDatahashCode(int userDatahashCode) {
        this.userDatahashCode = userDatahashCode;
    }

    public long getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(long expireDate) {
        this.expireDate = expireDate;
    }
}
