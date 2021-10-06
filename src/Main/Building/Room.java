package Main.Building;

import java.io.Serializable;

public class Room extends Building implements Serializable {
    public int bedCount;
    public int roomCode;
    static int x = 1;

    public Room(int bedCount) {
        this.bedCount = bedCount;
        this.roomCode = x++;
    }
    public Room(int bedCount, int roomCode) {
        this.bedCount = bedCount;
        this.roomCode = roomCode;
    }
}
