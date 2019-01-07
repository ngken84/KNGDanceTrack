package joker.persona.ngrocken.kngdancetrack;

import org.junit.Test;

import joker.persona.ngrocken.kngdancetrack.model.Dance;

import static org.junit.Assert.assertEquals;

public class DanceUnitTest {

    @Test
    public void constructor_works() {
        Dance dance = new Dance("Waltz", "Ballroom");
        assertEquals(dance.getName(), "Waltz");
        assertEquals(dance.getCategory(), "Ballroom");
    }

}
