import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ BishopTest.class, BoardTest.class, KnightTest.class,
		PawnTest.class, QueenTest.class, RookTest.class, WannabeKingTest.class, Charger.class })
public class AllTests {

}
