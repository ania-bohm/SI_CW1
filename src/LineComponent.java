import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JOptionPane;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import java.util.ArrayList;

class LineComponent extends JComponent {

    ArrayList<Line2D.Double> lines;
    ArrayList<Ellipse2D.Double> points;

    LineComponent(int width, int height) {
        super();
        setPreferredSize(new Dimension(width, height));
        lines = new ArrayList<Line2D.Double>();
        points = new ArrayList<Ellipse2D.Double>();
    }

    public void addLine(int x1, int x2, int y1, int y2) {
        Line2D.Double line = new Line2D.Double(x1, y1, x2, y2);
        lines.add(line);
        repaint();
    }

    public void addPoint(int x, int y) {
        Ellipse2D.Double point = new Ellipse2D.Double(x, y, 12, 12);
        points.add(point);
        repaint();
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        g2d.setColor(Color.darkGray);
        g2d.fillRect(0, 0, width - 1, height - 1);
        g2d.setColor(Color.black);
        g2d.drawRect(0, 0, width - 1, height - 1);
        g2d.setColor(Color.white);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                g2d.fillOval(i * 50 + 22, j * 50 + 22, 5, 5);
            }
        }
        g2d.setStroke(new BasicStroke(8));
        //float alpha = 0.5f;
        //Color color = new Color(1, 1, 1, alpha);
        //g2d.setPaint(color);
        Color gray = new Color(219, 209, 209);
        g2d.setPaint(gray);
        for (Line2D.Double line : lines) {
            g2d.drawLine(
                    (int) line.getX1() * 50 + 25,
                    (int) line.getY1() * 50 + 25,
                    (int) line.getX2() * 50 + 25,
                    (int) line.getY2() * 50 + 25
            );
        }
        g2d.setPaint(Color.white);
        for (Ellipse2D.Double point : points) {
            g2d.fillOval(
                    (int) (point.getX() * 50) - 6 + 25,
                    (int) (point.getY() * 50) - 6 + 25,
//                    (int) (point.getX() * 50),
//                    (int) (point.getY() * 50),
                    (int) point.getWidth(),
                    (int) point.getHeight());
        }
    }
}