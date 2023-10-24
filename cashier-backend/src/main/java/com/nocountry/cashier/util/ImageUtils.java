package com.nocountry.cashier.util;

import com.google.zxing.common.BitMatrix;
import com.nocountry.cashier.exception.GenericException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Random;

/**
 * @author ROMULO
 * @package com.lrpa.app.utils
 * @license Lrpa, zephyr cygnus
 * @since 10/9/2023
 */

@Component
public final class ImageUtils {

    @Value("${path.directory.logo}")
    private String pathImage;
    private final String LOGO = "logo.png";
    private Random random = new Random();

    private Path setPathImage(String filename, String path) {
        return Paths.get(path).resolve(filename).toAbsolutePath();
    }

    /**
     * *Genera una personalización al qr, Añadimos color de fondo
     *
     * @param matrix QR con los formatos, texto, ancho y altura
     * @return BufferedImage
     */
    public BufferedImage toCustomizeQR(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int halfWidth = width / 2;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D) image.getGraphics();

        // *  resulta en un texto más suave y legible en tus gráficos
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        Path ruta = setPathImage(LOGO, pathImage);
        BufferedImage logo;
        File file;
        try {
            file = new File(ruta.toUri());
            logo = ImageIO.read(file);
            //? CENTRAMOS NUESTRO LOGO SOBRE NUESTRO QR
            int xPos = (width - logo.getWidth()) / 2;
            int yPos = (height - logo.getHeight()) / 2;

            Map<String, int[]> mapRGB = randomColorRGB();
            int[] rgb1 = mapRGB.get("RGB1");
            int[] rgb2 = mapRGB.get("RGB2");

            graphics.setColor(new Color(rgb2[0], rgb2[1], rgb2[2])); //main 242, 107, 122  //rosadita 201,44,44  170,101,129
            graphics.fillRect(0, 0, halfWidth, height);

            graphics.setColor(new Color(rgb1[0], rgb1[1], rgb1[2]));//azulita main 75, 123, 236
            graphics.fillRect(halfWidth, 0, halfWidth, height); //azul tenue 68,116,157
            //azul oscurito 59,82,116

            graphics.setColor(Color.BLACK); //new Color(235, 59, 90) color red coqueto

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (matrix.get(x, y)) {
                        graphics.fillRect(x, y, 1, 1);
                    }
                }
            }
            graphics.drawImage(image, 0, 0, null);
            graphics.drawImage(logo, xPos, yPos, null);
            graphics.dispose();
            return image;
        } catch (IOException e) {
            throw new GenericException("Ocurrio un error al superponer las imagenes " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Map<String, int[]> randomColorRGB() {
        int[][] rgbOption1 = {
                {242, 107, 122},
                {201, 44, 44},
                {170, 101, 129},
                {235, 59, 90}
        };
        int[][] rgbOption2 = {
                {68, 116, 157},
                {75, 123, 236},
                {59, 82, 116}
        };
        //? Selecciona dos índices aleatorios para obtener dos conjuntos de valores RGB aleatorios
        int randomIndex1 = random.nextInt(rgbOption1.length);
        int randomIndex2 = random.nextInt(rgbOption2.length);

        //? Obtiene los valores RGB aleatorios de los dos conjuntos seleccionados
        int[] randomRGB1 = rgbOption1[randomIndex1];
        int[] randomRGB2 = rgbOption2[randomIndex2];

        return Map.of("RGB1", randomRGB1, "RGB2", randomRGB2);
    }


}
