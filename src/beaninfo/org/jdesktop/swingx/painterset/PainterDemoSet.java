/*
 * PainterDemoSet.java
 *
 * Created on October 18, 2006, 8:20 PM
 */

package org.jdesktop.swingx.painterset;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.text.Style;
import org.apache.batik.ext.awt.LinearGradientPaint;
import org.apache.batik.ext.awt.MultipleGradientPaint;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.painter.AbstractPainter;
import org.jdesktop.swingx.painter.CompoundPainter;
import org.jdesktop.swingx.painter.effects.GlowPathEffect;
import org.jdesktop.swingx.painter.effects.InnerGlowPathEffect;
import org.jdesktop.swingx.painter.effects.InnerShadowPathEffect;
import org.jdesktop.swingx.painter.MattePainter;
import org.jdesktop.swingx.painter.Painter;
import org.jdesktop.swingx.painter.PainterListCellRenderer;
import org.jdesktop.swingx.painter.effects.NeonBorderEffect;
import org.jdesktop.swingx.painter.effects.PathEffect;
import org.jdesktop.swingx.painter.PinstripePainter;
import org.jdesktop.swingx.painter.RectanglePainter;
import org.jdesktop.swingx.painter.ShapePainter;
import org.jdesktop.swingx.painter.TextPainter;
import org.jdesktop.swingx.painter.effects.ShadowPathEffect;
import org.jdesktop.swingx.util.ShapeUtils;

/**
 *
 * @author  joshy
 */
public class PainterDemoSet extends javax.swing.JFrame {
    
    /** Creates new form PainterDemoSet */
    public PainterDemoSet() {
        initComponents();
        painterList.setModel(new DefaultListModel());
        painterPanel.setLayout(new BorderLayout());
        CompoundPainter comp;
      //  GradientPaint gradient = new GradientPaint(new Point2D.Double(0,0), Color.BLACK,
      //          new Point2D.Double(0,10), Color.BLUE);
        
        Color[] colors = { Color.BLACK, Color.BLUE, Color.WHITE};
        float[] floats = { 0f, 0.5f, 1f};
        MultipleGradientPaint gradient = new LinearGradientPaint(
                new Point2D.Double(0,0), new Point2D.Double(100,0), 
                floats,colors);
        
        
        // a rectangle filled with a gradient
        RectanglePainter stdrect = new RectanglePainter(0,0,0,0);
        stdrect.setFillPaint(gradient);
        stdrect.setBorderPaint(Color.BLACK);
        stdrect.setBorderWidth(4f);
        comp = new CompoundPainter(stdrect, new TextPainter("This is some stuff"));
        addDemo(new JXButton("Cool Text Yo"), comp, "button with gradient and text");
        
        Shape starShape = ShapeUtils.generatePolygon(5,30,15,true);
        // build a star shape with 5 points and 30 degree angles
        ShapePainter star = null;
        
        // star filled
        star = new ShapePainter(starShape, Color.RED);
        star.setAntialiasing(ShapePainter.Antialiasing.On);
        star.setStyle(ShapePainter.Style.FILLED);
        addDemo(new JXPanel(),star,"Star style = filled");
        // star outline
        star = new ShapePainter(starShape, Color.RED);
        star.setAntialiasing(ShapePainter.Antialiasing.On);
        star.setStyle(ShapePainter.Style.OUTLINE);
        addDemo(new JXPanel(),star,"Star style = outline");
        // star both
        star = new ShapePainter(starShape, Color.RED);
        star.setAntialiasing(ShapePainter.Antialiasing.On);
        star.setStyle(ShapePainter.Style.BOTH);
        addDemo(new JXPanel(),star,"Star style = both");
        // star both
        star = new ShapePainter(starShape, Color.RED);
        star.setAntialiasing(ShapePainter.Antialiasing.On);
        star.setStyle(ShapePainter.Style.BOTH);
        star.setBorderWidth(6f);
        addDemo(new JXPanel(),star,"Star border width = 5");
        
        // left/top aligned star
        star = new ShapePainter(starShape, Color.RED);
        star.setAntialiasing(ShapePainter.Antialiasing.On);
        star.setHorizontal(ShapePainter.HorizontalAlignment.LEFT);
        star.setVertical(ShapePainter.VerticalAlignment.TOP);
        addDemo(new JXPanel(),star,"Star, left & top aligned");
        // left/top aligned with insets
        star = new ShapePainter(starShape, Color.RED);
        star.setAntialiasing(ShapePainter.Antialiasing.On);
        star.setHorizontal(ShapePainter.HorizontalAlignment.LEFT);
        star.setVertical(ShapePainter.VerticalAlignment.TOP);
        star.setInsets(new Insets(50,50,50,50));
        addDemo(new JXPanel(),star,"Star, left & top aligned, 50px insets");
        // left aligned only with left insets
        star = new ShapePainter(starShape, Color.RED);
        star.setAntialiasing(ShapePainter.Antialiasing.On);
        star.setHorizontal(ShapePainter.HorizontalAlignment.LEFT);
        star.setInsets(new Insets(0,50,0,0));
        addDemo(new JXPanel(),star,"Star, left aligned, 50px left insets");
        // left aligned only with left top insets
        star = new ShapePainter(starShape, Color.RED);
        star.setAntialiasing(ShapePainter.Antialiasing.On);
        star.setHorizontal(ShapePainter.HorizontalAlignment.LEFT);
        star.setInsets(new Insets(50,50,0,0));
        addDemo(new JXPanel(),star,"Star, left aligned, 50px left & top insets");
        
        // the same star, but with a drop shadow
        star = new ShapePainter(starShape, Color.RED);
        star.setAntialiasing(ShapePainter.Antialiasing.On);
        star.setStyle(ShapePainter.Style.FILLED);
        star.setPathEffect(new ShadowPathEffect());
        addDemo(new JXPanel(), star, "Star with drop shadow");
        
        
        
        // normal text
        Font font = new Font("SansSerif", Font.BOLD, 80);
        TextPainter textnorm = new TextPainter("Neon", font, Color.RED);
        comp = new CompoundPainter(new MattePainter(Color.GRAY),textnorm);
        addDemo(new JXPanel(),comp,"Text with no effects");
        
        
        // text AA on
        MattePainter gray = new MattePainter(Color.GRAY);
        TextPainter text = new TextPainter("Neon",font,Color.BLACK);
        text.setAntialiasing(TextPainter.Antialiasing.On);
        addDemo(new JXPanel(), new CompoundPainter(gray,text),"Text AA on");
        // text AA off
        text = new TextPainter("Neon",font,Color.BLACK);
        text.setAntialiasing(TextPainter.Antialiasing.Off);
        addDemo(new JXPanel(), new CompoundPainter(gray,text),"Text AA off");
        
        // text left aligned
        text = new TextPainter("Neon",font,Color.BLACK);
        text.setHorizontal(TextPainter.HorizontalAlignment.LEFT);
        addDemo(new JXPanel(), new CompoundPainter(gray,text),"Text Left aligned");
        // text right aligned
        text = new TextPainter("Neon",font,Color.BLACK);
        text.setHorizontal(TextPainter.HorizontalAlignment.RIGHT);
        addDemo(new JXPanel(), new CompoundPainter(gray,text),"Text Right aligned");
        // text top aligned
        text = new TextPainter("Neon",font,Color.BLACK);
        text.setVertical(TextPainter.VerticalAlignment.TOP);
        addDemo(new JXPanel(), new CompoundPainter(gray,text),"Text Top aligned");
        // text bottom aligned
        text = new TextPainter("Neon",font,Color.BLACK);
        text.setVertical(TextPainter.VerticalAlignment.BOTTOM);
        addDemo(new JXPanel(), new CompoundPainter(gray,text),"Text Bottom aligned");
        // text bottom aligned with insets
        text = new TextPainter("Neon",font,Color.BLACK);
        text.setVertical(TextPainter.VerticalAlignment.BOTTOM);
        text.setInsets(new Insets(0,0,20,0));
        addDemo(new JXPanel(), new CompoundPainter(gray,text),"Text Bottom aligned with 20px inset");
        
        // text with gradient
        text = new TextPainter("Neon",font,Color.BLACK);
        text.setFillPaint(gradient);
        addDemo(new JXPanel(), new CompoundPainter(gray,text),"Text with gradient");
        // text with snapped gradient
        text = new TextPainter("Neon",font,Color.BLACK);
        text.setFillPaint(gradient);
        text.setSnapPaint(true);
        addDemo(new JXPanel(), new CompoundPainter(gray,text),"Text with snapped gradient");
        
        // text w/ dropshadow
        TextPainter textshadow = new TextPainter("Neon", font, Color.RED);
        ShadowPathEffect shadow = new ShadowPathEffect();
        //shadow.setOffset(new Point(3,3));
        textshadow.setPathEffect(shadow);
        comp = new CompoundPainter(new MattePainter(Color.GRAY),textshadow);
        addDemo(new JXPanel(),comp,"Text with shadow");

        // text w/ glow effet
        TextPainter textglow = new TextPainter("Neon", font, Color.RED);
        GlowPathEffect glow = new GlowPathEffect();
        //glow.setOffset(new Point(0,0));
        textglow.setPathEffect(glow);
        comp = new CompoundPainter(new MattePainter(Color.GRAY),textglow);
        addDemo(new JXPanel(),comp,"Text with glow");
        
        // text w/ inner shadow effect
        TextPainter textinshad = new TextPainter("Neon", font, Color.RED);
        textinshad.setPathEffect(new InnerShadowPathEffect());
        comp = new CompoundPainter(new MattePainter(Color.GRAY),textinshad);
        addDemo(new JXPanel(), comp, "Text with inner shadow");
        
        // text w/ inner glow effect
        TextPainter textinglow = new TextPainter("Neon", font, Color.RED);
        textinglow.setPathEffect(new InnerGlowPathEffect());
        comp = new CompoundPainter(new MattePainter(Color.GRAY),textinglow);
        addDemo(new JXPanel(), comp, "Text with inner glow");
        
        
        
        
        
        RectanglePainter rectnorm = null;
        
        // rect w/ 0 insets
        rectnorm = createStandardRectPainter();
        rectnorm.setInsets(new Insets(0,0,0,0));
        addDemo(new JXPanel(),new CompoundPainter(gray,rectnorm),"Rectangle, green on gray, 0px insets");
        
        // normal rectangle
        rectnorm = createStandardRectPainter();
        addDemo(new JXPanel(),new CompoundPainter(gray,rectnorm),"Rectangle, green on gray, 20px insets");
        
        // rect w/ 20px top insets
        rectnorm = createStandardRectPainter();
        rectnorm.setInsets(new Insets(50,0,0,0));
        addDemo(new JXPanel(),new CompoundPainter(gray,rectnorm),"Rectangle 50px top insets");
        
        // do the fixed size demos
        rectnorm = create50pxRectPainter();
        addDemo("Rectangle, 50x50, default aligned (center)", gray, rectnorm);
        // rect 50x50 left aligned
        rectnorm = create50pxRectPainter();
        rectnorm.setHorizontal(RectanglePainter.HorizontalAlignment.LEFT);
        addDemo("Rectangle, 50x50, left aligned", gray, rectnorm);

        // rect 50x50 top aligned
        rectnorm = create50pxRectPainter();
        rectnorm.setVertical(RectanglePainter.VerticalAlignment.TOP);
        addDemo("Rectangle, 50x50, top aligned", gray, rectnorm);
        // rect 50x50 top aligned w/ horiz stretch
        rectnorm = create50pxRectPainter();
        rectnorm.setVertical(RectanglePainter.VerticalAlignment.TOP);
        rectnorm.setHorizontalStretch(true);
        addDemo("Rectangle, 50x50, top aligned w/ horiz stretch", gray, rectnorm);
        // rect 50x50 top aligned w/ vert stretch
        rectnorm = create50pxRectPainter();
        rectnorm.setVertical(RectanglePainter.VerticalAlignment.TOP);
        rectnorm.setVerticalStretch(true);
        addDemo("Rectangle, 50x50, top aligned w/ vert stretch", gray, rectnorm);
        // rect 50x50 top aligned w/ vert & horiz stretch
        rectnorm = create50pxRectPainter();
        rectnorm.setVertical(RectanglePainter.VerticalAlignment.TOP);
        rectnorm.setHorizontalStretch(true);
        rectnorm.setVerticalStretch(true);
        addDemo("Rectangle, 50x50, top aligned w/ horiz & vert stretch", gray, rectnorm);
        // rect 50x50 center aligned w/ vert & horiz stretch
        rectnorm = create50pxRectPainter();
        rectnorm.setVerticalStretch(true);
        rectnorm.setHorizontalStretch(true);
        addDemo("Rectangle, 50x50, center aligned w/ horiz & vert stretch", gray, rectnorm);
        
        // rect 50x50 center aligned w/ vert & horiz stretch & 20px insets
        rectnorm = create50pxRectPainter();
        rectnorm.setVerticalStretch(true);
        rectnorm.setHorizontalStretch(true);
        rectnorm.setInsets(new Insets(20,20,20,20));
        addDemo("Rectangle, 50x50, w/ horiz & vert stretch & 20px insets", gray, rectnorm);
        
        // rectangle with shadow
        RectanglePainter rectshad = createStandardRectPainter();
        rectshad.setAntialiasing(AbstractPainter.Antialiasing.On);
        ShadowPathEffect rectShadEffect = new ShadowPathEffect();
        //rectShadEffect.setOffset(new Point(10,10));
        rectshad.setPathEffect(rectShadEffect);
        addDemo(new JXPanel(),new CompoundPainter(gray,rectshad),"Rectangle with shadow");
        
        // rectangle with glow
        RectanglePainter rectglow = createStandardRectPainter();
        rectglow.setAntialiasing(AbstractPainter.Antialiasing.On);
        rectglow.setPathEffect(new GlowPathEffect());
        addDemo(new JXPanel(),new CompoundPainter(gray,rectglow),"Rectangle with glow");
        
        // rectangle with inner shadow
        RectanglePainter rectinshad = new RectanglePainter(20,20,20,20, 30,30, true,
                Color.GREEN, 3, Color.GREEN.darker());
        rectinshad.setAntialiasing(AbstractPainter.Antialiasing.On);
        InnerShadowPathEffect rectinshadEffect = new InnerShadowPathEffect();
        rectinshad.setPathEffect(rectinshadEffect);
        addDemo(new JXPanel(),new CompoundPainter(new MattePainter(Color.GRAY),
                rectinshad),"Rectangle with inner shadow");
        
        // rectangle with inner glow
        RectanglePainter rectinglow = new RectanglePainter(20,20,20,20, 30,30, true,
                Color.GREEN, 3, Color.GREEN.darker());
        rectinglow.setAntialiasing(AbstractPainter.Antialiasing.On);
        InnerGlowPathEffect rectinglowEffect = new InnerGlowPathEffect();
        rectinglow.setPathEffect(rectinglowEffect);
        addDemo(new JXPanel(),new CompoundPainter(new MattePainter(Color.GRAY),
                rectinglow),"Rectangle with inner glow");
        
        
        // rectangle with cool border
        RectanglePainter rectneon = new RectanglePainter(20,20,20,20, 30,30, true,
                Color.GREEN, 3, Color.GREEN.darker());
        rectneon.setStyle(RectanglePainter.Style.FILLED);
        rectneon.setAntialiasing(AbstractPainter.Antialiasing.On);
        rectneon.setPathEffect(new NeonBorderEffect(Color.WHITE, Color.ORANGE, 20));
        addDemo(new JXPanel(),new CompoundPainter(new MattePainter(Color.GRAY),
                rectneon),"Rectangle with neon border");
        
        
        
        //a list with painter effects
        PainterListCellRenderer renderer = new PainterListCellRenderer();
        
        // the list background
        RectanglePainter listRect = new RectanglePainter(1,1,1,1,10,10,true, Color.RED.brighter(), 1, Color.RED.darker());
        listRect.setAntialiasing(RectanglePainter.Antialiasing.On);
        CompoundPainter listNormalBg = new CompoundPainter(
                new MattePainter(Color.WHITE),
                listRect);
        renderer.setBackgroundPainter(listNormalBg);

        // the list foreground
        TextPainter listText = new TextPainter();
        listText.setFillPaint(Color.WHITE);
        listText.setHorizontal(TextPainter.HorizontalAlignment.LEFT);
        listText.setInsets(new Insets(0,8,0,0));
        listText.setAntialiasing(TextPainter.Antialiasing.On);

        listNormalBg.setAntialiasing(CompoundPainter.Antialiasing.On);
        renderer.setForegroundPainter(listText);
        
        // the list selection
        Painter selectionPainter = new CompoundPainter(
                new MattePainter(gradient),
                listText);
        renderer.setSelectionPainter(selectionPainter);
        
        // set it on the list
        JList list = createJListWithData();
        list.setCellRenderer(renderer);
        addDemo(list,null,"JList with red bg + gradient selection");
        
        // another list with normal even odds, plus reuse previous selection painter
        PainterListCellRenderer evenodd = new PainterListCellRenderer() {
            public Component getListCellRendererComponent(JList list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                // initialize the renderer (which is 'this')
                super.getListCellRendererComponent(list, value,
                        index, isSelected, cellHasFocus);
                setOpaque(true);
                if(index % 2 == 0) {
                    setBackground(Color.WHITE);
                } else {
                    setBackground(Color.LIGHT_GRAY);
                }
                return this;
            }
            
        };
        evenodd.setSelectionPainter(selectionPainter);
        list = createJListWithData();
        list.setCellRenderer(evenodd);
        addDemo(list,null,"JList with even/odd + gradient selection");
        
        
        
        // a generated triangle with a gradient
        ShapePainter triangle = new ShapePainter(ShapeUtils.generatePolygon(3,50,30));
        triangle.setFillPaint(gradient);
        addDemo(new JXPanel(),triangle,"Triangle w/ gradient");
        
        
        
        TextPainter normText = new TextPainter("Text", font);
        comp = new CompoundPainter(normText);
        addDemo(new JXPanel(), comp, "Normal Text");

        TextPainter rotText = new TextPainter("Text", font);
        comp = new CompoundPainter(rotText);
        comp.setTransform(AffineTransform.getRotateInstance(-Math.PI*2/8,100,100));
        addDemo(new JXPanel(), comp, "Rotated Text");
        
        
        TextPainter shearText = new TextPainter("Text", font);
        comp = new CompoundPainter(shearText);
        comp.setTransform(AffineTransform.getShearInstance(-0.2,0));
        addDemo(new JXPanel(), comp, "Sheared Text");
        
        TextPainter scaleText = new TextPainter("Text", font);
        comp = new CompoundPainter(scaleText);
        comp.setTransform(AffineTransform.getScaleInstance(2,2));
        addDemo(new JXPanel(), comp, "Scaled Text");
        
        
        rotText = new TextPainter("Text", font);
        rectnorm = new RectanglePainter(30,30,30,30,30,30,true,Color.RED,4f,Color.RED.darker());
        rectnorm.setAntialiasing(AbstractPainter.Antialiasing.On);
        comp = new CompoundPainter(rectnorm,rotText);
        comp.setTransform(AffineTransform.getRotateInstance(-Math.PI*2/8,100,100));
        addDemo(new JXPanel(), comp, "Rotated Text w/ effects on rect");
        
        
        
        
        // a text painter using the neon border path effect
        TextPainter coollogo = new TextPainter("abcde");
        coollogo.setFont(new Font("SansSerif",Font.BOLD,100));
        coollogo.setFillPaint(Color.GREEN);
        coollogo.setPathEffect(new NeonBorderEffect(Color.BLUE, Color.RED, 20));
        addDemo(new JXPanel(),coollogo,"A Cool Logo");

        
        // create a coming soon badge
        star = new ShapePainter(ShapeUtils.generatePolygon(30,50,45,true),Color.RED);
        star.setStyle(ShapePainter.Style.FILLED);
        star.setBorderPaint(Color.BLUE);
        ShadowPathEffect starShadow = new ShadowPathEffect();
        starShadow.setOffset(new Point(1,1));
        starShadow.setEffectWidth(5);
        star.setPathEffect(starShadow);
        star.setAntialiasing(ShapePainter.Antialiasing.On);
        addDemo(new JXPanel(), new CompoundPainter(
                new MattePainter(Color.GRAY),
                star,
                new TextPainter("Coming Soon!", 
                    new Font("SansSerif", Font.PLAIN, 12), Color.WHITE)
                ),
                "Coming Soon Badge");
        
    }

    private RectanglePainter create50pxRectPainter() {
        RectanglePainter rectnorm;
        
        // rectangle positioning
        // rect 50x50 default aligned (center)
        rectnorm = new RectanglePainter(50, 50, 30, Color.GREEN);
        rectnorm.setAntialiasing(AbstractPainter.Antialiasing.On);
        rectnorm.setBorderPaint(Color.GREEN.darker());
        //rectnorm.setStyle(AbstractPainter.Style.FILLED);
        rectnorm.setBorderWidth(3);
        return rectnorm;
    }

    private RectanglePainter createStandardRectPainter() {
        RectanglePainter rectnorm;
        // rect w/ 20px top insets
        rectnorm = new RectanglePainter(20,20,20,20, 30,30, true,
                Color.GREEN, 3, Color.GREEN.darker());
        rectnorm.setAntialiasing(AbstractPainter.Antialiasing.On);
        return rectnorm;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        painterList = new javax.swing.JList();
        painterPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jSplitPane1.setDividerLocation(250);
        painterList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        painterList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                painterListValueChanged(evt);
            }
        });

        jScrollPane1.setViewportView(painterList);

        jSplitPane1.setLeftComponent(jScrollPane1);

        painterPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        org.jdesktop.layout.GroupLayout painterPanelLayout = new org.jdesktop.layout.GroupLayout(painterPanel);
        painterPanel.setLayout(painterPanelLayout);
        painterPanelLayout.setHorizontalGroup(
            painterPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 421, Short.MAX_VALUE)
        );
        painterPanelLayout.setVerticalGroup(
            painterPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 307, Short.MAX_VALUE)
        );
        jSplitPane1.setRightComponent(painterPanel);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSplitPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSplitPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void painterListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_painterListValueChanged
        Demo demo = (Demo)painterList.getSelectedValue();
        painterPanel.removeAll();
        painterPanel.add(demo.component,"Center");
        //demo.component.repaint();
        painterPanel.revalidate();
        painterPanel.repaint();
    }//GEN-LAST:event_painterListValueChanged
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PainterDemoSet().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JList painterList;
    private javax.swing.JPanel painterPanel;
    // End of variables declaration//GEN-END:variables
    
    private void addDemo(JComponent component, Painter painter, String string) {
        ((DefaultListModel)painterList.getModel()).addElement(new Demo(component,string));
        //painterPanel.removeAll();
        //painterPanel.add(component, "Center");
        if(component instanceof JXPanel) {
            ((JXPanel)component).setBackgroundPainter(painter);
        }
        if(component instanceof JXButton) {
            ((JXButton)component).setForegroundPainter(painter);
        }
        //painterPanel.repaint();
    }
    
    private void addDemo(String text, Painter ... painters) {
        addDemo(new JXPanel(),new CompoundPainter(painters),text);
    }
    
    private JList createJListWithData() {
        String[] data = { "Item 1", "Item 2", "Item 3", "Item 4" };
        return new JList(data);
    }
    
    
    private class Demo {
        public JComponent component;
        public String title;
        public Demo(JComponent component, String title) {
            this.component = component;
            this.title = title;
        }
        public String toString() {
            return this.title;
        }
    }
    
}
