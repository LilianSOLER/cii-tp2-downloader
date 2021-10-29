package downloader.ui;

import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;


import java.util.Set;
import java.util.LinkedHashSet;

import static java.lang.Math.max;


class StackLayout implements LayoutManager {
	public void addLayoutComponent(String name, Component c) {}
	public void removeLayoutComponent(Component c) {}
	
	public Dimension minimumLayoutSize(Container parent) {		
		int width = 0;
		int height = 0;
		for(int i=0; i<parent.getComponentCount(); i++) {
			Component c = parent.getComponent(i);
			if(!c.isVisible())
				continue;
			Dimension size = c.getMinimumSize();
			width = max(width, size.width);
			height += size.height;
		}

		Insets insets = parent.getInsets();
		return new Dimension(width  + (insets.left + insets.right),
		                     height + (insets.top + insets.bottom));
	}

	public Dimension preferredLayoutSize(Container parent) {
		int width = 0;
		int height = 0;
		for(int i=0; i<parent.getComponentCount(); i++) {
			Component c = parent.getComponent(i);
			if(!c.isVisible())
				continue;
			Dimension size = c.getPreferredSize();
			width = max(width, size.width);
			height += size.height;
		}

		Insets insets = parent.getInsets();
		return new Dimension(width  + (insets.left + insets.right),
		                     height + (insets.top + insets.bottom));
	}
	
	public void layoutContainer(Container parent) {
		Insets insets = parent.getInsets();
		int x = insets.top;
		int y = insets.left;
		int width = parent.getWidth() - (insets.left + insets.right);
		
		for(int i=0; i<parent.getComponentCount(); i++) {
			Component c = parent.getComponent(i);
			if(!c.isVisible())
				continue;
			Dimension size = c.getPreferredSize();
			c.setBounds(x, y, width, size.height);
			y += size.height;
		}
	}
}
