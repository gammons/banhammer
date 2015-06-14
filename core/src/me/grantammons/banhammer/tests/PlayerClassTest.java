package me.grantammons.banhammer.tests;

import me.grantammons.banhammer.core.entities.playerClasses.Brute;
import org.testng.annotations.Test;

/**
 * Created by grantammons on 6/14/15.
 */
public class PlayerClassTest {
    @Test(description="generating stats")
    public void testGenerateStats() {
        Brute b = new Brute();
        System.out.println(b.toString());
    }
}
