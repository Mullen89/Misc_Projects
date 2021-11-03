package graphics;

import java.awt.image.BufferedImage;

public class Assets {

    /**
     * Terrain: grass = 0,0 | sand = 4,0 | dirt = 8,0 | stone = 0,7 | bush = 4, 13
     */

    private static String imagesFilePath = "../res/textures/";
    public static BufferedImage player, grass, sand, dirt, stone, bush;

    public static void init() {
        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage(imagesFilePath + "sprite_sheet_1.png"));
        SpriteSheet terrainSheet = new SpriteSheet(ImageLoader.loadImage(imagesFilePath + "terrain_sheet_1.png"));

        player = playerSheet.cropSheet(2, 1);
        grass = terrainSheet.cropSheet(0, 0);
        sand = terrainSheet.cropSheet(4, 0);
        dirt = terrainSheet.cropSheet(8, 0);
        stone = terrainSheet.cropSheet(0, 7);
        bush = terrainSheet.cropSheet(4, 13);
    }
}
