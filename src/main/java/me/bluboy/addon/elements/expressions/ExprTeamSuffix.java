package me.bluboy.addon.elements.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Nullable;

public class ExprTeamSuffix extends SimplePropertyExpression<Team, String> {

    static {
        register(ExprTeamSuffix.class, String.class, "[team] suffix", "teams");
    }

    @Override
    protected String getPropertyName() {
        return "team suffix";
    }

    @Nullable
    @Override
    public String convert(Team team) {
        return team.getSuffix();
    }

    @Override
    @Nullable
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.DELETE || mode == Changer.ChangeMode.RESET)
            return new Class[] {String.class};
        return null;
    }

    @Override
    public void change(final Event e, @Nullable final Object[] delta, final Changer.ChangeMode mode) {
        if (delta == null) {
            for (final Team t : getExpr().getArray(e)) {
                t.setSuffix(null);
            }
        } else {
            final String name = (String) delta[0];
            for (final Team t : getExpr().getArray(e)) {
                t.setSuffix(name);
            }
        }
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
