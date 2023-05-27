package gui;

import javax.swing.Icon;
import javax.swing.JCheckBox;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

class TransparentCheckBoxIcon implements Icon {
    private final int size;
    
    public TransparentCheckBoxIcon(int size) {
        this.size = size;
    }
    
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        JCheckBox checkBox = (JCheckBox) c;
        
        int boxSize = size - 4; // 调整选中框的大小
        
        g.setColor(Color.BLACK);
        g.drawRect(x, y, size - 1, size - 1); // 绘制选中框
        
        if (checkBox.isSelected()) {
            g.fillRect(x + (size - boxSize) / 2, y + (size - boxSize) / 2, boxSize, boxSize); // 填充选中框
        }
    }
    
    @Override
    public int getIconWidth() {
        return size;
    }
    
    @Override
    public int getIconHeight() {
        return size;
    }
}
