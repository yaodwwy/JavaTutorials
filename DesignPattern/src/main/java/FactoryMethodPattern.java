import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam Yao on 2017/6/8.
 * 出厂模式
 *
 * @Link https://en.wikipedia.org/wiki/Factory_method_pattern
 */

class Room {
    public void connect(Room room) {
        System.out.println("connect --> Room");
    }
}

class MagicRoom extends Room {
    public void connect(Room room) {
        System.out.println("connect --> MagicRoom");
    }
}

class OrdinaryRoom extends Room {
    public void connect(Room room) {
        System.out.println("connect --> OrdinaryRoom");
    }
}

abstract class MazeGameFactory {
    private final List<Room> rooms = new ArrayList<>();

    public MazeGameFactory() {
        Room room1 = makeRoom();
        Room room2 = makeRoom();
        room1.connect(room2);
        rooms.add(room1);
        rooms.add(room2);
    }
    abstract protected Room makeRoom();
}

class MagicMazeGameFactory extends MazeGameFactory {
    @Override
    protected Room makeRoom() {
        System.out.println("MagicMazeGameFactory --> makeRoom");
        return new MagicRoom();
    }
}

class OrdinaryMazeGameFactory extends MazeGameFactory {
    @Override
    protected Room makeRoom() {
        System.out.println("OrdinaryMazeGameFactory --> makeRoom");
        return new OrdinaryRoom();
    }
}

public class FactoryMethodPatternMain{
    public static void main(String[] args) throws Exception {
        MazeGameFactory ordinaryGame = new OrdinaryMazeGameFactory();
        Room ordinaryRoom = ordinaryGame.makeRoom();
        MazeGameFactory magicGame = new MagicMazeGameFactory();
        Room magicRoom = magicGame.makeRoom();
    }
}
