package gamesmanager.ui;

/*
 * ImagePanel.java
 *
 * Copyright (C) 2007  Scott Carpenter (scottc at movingtofreedom dot org)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 * Created on November 9, 2007, 4:07 PM
 *
 */

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

public class ImagePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private Image image;
    private Image scaledImage;
    private int imageWidth = 0;
    private int imageHeight = 0;

    public ImagePanel() {
        super();
    }

    public void loadImage(File f) {
        try {
            image = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println("Image could not be read");
            e.printStackTrace();
        }

        imageWidth = image.getWidth(this);
        imageHeight = image.getHeight(this);

        if (image != null) {

            // use floats so division below won't round
            float iw = imageWidth;
            float ih = imageHeight;
            float pw = this.getWidth(); // panel width
            float ph = this.getHeight(); // panel height

            if (pw < iw || ph < ih) {

                if ((pw / ph) > (iw / ih)) {
                    iw = -1;
                    ih = ph;
                } else {
                    iw = pw;
                    ih = -1;
                }

                // prevent errors if panel is 0 wide or high
                if (iw == 0) {
                    iw = -1;
                }
                if (ih == 0) {
                    ih = -1;
                }

                scaledImage = image.getScaledInstance(new Float(iw).intValue(),
                        new Float(ih).intValue(), Image.SCALE_DEFAULT);

            } else {
                scaledImage = image;
            }
        }

        Graphics g = this.getGraphics();
        this.paintComponent(g);
    }

    public void loadImage(String file) {
        this.loadImage(new File(file));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(scaledImage, 0, 0, this);
    }

    private class ImageLoader extends SwingWorker<Void, Void> {
        private JPanel jp;
        private Graphics g;

        public ImageLoader(JPanel jp, Graphics g) {
            this.jp = jp;
            this.g = g;
        }

        @Override
        protected Void doInBackground() {
            System.out.println("drawing");
            g.drawImage(scaledImage, 0, 0, jp);
            return null;
        }

        @Override
        protected void done() {
            // jp.repaint();
        }
    }
}