package edu.illinois.cs242.tests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import edu.illinois.cs242.pieces.Charger;


@RunWith(Suite.class)
@SuiteClasses({ BishopTest.class, BoardTest.class, KnightTest.class,
		PawnTest.class, QueenTest.class, RookTest.class, WannabeKingTest.class, Charger.class })
public class AllTests {

}
