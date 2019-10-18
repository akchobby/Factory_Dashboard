package dashboard;

public class Board {

    private int ip;
    private String boardName;
    private String boardID;

    public Board(int ip, String boardName, String boardID) {
        this.ip = ip;
        this.boardName = boardName;
        this.boardID = boardID;
    }

    public int getIp() {
        return ip;
    }

    public String getBoardName() {
        return boardName;
    }

    public String getBoardID() {
        return this.boardID;
    }
}
