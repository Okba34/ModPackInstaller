package fr.minecraftforgefrance.installer;

import static fr.minecraftforgefrance.common.Localization.LANG;

import java.awt.Desktop;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.minecraftforgefrance.common.RemoteInfoReader;

public class CreditFrame extends JDialog
{
    private static final long serialVersionUID = 1L;

    public CreditFrame(Frame parent)
    {
    	super(parent);
        this.setTitle(LANG.getTranslation("title.credits"));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setModalityType(ModalityType.APPLICATION_MODAL);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton sponsorButton = new JButton(LANG.getTranslation("scr.btn.mffwebsite"));
        sponsorButton.setAlignmentX(CENTER_ALIGNMENT);
        sponsorButton.setAlignmentY(CENTER_ALIGNMENT);
        sponsorButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    Desktop.getDesktop().browse(new URI("http://www.minecraftforgefrance.fr"));
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(CreditFrame.this, String.format(LANG.getTranslation("err.cannotopenurl"), "http://www.minecraftforgefrance.fr"), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel sponsorPanel = new JPanel();
        sponsorPanel.setLayout(new BoxLayout(sponsorPanel, BoxLayout.X_AXIS));
        sponsorPanel.setAlignmentX(CENTER_ALIGNMENT);
        sponsorPanel.setAlignmentY(CENTER_ALIGNMENT);
        sponsorPanel.add(sponsorButton);

        JLabel text = new JLabel();
        String creditText = "<html><br>";
        if(RemoteInfoReader.instance().hasCredits())
        {
            creditText += RemoteInfoReader.instance().getCredits() + "<br><br>";
        }
        creditText += String.format(LANG.getTranslation("scr.credits.html"), "robin4002", "kevin_68", "utybo", "cpw") + "<br></html>";
        text.setText(creditText);
        text.setAlignmentX(CENTER_ALIGNMENT);
        text.setAlignmentY(CENTER_ALIGNMENT);

        panel.add(text);
        panel.add(sponsorPanel);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(parent);
    }
}