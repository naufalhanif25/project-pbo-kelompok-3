

import java.awt.*;
public class BackGroundWarna {
  /**
     * Metode untuk menggambar latar belakang dengan gradasi.
     *
     * @param g2d       Graphics2D dari komponen tempat menggambar.
     * @param width     Lebar area yang akan digambar.
     * @param height    Tinggi area yang akan digambar.
     * @param color1    Warna awal gradasi.
     * @param color2    Warna akhir gradasi.
     */
    public static void drawGradientBackground(Graphics2D g2d, int width, int height, Color color1, Color color2) {
      GradientPaint gradient = new GradientPaint(0, 0, color1, 0, height, color2);
      g2d.setPaint(gradient);
      g2d.fillRect(0, 0, width, height);
  }
}

