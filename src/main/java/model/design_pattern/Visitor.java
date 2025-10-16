package model.design_pattern;

import controller.GameController;


public interface Visitor {
    void visitContinuing(GameController game);
    void visitPlayerWon(GameController game);
    void visitDraw(GameController game);
}
