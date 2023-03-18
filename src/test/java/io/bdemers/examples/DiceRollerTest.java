package io.bdemers.examples;

import dev.diceroll.parser.Dice;
import dev.diceroll.parser.DiceExpression;
import dev.diceroll.parser.DiceRollingVisitor;
import dev.diceroll.parser.impl.RegexDice;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class DiceRollerTest {

    @Test
    public void flipCoin() {
        assertThat(
                rollFlaky("1d2")
        ).isEqualTo(1);
    }

    @Test
    public void rollDie() {
        assertThat(
                rollFlaky("1d20")
        ).isGreaterThan(1);
    }

    private int rollFlaky(String expression) {
        return Dice.roll(expression);
    }

    private int rollPredictable(String expression, int value) {
        DiceExpression diceExpression = new RegexDice().parse(expression);
        return new DiceRollingVisitor(numberOfFaces -> value)
                .visit(diceExpression)
                .getValue();
    }
}
