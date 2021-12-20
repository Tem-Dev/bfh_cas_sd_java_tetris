package tetris.model;

import tetris.model.figures.Figure;

interface Movement {
    void make(Figure figure);
}
