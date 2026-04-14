import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Imagen {
    public static void main(String[] args) {
        File file = new File("src/main/resources/assets/img1.jpg");
        File file2 = new File("src/main/resources/assets/img1-Nuevo.jpg");

        int ancho;
        int alto;
        int pixel;
        int pixelNuevo;

        int r;
        int g;
        int b;

        int mascara = 0xFF;

        int binario = (7 >> 2);

        try {
            BufferedImage bufer = ImageIO.read(file);
            ancho = bufer.getWidth();
            alto = bufer.getHeight();

            BufferedImage bufer2 = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);

            for (int y = 0; y < alto; y++) {
                for (int x = 0; x < ancho; x++) {
                    pixel = bufer.getRGB(x, y);
                    r = (pixel >> 16) & mascara;
                    g = (pixel >> 8) & mascara;
                    b = pixel & mascara;

                    //System.out.println("(" + r + ", " + g + ", " + b + ")");

                    pixelNuevo = (r << 16) | (g << 8) | (b << 0);
                    bufer2.setRGB(x, y, pixelNuevo);
                }
            }
            ImageIO.write(bufer2, "jpg", file2);
            System.out.println("Imagen guardada");
        } catch (Exception e) {
            System.out.println("Error al leer la imagen" + e.getMessage());
        }
    }
}
