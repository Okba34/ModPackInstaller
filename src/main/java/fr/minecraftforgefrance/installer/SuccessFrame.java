package fr.minecraftforgefrance.installer;

import static fr.minecraftforgefrance.common.Localization.LANG;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SuccessFrame extends JFrame
{
    private static final long serialVersionUID = 1L;

    public SuccessFrame()
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setTitle(LANG.getTranslation("misc.success"));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(300, 100);
        this.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        JLabel label = new JLabel(LANG.getTranslation("installation.success"));
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setAlignmentY(TOP_ALIGNMENT);
        panel.add(label);
        this.getContentPane().add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton exit = new JButton(LANG.getTranslation("scr.btn.exit"));
        exit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                SuccessFrame.this.dispose();
            }
        });
        buttonPanel.add(exit);
        JButton runGame = new JButton(LANG.getTranslation("scr.btn.run"));
        runGame.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    Process ps = Runtime.getRuntime().exec("java -jar " + Installer.frame.mcDir.getPath() + File.separator + "launcher.jar");
                    ps.waitFor();
                    InputStream is = ps.getErrorStream();
                    byte b[] = new byte[is.available()];
                    is.read(b, 0, b.length);
                    if(new String(b).contains("Error"))
                    {
                        JOptionPane.showMessageDialog(null, LANG.getTranslation("err.runminecraft"), LANG.getTranslation("misc.error"), JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, LANG.getTranslation("err.runminecraft"), LANG.getTranslation("misc.error"), JOptionPane.ERROR_MESSAGE);
                }
                SuccessFrame.this.dispose();
            }
        });
        buttonPanel.add(runGame);
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        int x = (dim.width / 2) - (this.getSize().width / 2);
        int y = (dim.height / 2) - (this.getSize().height / 2);
        this.setLocation(x, y);
    }
}
