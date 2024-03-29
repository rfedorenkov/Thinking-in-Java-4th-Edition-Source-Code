package net.mindview.util;

import javax.swing.*;

/**
 * Tool for running Swing demos from the
 * console, both applets and JFrames.
 */
public class SwingConsole {
    public static void run(final JFrame f,
                           final int width,
                           final int height) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                f.setTitle(f.getClass().getSimpleName());
                f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                f.setSize(width, height);
                f.setVisible(true);
            }
        });
    }
}