package gamesmanager.ui;

import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;

public class HelpWindow extends JFrame implements HyperlinkListener {

    static final long serialVersionUID = -9877896;

    public HelpWindow(String index, int width, int height) {
        super("Ayuda: Random Games Management System");
        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        editorPane.addHyperlinkListener(this);

        URL url = getClass().getResource(index);
        if (url != null) {
            try {
                editorPane.setPage(url);
            } catch (IOException ioe) {
                if (Helpers.DEBUG) {
                    System.err.println("Bad URL: " + url);
                    ioe.printStackTrace();
                }
            }
        } else {
            System.err.println("File not found: " + index);
        }
        JScrollPane editorScrollPane = new JScrollPane(editorPane);
        editorScrollPane
                .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        editorScrollPane.setPreferredSize(new Dimension(width, height));
        editorScrollPane.setMinimumSize(new Dimension(10, 10));
        this.add(editorScrollPane);
        Helpers.setIcon(this);
        this.pack();
        this
                .setSize(new Dimension(this.getWidth() + 10,
                        this.getHeight() + 10));
        this.setLocationRelativeTo(null);
    }

    public void hyperlinkUpdate(HyperlinkEvent e) {
        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            JEditorPane pane = (JEditorPane) e.getSource();
            if (e instanceof HTMLFrameHyperlinkEvent) {
                HTMLFrameHyperlinkEvent evt = (HTMLFrameHyperlinkEvent) e;
                HTMLDocument doc = (HTMLDocument) pane.getDocument();
                doc.processHTMLFrameHyperlinkEvent(evt);
            } else {
                try {
                    pane.setPage(e.getURL());
                } catch (Throwable t) {
                    if (Helpers.DEBUG) {
                        t.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String args[]) {
        HelpWindow h = new HelpWindow("help/index.html", 450, 450);
        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        h.setVisible(true);

        HelpWindow h2 = new HelpWindow("about/index.html", 330, 330);
        h2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        h2.setVisible(true);
    }
}
