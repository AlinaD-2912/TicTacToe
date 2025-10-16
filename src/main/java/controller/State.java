package controller;

import model.design_pattern.Visitor;
import view.View;

public enum State implements Visitor {
    CONTINUING {
        @Override
        public void visitContinuing(GameController game) {
            System.out.println("Game continues");

        }

        @Override
        public void visitPlayerWon(GameController game) { }

        @Override
        public void visitDraw(GameController game) { }
    },
    PLAYER_WON {
        @Override
        public void visitContinuing(GameController game) { }

        @Override
        public void visitPlayerWon(GameController game) {
            game.getView().gameOverMessage(2);
        }

        @Override
        public void visitDraw(GameController game) { }
    },
    DRAW {
        @Override
        public void visitContinuing(GameController game) { }

        @Override
        public void visitPlayerWon(GameController game) { }

        @Override
        public void visitDraw(GameController game) {
            game.getView().gameOverMessage(1);
        }
    };
}