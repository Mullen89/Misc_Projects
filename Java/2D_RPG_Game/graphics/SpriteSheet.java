package graphics;

import java.awt.image.BufferedImage;
import java.awt.Point;

public class SpriteSheet {

    private BufferedImage sheet;
    // private int[][] sheetArr = new int[21][26];
    static final int SPRITE_WIDTH = 35;
    static final int SPRITE_HEIGHT = 35;

    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }

    public BufferedImage cropSheet(int spriteRow, int SpriteCol) {
        Point p = getSprite(spriteRow, SpriteCol);
        return sheet.getSubimage(p.x, p.y, SPRITE_WIDTH, SPRITE_HEIGHT);
    }

    public Point getSprite(int row, int col) {
        Point p = new Point();
        p.setLocation(col * SPRITE_HEIGHT, row * SPRITE_WIDTH);
        return p;
    }
}
