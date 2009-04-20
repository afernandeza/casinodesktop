package gamesmanager.beans;

public class GameTable {

    private int tableid;
    private GameType gameType;
    private String game;

    public GameTable() {

    }

    public GameTable(int tableid, int gameTypeid) {
        super();
        this.tableid = tableid;
        this.gameType = new GameType(gameTypeid);
    }

    public GameTable(int tableid, GameType gameType) {
        super();
        this.tableid = tableid;
        this.gameType = gameType;
    }

    public int getTableid() {
        return tableid;
    }

    public void setTableid(String tableid) {
        this.tableid = Integer.parseInt(tableid);
    }

    public void setTableid(int tableid) {
        this.tableid = tableid;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    @Override
    public String toString(){
        return this.getTableid() + " " + this.getGame();
    }
    
}
