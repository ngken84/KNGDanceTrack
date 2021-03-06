package joker.persona.ngrocken.kngdancetrack;

import org.junit.Test;

import java.util.Date;

import joker.persona.ngrocken.kngdancetrack.model.Move;

import static org.junit.Assert.assertEquals;

public class MoveUnitTest {

    @Test
    public void constructor_works() {
        Move move = new Move("Whip", 1, "West Coast Swing", "It's a Whip");
        assertEquals(move.getDescription(), "It's a Whip");
        assertEquals(move.getId(), "1");
        assertEquals(move.getName(), "Whip");
    }
}
